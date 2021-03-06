package org.ethannunn.gamereviews.controller;

import java.util.List;

import org.ethannunn.gamereviews.entity.GameEntity;
import org.ethannunn.gamereviews.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/")
public class MainController {

	@Autowired
	GameService gameService;

	@GetMapping(path = "/")
	public String showHomePage(Model model) {
		List<GameEntity> latestGames = gameService.getMostRecentEntries();
		model.addAttribute("latestGames", latestGames);
		return "index";
	}

	@GetMapping(path = "/about")
	public String showAboutPage() {
		return "about";
	}

	@GetMapping(path = "/login")
	public String showLoginPage() {
		return "login";
	}

}
