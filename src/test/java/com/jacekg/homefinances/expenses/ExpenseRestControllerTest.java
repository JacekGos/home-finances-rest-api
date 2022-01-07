package com.jacekg.homefinances.expenses;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.security.Principal;

import static org.mockito.Mockito.doNothing;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jacekg.homefinances.expenses.model.ConstantExpenseDTO;
import com.jacekg.homefinances.jwt.JwtAuthenticationEntryPoint;
import com.jacekg.homefinances.jwt.JwtRequestFilter;
import com.jacekg.homefinances.user.User;
import com.jacekg.homefinances.user.UserService;

@WebMvcTest(ExpenseRestController.class)
@AutoConfigureMockMvc(addFilters = false)
class ExpenseRestControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	ExpenseService expenseService;
	
	@MockBean
	UserService userService;
	
	@MockBean
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

	@MockBean
	private UserDetailsService jwtUserDetailsService;

	@MockBean
	private JwtRequestFilter jwtRequestFilter;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Mock
	Principal principal;
	
	@Test
	@WithMockUser(username = "user", roles = "USER")
	void addUserPreferenceConstantExpense_ShouldReturn_SatusCreated() throws Exception {
		
		User user = new User();
		TestingAuthenticationToken testingAuthenticationToken = new TestingAuthenticationToken(user,null);
		ConstantExpenseDTO constantExpenseDTO = new ConstantExpenseDTO(1L, "expense", 0, 0);
		
		doNothing().when(expenseService).addUserPreferenceConstantExpense(constantExpenseDTO, user);
		
		String jsonBody = objectMapper.writeValueAsString(constantExpenseDTO);
		
		String url = "/budget/expenses";
		
		mockMvc.perform(post(url)
				.contentType(MediaType.APPLICATION_JSON)
				.principal(testingAuthenticationToken)
                .content(jsonBody))
				.andExpect(status().isCreated());
	}

	@Test
	void testRemoveUserPreferenceConstantExpense() {
		fail("Not yet implemented");
	}

}
