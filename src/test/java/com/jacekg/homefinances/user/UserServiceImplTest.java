package com.jacekg.homefinances.user;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.matchers.Any;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.jacekg.homefinances.role.Role;
import com.jacekg.homefinances.role.RoleRepository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

//@SpringBootTest
@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

	@InjectMocks
	UserServiceImpl service;

	@Mock
	private UserRepository userRepository;

	@Mock
	private RoleRepository roleRepository;

	@Mock
	private BCryptPasswordEncoder passwordEncoder;

	@Mock
	private ModelMapper modelMapper;
	
	@Test
	@Disabled
	void save_ShouldReturn_UserWithAdminRole() {

		UserDTO userDTO = new UserDTO();
		userDTO.setRole("USER");

		Collection<Role> roles = new ArrayList<Role>();
		roles.add(new Role(1L, "ROLE_USER"));
//		roles.add(new Role(2L, "ROLE_ADMIN"));

		User user = new User();
		user.setRoles(roles);

//		when(roleRepository.findByName(userDTO.getRole())).thenReturn(new Role(1L, "ROLE_USER"));
//		when(userRepository.save(user)).thenReturn(user);

		UserDTO returnedUser = service.save(userDTO);

		assertEquals("ROLE_USER", returnedUser.getRole());

		verify(roleRepository).findByName(userDTO.getRole());
		verify(userRepository).save(user);
	}

	@Test
	@Disabled
	void save_ShouldReturn_User() {
		
		ModelMapper modelMapper = new ModelMapper();
		UserDTO inputUserDTO = new UserDTO();
		
		inputUserDTO.setId(10L);
		inputUserDTO.setUsername("username");
		inputUserDTO.setPassword("password");
		inputUserDTO.setFirstName("name");
		inputUserDTO.setLastName("lastname");
		inputUserDTO.setEmail("email");
		inputUserDTO.setRole("ROLE_ADMIN");
		
		User inputUser = new User();

		inputUser.setId(10L);
		inputUser.setUsername("username");
		inputUser.setPassword("password");
		inputUser.setFirstName("name");
		inputUser.setLastName("lastname");
		inputUser.setEmail("email");
		inputUser.setRoles(Arrays.asList(new Role(1L, "ROLE_USER"), new Role(2L, "ROLE_ADMIN")));
		
		when(modelMapper.map(inputUserDTO, User.class)).thenReturn(inputUser);
		
//		User savedUser = modelMapper.map(inputUserDTO, User.class);
		
		service.save(inputUserDTO);
		
		ArgumentCaptor<User> userArgumentCaptor =
				ArgumentCaptor.forClass(User.class);
		
//		verify(userRepository).save(userArgumentCaptor.capture());
		verify(userRepository).save(inputUser);
		
		User capturedUser = userArgumentCaptor.getValue();
		
		assertNotNull(capturedUser);
		assertEquals(10L, capturedUser.getId());
//		assertEquals("ROLE_ADMIN", capturedUser.getRoleName());
		
	}

	@Test
	void findAll_CanReturn_UsersList() {

		service.findAll();

		verify(userRepository).findAll();
	}
	
	@Test
	void findByUsername_CanReturn_User() {
		
		String username = "username";
		
		service.findByUsername(username);
		
		verify(userRepository).findByUsername(username);
	}
	
	@Test
	void findByUserId_CanReturn_User() {
		
		Long userId = 1L;
		
		service.findByUserId(userId);
		
		verify(userRepository).findByUserId(userId);
	}
	
	/*
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

	@Test
	void convertToDTO_ShouldReturn_UserDTO() {

		ModelMapper modelMapper = new ModelMapper();

		User inputUser = new User();

		inputUser.setId(10L);
		inputUser.setUsername("username");
		inputUser.setPassword("password");
		inputUser.setFirstName("name");
		inputUser.setLastName("lastname");
		inputUser.setEmail("email");
		inputUser.setRoles(Arrays.asList(new Role(1L, "ROLE_USER"), new Role(2L, "ROLE_ADMIN")));

		UserDTO returnedUser = modelMapper.map(inputUser, UserDTO.class);

		assertEquals(10L, returnedUser.getId());
		assertEquals("username", returnedUser.getUsername());
		assertEquals("password", returnedUser.getPassword());
		assertEquals("name", returnedUser.getFirstName());
		assertEquals("lastname", returnedUser.getLastName());
		assertEquals("email", returnedUser.getEmail());
		assertEquals("ROLE_ADMIN", returnedUser.getRole());
	}

	@Test
	void convertToEntity_ShouldReturn_User() {

		ModelMapper modelMapper = new ModelMapper();

		UserDTO inputUserDTO = new UserDTO();

		inputUserDTO.setId(10L);
		inputUserDTO.setUsername("username");
		inputUserDTO.setPassword("password");
		inputUserDTO.setFirstName("name");
		inputUserDTO.setLastName("lastname");
		inputUserDTO.setEmail("email");

		User returnedUser = modelMapper.map(inputUserDTO, User.class);

		assertEquals(10L, returnedUser.getId());
		assertEquals("username", returnedUser.getUsername());
		assertEquals("password", returnedUser.getPassword());
		assertEquals("name", returnedUser.getFirstName());
		assertEquals("lastname", returnedUser.getLastName());
		assertEquals("email", returnedUser.getEmail());
	}
}
