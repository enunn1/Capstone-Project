package org.ethannunn.gamereviews.service;

import org.ethannunn.gamereviews.entity.CommentEntity;
import org.ethannunn.gamereviews.repository.CommentRepository;
import org.ethannunn.gamereviews.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentRepository commentRepository;
	
	public CommentServiceImpl(CommentRepository commentRepository) {
		this.commentRepository = commentRepository;
	}

	@Override
	public CommentEntity save(CommentEntity commentEntity) {
		return commentRepository.save(commentEntity);
	}
	
	@Override
	public void deleteComment(CommentEntity commentEntity) {
		commentRepository.delete(commentEntity);
	}

	@Override
	public CommentEntity getCommentById(Integer id) throws NotFoundException {
		return commentRepository.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);
	}
}
