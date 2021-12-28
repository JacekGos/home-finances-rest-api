package com.jacekg.homefinances.user;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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
	
//	@Test
//	@Disabled
//	void save_ShouldReturn_User() {
//		
//		ModelMapper modelMapper = new ModelMapper();
//		
//		UserDTO inputUserDTO = new UserDTO(
//				10L,
//				"username",
//				"password",
//				"password",
//				"firstname",
//				"lastname",
//				"email",
//				"ROLE_ADMIN", 
//				true, true, true, true); 
//	
//		User mappedUser = new User(
//				10L,
//				"username",
//				"password",
//				"firstname",
//				"lastname",
//				"email",
//				true, true, true, true,
//				null, null,
//				Arrays.asList(new Role(1L, "ROLE_USER"), new Role(2L, "ROLE_ADMIN")));
//		
//		User expectedUser = new User(
//				10L,
//				"username",
//				"password",
//				"firstname",
//				"lastname",
//				"email",
//				true, true, true, true,
//				null,
//				null,
//				Arrays.asList(new Role(1L, "ROLE_USER"), new Role(2L, "ROLE_ADMIN")));
//		
//		when(userRepository.findByUsername(Mockito.anyString())).thenReturn(new User());
////		when(modelMapper.map(inputUserDTO, User.class)).thenReturn(mappedUser);
//		when(userRepository.save(mappedUser)).thenReturn(expectedUser);
////		
////		UserDTO returnedUser = service.save(inputUserDTO);
//		
//		User inputUser = new User();
//
//		inputUser.setId(10L);
//		inputUser.setUsername("username");
//		inputUser.setPassword("password");
//		inputUser.setFirstName("name");
//		inputUser.setLastName("lastname");
//		inputUser.setEmail("email");
//		inputUser.setRoles(Arrays.asList(new Role(1L, "ROLE_USER"), new Role(2L, "ROLE_ADMIN")));
//		
////		when(modelMapper.map(inputUserDTO, org.mockito.ArgumentMatchers.any())).thenReturn(inputUser);
////		when(modelMapper.map(Mockito.any(UserDTO.class), Mockito.any(User.class))).thenReturn(inputUser);
////		when(modelMapper.map(new UserDTO(), User.class)).thenReturn(inputUser);
////		when(modelMapper.map(org.mockito.ArgumentMatchers.any(), org.mockito.ArgumentMatchers.any())).
////			thenReturn(inputUser);
//		
//		when(userRepository.findByUsername(Mockito.anyString())).thenReturn(null);
//		when(modelMapper.map(new UserDTO(), org.mockito.ArgumentMatchers.any())).thenReturn(mappedUser);
//		when(userRepository.save(mappedUser)).thenReturn(expectedUser);
//		
////		User savedUser = modelMapper.map(inputUserDTO, User.class);
//		
//		service.save(inputUserDTO);
//		
//		ArgumentCaptor<User> userArgumentCaptor =
//				ArgumentCaptor.forClass(User.class);
//		
//		verify(userRepository).save(userArgumentCaptor.capture());
//		verify(userRepository).save(inputUser);
//		
//		User capturedUser = userArgumentCaptor.getValue();
//		
//		assertNotNull(capturedUser);
//		assertEquals(10L, capturedUser.getId());
//		assertEquals("ROLE_ADMIN", capturedUser.getRoleName());
//		
//	}
	
	@Test
	void save_ShouldReturn_User() {
		
		UserDTO userDTO = new UserDTO(
				10L,
				"username",
				"password",
				"password",
				"firstname",
				"lastname",
				"email",
				"ROLE_ADMIN", 
				true, true, true, true); 
		
		User user = new User(
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
		
		when(modelMapper.map(userDTO, User.class)).thenReturn(user);
		when(modelMapper.map(user, UserDTO.class)).thenReturn(userDTO);
		when(userRepository.save(Mockito.any(User.class))).thenReturn(user);
		
		UserDTO savedUser = service.save(userDTO);
		
		verify(userRepository).save(Mockito.any(User.class));
		
		assertNotNull(savedUser);
		assertEquals(10L, savedUser.getId());
		assertEquals("ROLE_ADMIN", savedUser.getRole());
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
}
