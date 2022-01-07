package com.jacekg.homefinances.user;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jacekg.homefinances.jwt.JwtAuthenticationEntryPoint;
import com.jacekg.homefinances.jwt.JwtRequestFilter;

@WebMvcTest(UserRestController.class)
@AutoConfigureMockMvc(addFilters = false)
class UserRestControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private UserService userService;
	
	@MockBean
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

	@MockBean
	private UserDetailsService jwtUserDetailsService;

	@MockBean
	private JwtRequestFilter jwtRequestFilter;
	
	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void addUser_ShouldReturn_StatusCreated() throws Exception {
		
		UserDTO userDTO = new UserDTO(
				10L,
				"username",
				"password",
				"password",
				"firstname",
				"lastname",
				"email@email.com",
				"ROLE_ADMIN", 
				true, true, true, true);

        String jsonBody = objectMapper.writeValueAsString(userDTO);
		
		when(userService.save(any(UserDTO.class))).thenReturn(userDTO);
		
		String url = "/signup";
		
		mockMvc.perform(post(url)
				.contentType(MediaType.APPLICATION_JSON)
                .content(jsonBody))
				.andExpect(status().isCreated());
		
	}

}