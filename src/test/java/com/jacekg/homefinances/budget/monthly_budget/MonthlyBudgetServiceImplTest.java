package com.jacekg.homefinances.budget.monthly_budget;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import com.jacekg.homefinances.budget.irregular_expenses_budget.IrregularExpensesBudgetRepository;
import com.jacekg.homefinances.expenses.ConstantExpenseRepository;
import com.jacekg.homefinances.expenses.OneTimeExpenseRepository;
import com.jacekg.homefinances.user.UserRepository;
import com.jacekg.homefinances.user.UserServiceImpl;

@ExtendWith(MockitoExtension.class)
class MonthlyBudgetServiceImplTest {
	
	@InjectMocks
	MonthlyBudgetServiceImpl service;
	
	@Mock
	private MonthlyBudgetRepository monthlyBudgetRepository;

	@Mock
	private UserRepository userRepository;
	
	@Mock
	private ConstantExpenseRepository constantExpenseRepository;
	
	@Mock
	private OneTimeExpenseRepository oneTimeExpenseRepository;
	
	@Mock
	private IrregularExpensesBudgetRepository irregularExpensesBudgetRepository;

	@Mock
	private ModelMapper modelMapper;
	
	@Test
	void findByUserIdAndDate_ShouldReturn_MonthlyBudget() {

		LocalDate date = LocalDate.now().withDayOfMonth(1);
		
		MonthlyBudgetDTO monthyBudgetDTO = new MonthlyBudgetDTO
				(1L, 1L, date, 0, 0, null, null);
		
		MonthlyBudget monthlyBudget = new MonthlyBudget
				(1L, date, 0, 0, null, null, null);
		
		when(monthlyBudgetRepository
				.findByUserIdAndDate(any(Long.class), any(LocalDate.class))).thenReturn(monthlyBudget);
		when(modelMapper.map(monthlyBudget, MonthlyBudgetDTO.class)).thenReturn(monthyBudgetDTO);
		
		MonthlyBudgetDTO returnedMonthlyBudgetDTO = service.findByUserIdAndDate(1L, date);
		
		verify(monthlyBudgetRepository).findByUserIdAndDate(1L, date);
		
		assertNotNull(returnedMonthlyBudgetDTO);
		assertEquals(1L, returnedMonthlyBudgetDTO.getId());
		assertEquals(date, returnedMonthlyBudgetDTO.getDate());
	}

	@Test
	void testSave() {
		fail("Not yet implemented");
	}

	@Test
	void testFindAllByUserId() {
		fail("Not yet implemented");
	}

	@Test
	void testUpdate() {
		fail("Not yet implemented");
	}

	@Test
	void testDeleteByDate() {
		fail("Not yet implemented");
	}

}
