package com.jacekg.homefinances.budget.irregular_expenses_budget;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import com.jacekg.homefinances.budget.monthly_budget.MonthlyBudgetRepository;
import com.jacekg.homefinances.budget.monthly_budget.MonthlyBudgetService;
import com.jacekg.homefinances.expenses.IrregularExpenseRepository;
import com.jacekg.homefinances.user.UserRepository;

@ExtendWith(MockitoExtension.class)
class IrregularExpensesBudgetServiceImplTest {
	
	@InjectMocks
	IrregularExpensesBudgetServiceImpl serviceUnderTest;
	
	@Mock
	private MonthlyBudgetService monthlyBudgetService;
	
	@Mock
	private UserRepository userRepository;
	
	@Mock
	private IrregularExpensesBudgetRepository irregularExpensesBudgetRepository;
	
	@Mock
	private IrregularExpenseRepository irregularExpenseRepository;
	
	@Mock
	private MonthlyBudgetRepository monthlyBudgetRepository;
	
	@Mock
	private ModelMapper modelMapper;
	
	@Test
	void findByUserIdAndDate_ShouldReturn_IrregularExpensesBudgetDTO() {
		
	}

	@Test
	void testSave() {
	
		
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
