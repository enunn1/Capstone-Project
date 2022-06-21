package org.ethannunn.gamereviews.entity;

import java.util.Collection;
import java.util.List;

import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user", uniqueConstraints = @UniqueConstraint(columnNames = { "email", "username" }))
@Data
@NoArgsConstructor
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int user_id;

	private String username;

	private String password;

	private String email;

	@ManyToMany(fetch = FetchType.EAGER, targetEntity = RoleEntity.class, cascade = CascadeType.ALL)
	private Collection<RoleEntity> roles;

	public UserEntity(String username, String password, String email) {
		this.username = username;
		this.password = password;
		this.email = email;
	}
}
