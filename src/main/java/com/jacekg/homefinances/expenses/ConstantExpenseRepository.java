package com.jacekg.homefinances.expenses;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jacekg.homefinances.expenses.model.ConstantExpense;

public interface ConstantExpenseRepository extends JpaRepository<ConstantExpense, Long> {
	
//	@Query("FROM ConstantExpense ce WHERE monthly_budget_id=:monthlyBudgetId")
//	List<ConstantExpense> findAllByMonthlyBudgetId
//		(@Param(value = "monthlyBudgetId") Long monthlyBudgetId);
	
//	@Query("FROM ConstantExpense ce WHERE monthly_budget_id=:monthlyBudgetId")
	List<ConstantExpense> findAllByMonthlyBudgetId
		(Long monthlyBudgetId);
	
	Void deleteByConstantExpenseId(Long constantExpenseId);
}
