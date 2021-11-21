package com.jacekg.homefinances.jwt;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.StringUtils;

import com.jacekg.homefinances.role.Role;
import com.jacekg.homefinances.user.User;
import com.jacekg.homefinances.user.UserNotValidException;
import com.jacekg.homefinances.user.UserRepository;

@SpringBootTest
class JwtUserDetailsServiceTest {
	
	@Mock
	UserRepository userRepository;
	
	@InjectMocks
	JwtUserDetailsService service;
	
	@Test
	void loadUserByUsername_ShouldReturn_UserDetails() {
		
		Collection<Role> roles = new ArrayList<Role>();
		Role role = new Role();
		role.setName("USER");
		roles.add(role);
		
		User user = new User();
		user.setUsername("user");
		user.setPassword("password");
		user.setEnabled(true);
		user.setNonExpired(true);
		user.setCredentialsNonExpired(true);
		user.setNonLocked(true);
		user.setRoles(roles);
		
		when(userRepository.findByUsername(user.getUsername())).thenReturn(user);
		
		UserDetails returnedUser = service.loadUserByUsername(user.getUsername());
		
		assertNotNull(returnedUser);
		assertEquals("user", returnedUser.getUsername());
		
		verify(userRepository).findByUsername(user.getUsername());
		
	}
	
	@Test
	void loadUserByUsername_ShouldThrow_UsernameNotFoundException() {
		
		User user = null;
		
		when(userRepository.findByUsername("username")).thenReturn(user);
		
		assertThrows(UsernameNotFoundException.class, () -> service.loadUserByUsername("username"));
		
		verify(userRepository).findByUsername("username");
		
	}
}

















