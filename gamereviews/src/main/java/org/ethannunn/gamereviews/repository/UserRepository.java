package org.ethannunn.gamereviews.repository;

import org.ethannunn.gamereviews.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, Integer> {

	UserEntity findByUsername(String username);
	UserEntity findByEmail(String email);
}
