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

import com.jacekg.homefinances.role.Role;
import com.jacekg.homefinances.user.User;
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
		roles.add(role);
		
		User user = new User();
		user.setUsername("user");
		user.setRoles(roles);
		
		when(userRepository.findByUsername(user.getUsername())).thenReturn(user);
		
		UserDetails returnedUser = service.loadUserByUsername(user.getUsername());
		
		assertNotNull(returnedUser);
//		assertEquals("user", returnedUser.getUsername());
		
		verify(userRepository).findByUsername(user.getUsername());
		
	}
	
	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}

}

















