package com.jacekg.homefinances.user;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
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
	void save_WhenUsernameExists_ShouldThrow_UserNotValidException() {
		
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
		
		User user = new User();
		user.setUsername("username");
		
		when(userRepository.findByUsername("username")).thenReturn(user);
		
		assertThrows(UserNotValidException.class, () -> {
			service.save(userDTO);
		});
		
	}

	@Test
	void findByUsername_ShouldReturn_User() {
		
		String username = "username";
		
		service.findByUsername(username);
		
		verify(userRepository).findByUsername(username);
	}
	
	@Test
	void findByUserId_ShouldReturn_User() {
		
		Long userId = 1L;
		
		service.findByUserId(userId);
		
		verify(userRepository).findByUserId(userId);
	}
}
