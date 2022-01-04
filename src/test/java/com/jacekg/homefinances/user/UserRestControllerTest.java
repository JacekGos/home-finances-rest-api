package com.jacekg.homefinances.user;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.web.context.WebApplicationContext;

@WebMvcTest(UserRestController.class)
@AutoConfigureMockMvc
class UserRestControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private UserService userService;
	
	@Test
	void addUser_ShouldReturn_UserDTO() throws Exception {
		
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
		
		when(userService.save(any(UserDTO.class))).thenReturn(userDTO);
		
		String url = "/signup";
		
		mockMvc.perform(get(url))
			.andExpect(status().isOk());
		
	}

}
