package com.jacekg.homefinances.expenses;


import org.springframework.data.jpa.repository.JpaRepository;

import com.jacekg.homefinances.expenses.model.UserPreferenceConstantExpense;

public interface UserPreferenceConstantExpenseRepository 
	extends JpaRepository<UserPreferenceConstantExpense, Long> {
}
