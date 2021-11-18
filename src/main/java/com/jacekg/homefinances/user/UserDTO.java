package com.jacekg.homefinances.user;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserDTO {
	
private Long id;
	
	private String userName;
	
	private String password;
	
	@NotNull(message = "wymagane")
	@Size(min = 1, max = 50, message = "za długie imię")
	private String firstName;
	
	@NotNull(message = "wymagane")
	@Size(min = 1, max = 50, message = "za długie nazwisko")
	private String lastName;
	
//	@ValidEmail
	@NotNull(message = "wymagane")
	@Size(min = 1, max = 50, message = "za długi email")
	private String email;
	
	private String role;
	
	private boolean isEnabled;
	
	private boolean isNonExpired;
	 
	private boolean isCredentialsNonExpired;
	 
	private boolean isNonLocked;
	
	public UserDTO() {
		
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public boolean isEnabled() {
		return isEnabled;
	}

	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	public boolean isNonExpired() {
		return isNonExpired;
	}

	public void setNonExpired(boolean isNonExpired) {
		this.isNonExpired = isNonExpired;
	}

	public boolean isCredentialsNonExpired() {
		return isCredentialsNonExpired;
	}

	public void setCredentialsNonExpired(boolean isCredentialsNonExpired) {
		this.isCredentialsNonExpired = isCredentialsNonExpired;
	}

	public boolean isNonLocked() {
		return isNonLocked;
	}

	public void setNonLocked(boolean isNonLocked) {
		this.isNonLocked = isNonLocked;
	}

	@Override
	public String toString() {
		return "FormUser [userName=" + userName + ", password=" + password + ", firstName=" + firstName + ", lastName="
				+ lastName + ", email=" + email + ", role=" + role + "]";
	}
	
}
