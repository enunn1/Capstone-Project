package org.ethannunn.gamereviews.repository;

import java.util.List;

import org.ethannunn.gamereviews.entity.GameEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<GameEntity, Integer> {

	@Query("Select g FROM GameEntity g ORDER BY gameTitle")
	public List<GameEntity> findAllOrderByGameTitle();
	
//	@Query("Select g FROM GameEntity g ORDER BY game_id DESC LIMIT 3")
	public List<GameEntity> findTop3ByOrderByGameIdDesc();
	
	@Query("Select g FROM GameEntity g Where gameTitle LIKE %:title% ORDER BY gameTitle")
	public List<GameEntity> findByGameTitle(@Param("title") String title);
}
