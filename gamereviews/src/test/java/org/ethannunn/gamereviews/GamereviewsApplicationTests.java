package org.ethannunn.gamereviews;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.ethannunn.gamereviews.entity.CommentEntity;
import org.ethannunn.gamereviews.entity.ConsoleEntity;
import org.ethannunn.gamereviews.entity.GameEntity;
import org.ethannunn.gamereviews.entity.RoleEntity;
import org.ethannunn.gamereviews.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GamereviewsApplicationTests {

	@Test
	void contextLoads() {
	}
	
	@ParameterizedTest
	@CsvSource({"Game #1", "A Game", "My Game"})
	void test_set_game_title(String gameTitle) {
		GameEntity game = new GameEntity();
		game.setGameTitle(gameTitle);
		assertEquals(game.getGameTitle(), gameTitle);
	}
	
	@ParameterizedTest
	@CsvSource({"Comment text", "String", "Test string"})
	void test_add_game_comment(String content) {
		CommentEntity comment = new CommentEntity();
		comment.setContent(content);
		GameEntity game = new GameEntity();
		game.setComments(new ArrayList<CommentEntity>());
		game.getComments().add(comment);
		assertTrue(game.getComments().contains(comment));
	}
	
	@ParameterizedTest
	@CsvSource({"Wii", "NES", "Playstation"})
	void test_set_console_name(String consoleName) {
		ConsoleEntity console = new ConsoleEntity();
		console.setConsoleName(consoleName);
		assertEquals(console.getConsoleName(), consoleName);
	}
	
	@ParameterizedTest
	@CsvSource({"NintendoFan12", "NewUser84", "JohnSample"})
	void test_set_user_name(String username) {
		UserEntity user = new UserEntity();
		user.setUsername(username);
		assertEquals(user.getUsername(), username);
	}
	
	@ParameterizedTest
	@CsvSource({"Comment text", "String", "Test string"})
	void test_set_comment_content(String content) {
		CommentEntity comment = new CommentEntity();
		comment.setContent(content);
		assertEquals(comment.getContent(), content);
	}
	
	@ParameterizedTest
	@CsvSource({"Comment text", "String", "Test string"})
	void test(String roleName) {
		RoleEntity role = new RoleEntity();
		role.setName(roleName);
		assertEquals(role.getName(), roleName);
	}

}
