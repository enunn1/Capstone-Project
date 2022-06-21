package org.ethannunn.gamereviews.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.ethannunn.gamereviews.constraint.FieldMatch;

@FieldMatch.List({
    @FieldMatch(first = "password", second = "confirmPassword", message = "The password fields must match")
})
public class UserRegistrationDto {

	@NotEmpty
    private String username;
	
	@Email
    @NotEmpty
    private String email;
	
	@NotEmpty
    private String password;

    @NotEmpty
    private String confirmPassword;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
    
    
}
