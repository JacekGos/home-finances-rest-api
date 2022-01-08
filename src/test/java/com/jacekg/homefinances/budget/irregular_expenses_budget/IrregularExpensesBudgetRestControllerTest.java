package com.jacekg.homefinances.budget.irregular_expenses_budget;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jacekg.homefinances.budget.monthly_budget.MonthlyBudgetDTO;
import com.jacekg.homefinances.budget.monthly_budget.MonthlyBudgetRestController;
import com.jacekg.homefinances.jwt.JwtAuthenticationEntryPoint;
import com.jacekg.homefinances.jwt.JwtRequestFilter;
import com.jacekg.homefinances.user.User;
import com.jacekg.homefinances.user.UserService;

@WebMvcTest(IrregularExpensesBudgetRestController.class)
@AutoConfigureMockMvc(addFilters = false)
class IrregularExpensesBudgetRestControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	UserService userService;
	
	@MockBean
	IrregularExpensesBudgetService irregularExpensesBudgetService;
	
	@MockBean
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

	@MockBean
	private UserDetailsService jwtUserDetailsService;

	@MockBean
	private JwtRequestFilter jwtRequestFilter;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Test
	void addIrreguarExpenseBudget_ShouldReturn_StatusCreated() throws Exception {
		
		LocalDate date = LocalDate.now().withDayOfMonth(1);
		
		User user = new User();
		user.setId(1L);
		TestingAuthenticationToken testingAuthenticationToken = new TestingAuthenticationToken(user,null);
		
		IrregularExpensesBudgetDTO irregularExpensesBudgetDTO = new IrregularExpensesBudgetDTO
				(1L, 1L, date, 0, 0, null);
		
		when(userService.findByUsername(any(String.class))).thenReturn(user);
		when(irregularExpensesBudgetService.save(irregularExpensesBudgetDTO)).thenReturn(irregularExpensesBudgetDTO);
		
		String jsonBody = objectMapper.writeValueAsString(irregularExpensesBudgetDTO);
		
		String url = "/budget/irregular-exepnses-budgets";
		
		mockMvc.perform(post(url)
				.contentType(MediaType.APPLICATION_JSON)
				.principal(testingAuthenticationToken)
                .content(jsonBody))
				.andExpect(status().isCreated());
	}

	@Test
	void getAllIrregularExpensesBudgets_ShouldReturn_StatusOk() throws Exception {
		
		User user = new User();
		TestingAuthenticationToken testingAuthenticationToken = new TestingAuthenticationToken(user,null);
		
		List<IrregularExpensesBudgetDTO> irregularExpensesBudgetDTOs = new ArrayList<>();
		irregularExpensesBudgetDTOs.add(new IrregularExpensesBudgetDTO());
		
		when(userService.findByUsername(any(String.class))).thenReturn(user);
		when(irregularExpensesBudgetService.findAllByUserId(any(Long.class))).thenReturn(irregularExpensesBudgetDTOs);
		
		String url = "/budget/irregular-exepnses-budgets";
		
		mockMvc.perform(get(url)
				.principal(testingAuthenticationToken))
				.andExpect(status().isOk());
	}

	@Test
	void updateIrregularExpensesBudget_ShouldReturn_StatusOk() throws Exception {
		
		LocalDate date = LocalDate.now().withDayOfMonth(1);
		
		User user = new User();
		TestingAuthenticationToken testingAuthenticationToken = new TestingAuthenticationToken(user,null);
		
		IrregularExpensesBudgetDTO irregularExpensesBudgetDTO = new IrregularExpensesBudgetDTO
				(1L, 1L, date, 0, 0, null);
		
		when(irregularExpensesBudgetService.update(any(IrregularExpensesBudgetDTO.class)))
			.thenReturn(irregularExpensesBudgetDTO);
		
		String jsonBody = objectMapper.writeValueAsString(irregularExpensesBudgetDTO);
		
		String url = "/budget/irregular-exepnses-budgets";
		
		mockMvc.perform(put(url)
				.contentType(MediaType.APPLICATION_JSON)
				.principal(testingAuthenticationToken)
                .content(jsonBody))
				.andExpect(status().isOk());
	}

	@Test
	void testDeleteMonthlyBudget() {
		fail("Not yet implemented");
	}

}
