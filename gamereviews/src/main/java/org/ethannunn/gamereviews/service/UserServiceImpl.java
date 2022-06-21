package org.ethannunn.gamereviews.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import org.ethannunn.gamereviews.dto.UserRegistrationDto;
import org.ethannunn.gamereviews.entity.RoleEntity;
import org.ethannunn.gamereviews.entity.UserEntity;
import org.ethannunn.gamereviews.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity user = userRepository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				mapRolesToAuthorities(user.getRoles()));
	}

	@Override
	public UserEntity findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public UserEntity findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public UserEntity save(UserRegistrationDto registration) {
		UserEntity user = new UserEntity();
		user.setUsername(registration.getUsername());
		user.setEmail(registration.getEmail());
		user.setPassword(passwordEncoder.encode(registration.getPassword()));
		user.setRoles(Arrays.asList(new RoleEntity("USER")));
		return userRepository.save(user);
	}

	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<RoleEntity> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}
	
	@Override
	public UserEntity getLoggedInUser() {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		return findByUsername(username);
		
	}

}
