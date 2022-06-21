package org.ethannunn.gamereviews.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="comment")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int comment_id;

	@Column(name="post_date")
	private String postDate;
	
	private String content;
	
	@ManyToOne(targetEntity = UserEntity.class, cascade = CascadeType.ALL)
	private UserEntity user;
	
	public CommentEntity(String content) {
		this.content = content;
	}
}
