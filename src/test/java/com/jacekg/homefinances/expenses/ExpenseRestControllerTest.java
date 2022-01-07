package com.jacekg.homefinances.expenses;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.doNothing;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.web.servlet.MockMvc;

import com.jacekg.homefinances.expenses.model.ConstantExpenseDTO;
import com.jacekg.homefinances.jwt.JwtAuthenticationEntryPoint;
import com.jacekg.homefinances.jwt.JwtRequestFilter;
import com.jacekg.homefinances.user.User;
import com.jacekg.homefinances.user.UserService;

@WebMvcTest(ExpenseRestController.class)
@AutoConfigureMockMvc
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
	
	@Test
	void addUserPreferenceConstantExpense_ShouldReturn_SatusCreated() throws Exception {
		
		User user = new User();
		ConstantExpenseDTO constantExpenseDTO = new ConstantExpenseDTO();
		
//		when(expenseService.addUserPreferenceConstantExpense
//				(any(ConstantExpenseDTO.class), any(User.class)))
		
		doNothing().when(expenseService).addUserPreferenceConstantExpense(constantExpenseDTO, user);
		
		String url = "/expenses";
		
		mockMvc.perform(post(url))
			.andExpect(status().isCreated());
	}

	@Test
	void testRemoveUserPreferenceConstantExpense() {
		fail("Not yet implemented");
	}

}
