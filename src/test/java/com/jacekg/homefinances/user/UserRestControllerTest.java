package com.jacekg.homefinances.user;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRestControllerTest {
	
	@Mock
	UserService userService;
	
	@InjectMocks
	UserRestController controller;
	
	@Test
	void addUser_ShouldReturn_ValidUser() {
		
		UserDTO inputUser = new UserDTO();
		User outputUser = new User();
		outputUser.setId(10L);
		
		when(userService.save(inputUser)).thenReturn(outputUser);
		User returnedUser = controller.addUser(inputUser);
		
		assertNotNull(returnedUser);
		assertNotNull(returnedUser.getId());
		assertEquals(10L, returnedUser.getId());
		
		verify(userService).save(inputUser);
	}
	
	@Test
	void addUser_ShouldThrow_UserNotValidException() {
		
		UserDTO user = new UserDTO();
		user.setUsername("user");
		
		when(userService.findByUsername(user.getUsername())).thenThrow(UserNotValidException.class);
		
		assertThrows(UserNotValidException.class, () -> controller.addUser(user));
		
		verify(userService).findByUsername(user.getUsername());
	}
	
	@Test
	void findAll_ShouldReturn_UsersList() {
		
		List<User> userList = new ArrayList<>();
		userList.add(new User());
		
		when(userService.findAll()).thenReturn(userList);
		
		List<User> returnedUserList = controller.findAll();
		
		assertNotNull(returnedUserList);
		
		verify(userService).findAll();
		
	}

}


























