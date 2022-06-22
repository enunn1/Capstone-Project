package org.ethannunn.gamereviews.service;

import java.util.List;

import org.ethannunn.gamereviews.entity.GameEntity;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

public interface GameService {

	public List<GameEntity> getAllGames();
	public List<GameEntity> searchGameByTitle(String title);
	public List<GameEntity> getMostRecentEntries();
	public GameEntity getGameById(Integer id) throws NotFoundException;
	public GameEntity save(GameEntity game);
}
