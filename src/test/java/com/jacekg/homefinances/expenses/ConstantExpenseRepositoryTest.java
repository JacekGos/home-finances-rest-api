package com.jacekg.homefinances.expenses;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.jacekg.homefinances.budget.monthly_budget.MonthlyBudget;
import com.jacekg.homefinances.budget.monthly_budget.MonthlyBudgetRepository;
import com.jacekg.homefinances.expenses.model.ConstantExpense;

@DataJpaTest
class ConstantExpenseRepositoryTest {
	
	@Autowired
	ConstantExpenseRepository constantExpenseRepository;
	
	@Autowired
	MonthlyBudgetRepository monthlyBudgetRepository;
	
	@Test
	void findAllByMonthlyBudgetId_ShouldReturn_ConstantExpenseList() {
	
		Long monthlyBudgetId = 1L;
		
		ConstantExpense constantExpense = new ConstantExpense("constant expense", 0, 0);
		
		MonthlyBudget monthlyBudget = new MonthlyBudget
				(1L, LocalDate.now(), 0, 0, null, List.of(constantExpense), null);
		
		monthlyBudgetRepository.save(monthlyBudget);
		
		List<ConstantExpense> constantExpenses = constantExpenseRepository.findAllByMonthlyBudgetId(monthlyBudgetId);
		
		assertTrue(constantExpenses.size() > 0);
	}

}
