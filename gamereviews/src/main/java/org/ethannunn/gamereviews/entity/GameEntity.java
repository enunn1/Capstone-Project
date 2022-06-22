package org.ethannunn.gamereviews.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="game")
@Data
@NoArgsConstructor
public class GameEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="game_id")
	private int gameId;

	@Column(name="game_title")
	private String gameTitle;
	
	private String genre;
	
	private String publisher;
	
	@Column(name="release_year")
	private int releaseYear;
	
	private int score;
	
	private String review;

	@OneToMany(targetEntity = CommentEntity.class, cascade = CascadeType.ALL)
	private List<CommentEntity> comments;

	@ManyToMany(targetEntity = ConsoleEntity.class, cascade = CascadeType.ALL)
	private List<ConsoleEntity> console;

	public GameEntity(String gameTitle, String genre, String publisher, int releaseYear, int score, String review) {
		this.gameTitle = gameTitle;
		this.genre = genre;
		this.publisher = publisher;
		this.releaseYear = releaseYear;
		this.score = score;
		this.review = review;
	}
}
