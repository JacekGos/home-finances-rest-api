package com.jacekg.homefinances.expenses;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.jacekg.homefinances.budget.monthly_budget.MonthlyBudget;
import com.jacekg.homefinances.budget.monthly_budget.MonthlyBudgetRepository;
import com.jacekg.homefinances.expenses.model.OneTimeExpense;

@DataJpaTest
class OneTimeExpenseRepositoryTest {

	@Autowired
	OneTimeExpenseRepository oneTimeExpenseRepository;

	@Autowired
	MonthlyBudgetRepository monthlyBudgetRepository;

	@Test
	void findAllByMonthlyBudgetId_ShouldReturn_OneTimeExpensesList() {

		Long monthlyBudgetId = 1L;

		OneTimeExpense oneTimeExpense = new OneTimeExpense("constant expense", 0, 0);

		MonthlyBudget monthlyBudget = new MonthlyBudget
					(1L, LocalDate.now(), 0, 0, null, null, List.of(oneTimeExpense));

		monthlyBudgetRepository.save(monthlyBudget);

		List<OneTimeExpense> oneTimeExpenses = oneTimeExpenseRepository.findAllByMonthlyBudgetId(monthlyBudgetId);

		assertTrue(oneTimeExpenses.size() > 0);
	}
}
