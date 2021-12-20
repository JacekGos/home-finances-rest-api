package com.jacekg.homefinances.expenses;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jacekg.homefinances.expenses.model.IrregularExpense;

public interface IrregularExpenseRepository extends JpaRepository<IrregularExpense, Long> {
	
	@Query("FROM IrregularExpense ie WHERE irregular_expenses_budget_id=:irregularExpensesBudgetId")
	List<IrregularExpense> findAllByIrregularExpensesBudgetId
		(@Param(value = "irregularExpensesBudgetId") Long irregularExpensesBudgetId);
}