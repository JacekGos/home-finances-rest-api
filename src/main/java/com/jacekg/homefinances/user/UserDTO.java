package com.jacekg.homefinances.user;

import java.util.Collection;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.jacekg.homefinances.role.Role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class UserDTO {
	
private Long id;
	
	private String username;
	
	private String password;
	
	@NotNull(message = "wymagane")
	@Size(min = 1, max = 50, message = "za długie imię")
	private String firstName;
	
	@NotNull(message = "wymagane")
	@NotBlank
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
	
}
