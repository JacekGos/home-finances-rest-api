package com.jacekg.homefinances.budget.monthly_budget;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.mockito.Mockito.any;

import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jacekg.homefinances.jwt.JwtAuthenticationEntryPoint;
import com.jacekg.homefinances.jwt.JwtRequestFilter;
import com.jacekg.homefinances.user.User;
import com.jacekg.homefinances.user.UserService;

@WebMvcTest(MonthlyBudgetRestController.class)
@AutoConfigureMockMvc(addFilters = false)
class MonthlyBudgetRestControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private MonthlyBudgetService monthlyBudgetService;
	
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
	
	@Mock
	Principal principal;
	
	@Test
	void addMonthlyBudget_ShouldReturn_StatusCreated() throws Exception {
		
		LocalDate date = LocalDate.now().withDayOfMonth(1);
		
		User user = new User();
		user.setId(1L);
		TestingAuthenticationToken testingAuthenticationToken = new TestingAuthenticationToken(user,null);
		
		MonthlyBudgetDTO monthlyBudgetDTO = new MonthlyBudgetDTO
				(1L, 1L, date, 0, 0, null, null);
		
		when(userService.findByUsername(any(String.class))).thenReturn(user);
		when(monthlyBudgetService.save(monthlyBudgetDTO)).thenReturn(monthlyBudgetDTO);
		
		String jsonBody = objectMapper.writeValueAsString(monthlyBudgetDTO);
		
		String url = "/budget/monthly-budgets";
		
		mockMvc.perform(post(url)
				.contentType(MediaType.APPLICATION_JSON)
				.principal(testingAuthenticationToken)
                .content(jsonBody))
				.andExpect(status().isCreated());
	}
	
	@Test
	void getAllMonthlyBudgets_ShouldReturn_StatusOk() throws Exception {
		
		User user = new User();
		TestingAuthenticationToken testingAuthenticationToken = new TestingAuthenticationToken(user,null);
		
		List<MonthlyBudgetDTO> monthlyBudgetDTOs = new ArrayList<>();
		monthlyBudgetDTOs.add(new MonthlyBudgetDTO());
		
		when(userService.findByUsername(any(String.class))).thenReturn(user);
		when(monthlyBudgetService.findAllByUserId(any(Long.class))).thenReturn(monthlyBudgetDTOs);
		
		String url = "/budget/monthly-budgets";
		
		mockMvc.perform(get(url)
				.principal(testingAuthenticationToken))
				.andExpect(status().isOk());
	}
	
	@Test
	void testUpdateMonthlyBudget() {
		fail("Not yet implemented");
	}

	@Test
	void testDeleteMonthlyBudget() {
		fail("Not yet implemented");
	}

}
