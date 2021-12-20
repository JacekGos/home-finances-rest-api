package com.jacekg.homefinances.budget;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.jacekg.homefinances.budget.monthly_budget.MonthlyBudgetDTO;
import com.jacekg.homefinances.budget.monthly_budget.MonthlyBudgetRepository;
import com.jacekg.homefinances.budget.monthly_budget.MonthlyBudgetServiceImpl;
import com.jacekg.homefinances.user.UserRepository;

@ExtendWith(MockitoExtension.class)
class BudgetServiceImplTest {
	
	@InjectMocks
	MonthlyBudgetServiceImpl budgetServiceImpl; 
	
	@Mock
	private UserRepository userRepository;
	
	@Mock
	private MonthlyBudgetRepository monthlyBudgetRepository;
	
	
	
	@Test
	void save_ShouldReturn_MonthlyBudgetDTO() {
		
		MonthlyBudgetDTO monthlyBudgetDTO = new MonthlyBudgetDTO();
		monthlyBudgetDTO.setDate(LocalDate.now());
		monthlyBudgetDTO.setUserId(1L);
		
		budgetServiceImpl.save(null);
	}

}










