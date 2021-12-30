package com.jacekg.homefinances.budget.irregular_expenses_budget;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.jacekg.homefinances.user.User;

@DataJpaTest
class IrregularExpensesBudgetRepositoryTest {
	
	@Autowired
	IrregularExpensesBudgetRepository irregularExpensesBudgetRepository;
	
	@Test
	void findByUserIdAndDate_ShouldReturnIrregularExpensesBudget() {

		Long userId = 1L;

		User user = new User();
		user.setId(1L);
		
		LocalDate date = LocalDate.now().withDayOfMonth(1);
		
		IrregularExpensesBudget irregularExpensesBudget 
			= new IrregularExpensesBudget(1L, date, 0, 0, user, null);
		
		irregularExpensesBudgetRepository.save(irregularExpensesBudget);
		
		IrregularExpensesBudget returnedBudget = irregularExpensesBudgetRepository.findByUserIdAndDate(userId, date);
		
		assertEquals(1L, returnedBudget.getId());
		assertEquals(1L, returnedBudget.getUser().getId());
		assertEquals(date, returnedBudget.getDate());
	}
}
