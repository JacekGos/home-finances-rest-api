package com.jacekg.homefinances.user;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.matchers.Any;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.jacekg.homefinances.role.Role;
import com.jacekg.homefinances.role.RoleRepository;

@SpringBootTest
class UserServiceImplTest {

	@InjectMocks
	UserServiceImpl service;
	
	@Mock
	private UserRepository userRepository;
	
	@Mock
	private RoleRepository roleRepository;
	
	@Mock
	private BCryptPasswordEncoder passwordEncoder;
	
	/*
	@Test
	void save_ShouldReturn_UserWithAdminRole() {
		
		UserDTO userDTO = new UserDTO();
		userDTO.setRole("USER");

		Collection<Role> roles = new ArrayList<Role>();
		roles.add(new Role(1L, "ROLE_USER"));
//		roles.add(new Role(2L, "ROLE_ADMIN"));
		
		User user = new User();
		user.setRoles(roles);
		
//		userDTO.setId(1L);
//		userDTO.setUsername("username");
//		userDTO.setPassword(passwordEncoder.encode("password"));
//		userDTO.setFirstName(StringUtils.capitalize("firstName"));
//		userDTO.setLastName(StringUtils.capitalize("lastName"));
//		userDTO.setEmail("email");
//		userDTO.setEnabled(true);
//		userDTO.setNonExpired(true);
//		userDTO.setCredentialsNonExpired(true);
//		userDTO.setNonLocked(true);
		
		
		when(roleRepository.findByName(userDTO.getRole())).thenReturn(new Role(1L, "ROLE_USER"));
		when(userRepository.save(user)).thenReturn(user);
		
		User returnedUser = service.save(userDTO);
		
		assertEquals("ROLE_USER", returnedUser.getRoles());
		
		verify(roleRepository).findByName(userDTO.getRole());
		verify(userRepository).save(user);
	}
	
	@Test
	void save_ShouldReturn_User() {
		
		User user = new User();
		User savedUser = new User();
		UserDTO inputUser = new UserDTO();
		inputUser.setRole("USER");
		
		when(userRepository.save(user)).thenReturn(savedUser);
		
		User outputUser = service.save(inputUser);
		
		assertNotNull(outputUser);
		
		verify(userRepository).save(user);
	}
	
	@Test
	void findAll_ShouldReturn_UsersList() {
		
		List<User> usersList = new ArrayList<>();
		usersList.add(new User());
		
		when(userRepository.findAll()).thenReturn(usersList);
		
		List<User> returnedList = service.findAll();
		
		assertNotNull(returnedList);
		
		verify(userRepository).findAll();
	}
	
	@Test
	void findByUserName_ShouldReturn_User() {
		
		User user = new User();
		user.setUsername("user");
		
		when(userRepository.findByUsername("user")).thenReturn(user);
		
		User returnerUser = service.findByUsername("user");
		
		assertNotNull(returnerUser);
		assertEquals("user", returnerUser.getUsername());
		
		verify(userRepository).findByUsername("user");
	}
	*/
}

















