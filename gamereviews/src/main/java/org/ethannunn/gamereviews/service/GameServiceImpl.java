package org.ethannunn.gamereviews.service;

import java.util.List;

import org.ethannunn.gamereviews.entity.GameEntity;
import org.ethannunn.gamereviews.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

@Service
public class GameServiceImpl implements GameService {
	
	@Autowired
	private GameRepository gameRepository;
	
	public GameServiceImpl(GameRepository gameRepository) {
		this.gameRepository = gameRepository;
	}
	
	@Override
	public List<GameEntity> getAllGames() {
		return gameRepository.findAllOrderByGameTitle();
	}
	
	@Override
	public List<GameEntity> searchGameByTitle(String title) {
		return gameRepository.findByGameTitle(title);
	}

	@Override
	public GameEntity getGameById(Integer id) throws NotFoundException {
		return gameRepository.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);
	}

	@Override
	public GameEntity save(GameEntity game) {
		return gameRepository.save(game);
	}

	

}
