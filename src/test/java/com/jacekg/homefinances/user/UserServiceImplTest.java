package com.jacekg.homefinances.user;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.internal.matchers.Any;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jacekg.homefinances.role.Role;
import com.jacekg.homefinances.role.RoleRepository;


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
		
		UserDTO inputUserDTO = new UserDTO(
				10L,
				"username",
				"password",
				"password",
				"firstname",
				"lastname",
				"email",
				"ROLE_ADMIN", 
				true, true, true, true); 
	
		User mappedUser = new User(
				10L,
				"username",
				"password",
				"firstname",
				"lastname",
				"email",
				true, true, true, true,
				null, null,
				Arrays.asList(new Role(1L, "ROLE_USER"), new Role(2L, "ROLE_ADMIN")));
		
		User expectedUser = new User(
				10L,
				"username",
				"password",
				"firstname",
				"lastname",
				"email",
				true, true, true, true,
				null,
				null,
				Arrays.asList(new Role(1L, "ROLE_USER"), new Role(2L, "ROLE_ADMIN")));
		
		when(userRepository.findByUsername(Mockito.anyString())).thenReturn(new User());
//		when(modelMapper.map(inputUserDTO, User.class)).thenReturn(mappedUser);
		when(userRepository.save(mappedUser)).thenReturn(expectedUser);
//		
//		UserDTO returnedUser = service.save(inputUserDTO);
		
		User inputUser = new User();

		inputUser.setId(10L);
		inputUser.setUsername("username");
		inputUser.setPassword("password");
		inputUser.setFirstName("name");
		inputUser.setLastName("lastname");
		inputUser.setEmail("email");
		inputUser.setRoles(Arrays.asList(new Role(1L, "ROLE_USER"), new Role(2L, "ROLE_ADMIN")));
		
//		when(modelMapper.map(inputUserDTO, org.mockito.ArgumentMatchers.any())).thenReturn(inputUser);
//		when(modelMapper.map(Mockito.any(UserDTO.class), Mockito.any(User.class))).thenReturn(inputUser);
//		when(modelMapper.map(new UserDTO(), User.class)).thenReturn(inputUser);
//		when(modelMapper.map(org.mockito.ArgumentMatchers.any(), org.mockito.ArgumentMatchers.any())).
//			thenReturn(inputUser);
		
		when(userRepository.findByUsername(Mockito.anyString())).thenReturn(null);
		when(modelMapper.map(new UserDTO(), org.mockito.ArgumentMatchers.any())).thenReturn(mappedUser);
		when(userRepository.save(mappedUser)).thenReturn(expectedUser);
		
//		User savedUser = modelMapper.map(inputUserDTO, User.class);
		
		service.save(inputUserDTO);
		
		ArgumentCaptor<User> userArgumentCaptor =
				ArgumentCaptor.forClass(User.class);
		
		verify(userRepository).save(userArgumentCaptor.capture());
		verify(userRepository).save(inputUser);
		
		User capturedUser = userArgumentCaptor.getValue();
		
		assertNotNull(capturedUser);
		assertEquals(10L, capturedUser.getId());
		assertEquals("ROLE_ADMIN", capturedUser.getRoleName());
		
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

	@Test
	void convertToDTO_ShouldReturn_UserDTO() {

		ModelMapper modelMapper = new ModelMapper();
		
		User inputUser = new User(
				10L,
				"username",
				"password",
				"name",
				"lastname",
				"email",
				true, true, true, true,
				null,
				null,
				Arrays.asList(new Role(1L, "ROLE_USER"), new Role(2L, "ROLE_ADMIN")));

		UserDTO returnedUser = modelMapper.map(inputUser, UserDTO.class);
		
		Assertions.assertThat(returnedUser.getId()).isEqualTo(10L);
		Assertions.assertThat(returnedUser.getUsername()).isEqualTo("username");
		Assertions.assertThat(returnedUser.getPassword()).isEqualTo("password");
		Assertions.assertThat(returnedUser.getFirstName()).isEqualTo("name");
		Assertions.assertThat(returnedUser.getLastName()).isEqualTo("lastname");
		Assertions.assertThat(returnedUser.getEmail()).isEqualTo("email");
		Assertions.assertThat(returnedUser.getRole()).isEqualTo("ROLE_ADMIN");
	}

	@Test
	void convertToEntity_ShouldReturn_User() {

		ModelMapper modelMapper = new ModelMapper();

		UserDTO inputUserDTO = new UserDTO(
				10L,
				"username",
				"password",
				"password",
				"name",
				"lastname",
				"email",
				"ROLE_ADMIN", 
				true, true, true, true); 

		User returnedUser = modelMapper.map(inputUserDTO, User.class);
		
		Assertions.assertThat(inputUserDTO.getId()).isEqualTo(10L);
		Assertions.assertThat(inputUserDTO.getUsername()).isEqualTo("username");
		Assertions.assertThat(inputUserDTO.getPassword()).isEqualTo("password");
		Assertions.assertThat(inputUserDTO.getFirstName()).isEqualTo("name");
		Assertions.assertThat(inputUserDTO.getLastName()).isEqualTo("lastname");
		Assertions.assertThat(inputUserDTO.getEmail()).isEqualTo("email");
	}
}
