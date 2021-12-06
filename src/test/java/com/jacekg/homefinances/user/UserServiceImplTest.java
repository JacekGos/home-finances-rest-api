package com.jacekg.homefinances.user;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.matchers.Any;
import org.mockito.junit.jupiter.MockitoSettings;
import org.modelmapper.ModelMapper;
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
	
	@Mock
	private ModelMapper modelMapper;
	
	@Test
	void save_ShouldReturn_UserWithAdminRole() {
		
		UserDTO userDTO = new UserDTO();
		userDTO.setRole("USER");

		Collection<Role> roles = new ArrayList<Role>();
		roles.add(new Role(1L, "ROLE_USER"));
//		roles.add(new Role(2L, "ROLE_ADMIN"));
		
		User user = new User();
		user.setRoles(roles);
		
		when(roleRepository.findByName(userDTO.getRole())).thenReturn(new Role(1L, "ROLE_USER"));
		when(userRepository.save(user)).thenReturn(user);
		
		UserDTO returnedUser = service.save(userDTO);
		
		assertEquals("ROLE_USER", returnedUser.getRole());
		
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
		
		UserDTO outputUser = service.save(inputUser);
		
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
	
	@Test
	void convertToDTO_ShouldReturn_UserDTO() {
		
		User inputUser = new User();
		UserDTO outputUser = new UserDTO();
		
		inputUser.setId(10L);
		inputUser.setUsername("username");
		inputUser.setPassword("password");
		inputUser.setFirstName("name");
		inputUser.setLastName("lastname");
		inputUser.setEmail("email");
		inputUser.setRoles(Arrays.asList(
					roleRepository.findByName("ROLE_USER"),
					roleRepository.findByName("ROLE_ADMIN")));
		
		when(modelMapper.map(inputUser, UserDTO.class)).thenReturn(outputUser);
		
		UserDTO returnedUser = modelMapper.map(inputUser, UserDTO.class);
		
		assertNotNull(returnedUser);
		assertEquals(10L, returnedUser.getId());
		assertEquals("username", returnedUser.getUsername());
		assertEquals("password", returnedUser.getPassword());
		assertEquals("name", returnedUser.getFirstName());
		assertEquals("lastname", returnedUser.getLastName());
		assertEquals("email", returnedUser.getEmail());
		assertEquals("ROLE_ADMIN", returnedUser.getRole());

		verify(modelMapper).map(inputUser, UserDTO.class);
	}
}

















