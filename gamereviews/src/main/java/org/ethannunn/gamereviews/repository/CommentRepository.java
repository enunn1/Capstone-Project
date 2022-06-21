package org.ethannunn.gamereviews.repository;

import org.ethannunn.gamereviews.entity.CommentEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface CommentRepository extends CrudRepository<CommentEntity, Integer> {

}
