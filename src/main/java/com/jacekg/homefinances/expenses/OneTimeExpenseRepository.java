package com.jacekg.homefinances.expenses;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jacekg.homefinances.expenses.model.OneTimeExpense;

public interface OneTimeExpenseRepository extends JpaRepository<OneTimeExpense, Long> {
	
	@Query("FROM OneTimeExpense ote WHERE monthly_budget_id=:monthlyBudgetId")
	List<OneTimeExpense> findAllByMonthlyBudgetId
		(@Param(value = "monthlyBudgetId") Long monthlyBudgetId);
}
