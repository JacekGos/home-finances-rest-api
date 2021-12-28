package com.jacekg.homefinances.expenses;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.jacekg.homefinances.budget.irregular_expenses_budget.IrregularExpensesBudget;
import com.jacekg.homefinances.budget.irregular_expenses_budget.IrregularExpensesBudgetRepository;
import com.jacekg.homefinances.expenses.model.IrregularExpense;

@DataJpaTest
class IrregularExpenseRepositoryTest {

	@Autowired
	IrregularExpenseRepository irregularExpenseRepository;
	
	@Autowired
	IrregularExpensesBudgetRepository irregularExpensesBudgetRepository;
	
	@Test
	void findAllByIrregularExpensesBudgetId_ShouldReturn_IrregularExpensesList() {

		Long irregularExpensesBudgetId = 1L;

		IrregularExpense irregularExpense = new IrregularExpense("irregular expense", 0, 0);

		IrregularExpensesBudget irregularExpensesBudget = new IrregularExpensesBudget
				(1L, LocalDate.now(), 0, 0, null, List.of(irregularExpense));

		irregularExpensesBudgetRepository.save(irregularExpensesBudget);

		List<IrregularExpense> irregularExpenses = irregularExpenseRepository.findAllByIrregularExpensesBudgetId(irregularExpensesBudgetId);

		assertTrue(irregularExpenses.size() > 0);

	}
}
