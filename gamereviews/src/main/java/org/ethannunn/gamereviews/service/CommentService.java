package org.ethannunn.gamereviews.service;

import java.util.List;

import org.ethannunn.gamereviews.entity.CommentEntity;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

public interface CommentService {

	CommentEntity save(CommentEntity commentEntity);
	
	void deleteComment(CommentEntity commentEntity);
	
	CommentEntity getCommentById(Integer id) throws NotFoundException;
}
