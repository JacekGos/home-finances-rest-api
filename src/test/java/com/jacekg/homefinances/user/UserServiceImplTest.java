package com.jacekg.homefinances.user;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.StringUtils;

import com.jacekg.homefinances.role.Role;
import com.jacekg.homefinances.role.RoleRepository;


@SpringBootTest
class UserServiceImplTest {

	@Mock
	private UserRepository userRepository;
	
	@Mock
	private RoleRepository roleRepository;
	
	@Mock
	private BCryptPasswordEncoder passwordEncoder;
	
	@InjectMocks
	UserServiceImpl service;

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
	void test() {
		
		UserDTO userDTO = new UserDTO();
		userDTO.setRole("USER");
		
		User user = new User();
		
		when(userRepository.save(user)).thenReturn(new User());
		
		User returnedUser = service.save(userDTO);
		
//		assertNotNull(returnedUser);
		assertEquals(1L, returnedUser.getId());
		
		verify(userRepository).save(user);
		
	}
}

















