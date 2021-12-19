package com.jacekg.homefinances.expenses;


import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jacekg.homefinances.expenses.model.UserPreferenceConstantExpense;

public interface UserPreferenceConstantExpenseRepository 
	extends JpaRepository<UserPreferenceConstantExpense, Long> {
	
	@Query("FROM UserPreferenceConstantExpense up WHERE user_id=:userId")
	Set<UserPreferenceConstantExpense> findAllByUserId(@Param("userId") Long userId);
}
