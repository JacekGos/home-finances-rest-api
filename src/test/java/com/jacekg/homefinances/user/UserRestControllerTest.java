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

}






