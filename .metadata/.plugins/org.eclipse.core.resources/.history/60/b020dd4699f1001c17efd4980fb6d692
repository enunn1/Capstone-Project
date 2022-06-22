package org.ethannunn.gamereviews.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.ethannunn.gamereviews.entity.GameEntity;
import org.ethannunn.gamereviews.repository.GameRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class GameServiceTest {
	
	@Mock
	private GameRepository gameRepository;

	private GameService gameService; //service under test
	
	GameEntity game;

	@BeforeEach
	public void setUp() {
	    this.gameService = new GameServiceImpl(gameRepository);
	}
	
	@Test
	public void when_no_games_return_empty_list() {
		Mockito.when(gameRepository.findAllOrderByGameTitle()).thenReturn(Collections.emptyList());
		var gamesFromDb = gameService.getAllGames();
		assertTrue(gamesFromDb.isEmpty());
	}
	
	@Test
	public void get_all_games_sort_by_title() {
		List<GameEntity> games = new ArrayList<GameEntity>();
		
		games.add(new GameEntity("My Game", null, null, 0, 0, null));
		games.add(new GameEntity("Game #1", null, null, 0, 0, null));
		games.add(new GameEntity("A Game", null, null, 0, 0, null));
		Collections.sort(games, (x, y) -> (x.getGameTitle().compareTo(y.getGameTitle())));
		Mockito.when(gameRepository.findAllOrderByGameTitle()).thenReturn(games);
		
		var gamesFromDb = gameService.getAllGames();
		
		assertEquals(gamesFromDb.get(0).getGameTitle(), "A Game");
		assertEquals(gamesFromDb.get(1).getGameTitle(), "Game #1");
		assertEquals(gamesFromDb.get(2).getGameTitle(), "My Game");
	}

}
