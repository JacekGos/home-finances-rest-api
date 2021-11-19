package com.jacekg.homefinances.user;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserRestControllerTest {
	
	@Mock
	UserService userService;
	
	@InjectMocks
	UserRestController controller;
	
	@Test
	void addUser_ShouldReturn_ValidUser() {
		
		UserDTO user = new UserDTO();
		User addedUser = new User();
		addedUser.setId(10L);
		
		when(userService.save(user)).thenReturn(addedUser);
		UserDTO outputUser = controller.addUser(user);
		
		assertNotNull(outputUser);
		assertNotNull(outputUser.getId());
		assertEquals(10L, outputUser.getId());
		
		verify(userService).save(user);
	}

}






