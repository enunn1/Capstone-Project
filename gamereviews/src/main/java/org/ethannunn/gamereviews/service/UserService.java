package org.ethannunn.gamereviews.service;

import org.ethannunn.gamereviews.dto.UserRegistrationDto;
import org.ethannunn.gamereviews.entity.UserEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
	
	UserEntity findByUsername(String username);
	UserEntity findByEmail(String email);
	UserEntity save(UserRegistrationDto registration);
	UserEntity getLoggedInUser();

}
