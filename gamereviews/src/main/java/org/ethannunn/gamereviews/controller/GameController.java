package org.ethannunn.gamereviews.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.ethannunn.gamereviews.entity.CommentEntity;
import org.ethannunn.gamereviews.entity.GameEntity;
import org.ethannunn.gamereviews.entity.UserEntity;
import org.ethannunn.gamereviews.service.CommentService;
import org.ethannunn.gamereviews.service.GameService;
import org.ethannunn.gamereviews.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(path = "/games")
public class GameController {

	@Autowired
	private GameService gameService;

	@Autowired
	private CommentService commentService;

	@Autowired
	private UserService userService;

	@RequestMapping(method = RequestMethod.GET)
	public String getAllGames(Model model, @RequestParam Optional<String> gameTitle) {
		List<GameEntity> games;
		if (gameTitle.isEmpty()) {
			games = gameService.getAllGames();
		} else {
			games = gameService.searchGameByTitle(gameTitle.get());
		}
		model.addAttribute("games", games);
		model.addAttribute("gameEntity", new GameEntity());
		return "games";
	}

	@RequestMapping(path = "/", method = RequestMethod.GET)
	public String redirect() {
		return "redirect:/games";
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public String getGamebyId(@PathVariable("id") Integer id, Model model) {
		try {
			GameEntity gameEntity = gameService.getGameById(id);
			List<CommentEntity> comments = gameEntity.getComments();
			comments.sort((CommentEntity c1, CommentEntity c2) -> c2.getPostDate().compareTo(c1.getPostDate()));
			model.addAttribute("game", gameEntity);
			model.addAttribute("newComment", new CommentEntity());
			model.addAttribute("comments", comments);
			model.addAttribute("updateComment", new CommentEntity());
		} catch (NotFoundException e) {
			return "game_not_found";
		}
		return "game_page";
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.POST)
	public String postComment(@PathVariable("id") Integer gameId, Model model, @Valid CommentEntity commentEntity, BindingResult bindingResult, RedirectAttributes redirAttrs) {
		GameEntity gameEntity;
		try {
			gameEntity = gameService.getGameById(gameId);
		} catch (NotFoundException e) {
			return "game_not_found";
		}
		UserEntity user = userService.getLoggedInUser();
		if (user == null) {
			redirAttrs.addFlashAttribute("failMessage", "Please login to post a comment");
		}
		else if (!bindingResult.hasErrors()) {
			commentEntity.setUser(user);
			commentEntity.setPostDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm:ss")));
			commentService.save(commentEntity);
			
			gameEntity.getComments().add(commentEntity);
			gameService.save(gameEntity);
			redirAttrs.addFlashAttribute("successMessage", "Comment successfully created!");
		}
		return "redirect:./" + gameId;
	}
	
	@RequestMapping(path="{gameId}/delete/{commentId}", method = RequestMethod.POST)
	public String deleteComment(@PathVariable("gameId") Integer gameId, @PathVariable("commentId") Integer commentId, Model model, RedirectAttributes redirAttrs) {
		try {
			GameEntity gameEntity = gameService.getGameById(gameId);
			CommentEntity commentEntity = commentService.getCommentById(commentId);
			
			gameEntity.getComments().remove(commentEntity);
			gameService.save(gameEntity);
			
			commentEntity.setUser(null);
			commentService.save(commentEntity);
			commentService.deleteComment(commentEntity);
			
			redirAttrs.addFlashAttribute("successMessage", "Comment successfully deleted!");
		} catch (NotFoundException e) {
			redirAttrs.addFlashAttribute("failMessage", "Could not delete comment");
		}
		return "redirect:../../" + gameId;
	}
	
	@RequestMapping(path="{gameId}/update/{commentId}", method = RequestMethod.POST)
	public String updateComment(@PathVariable("gameId") Integer gameId, @PathVariable("commentId") Integer commentId, @Valid CommentEntity updatedComment, BindingResult bindingResult, Model model, RedirectAttributes redirAttrs) {
		try {
			CommentEntity commentEntity = commentService.getCommentById(commentId);
				
			if (!bindingResult.hasErrors()) {
				commentEntity.setContent(updatedComment.getContent());
				commentService.save(commentEntity);
			}
			
			redirAttrs.addFlashAttribute("successMessage", "Comment successfully updated!");
		} catch (NotFoundException e) {
			redirAttrs.addFlashAttribute("failMessage", "Could not update comment");
		}
		return "redirect:../../" + gameId;
	}

}
