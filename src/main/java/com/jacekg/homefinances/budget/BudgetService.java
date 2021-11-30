package com.jacekg.homefinances.budget;

import java.time.LocalDate;

public interface BudgetService {
	
	MonthlyBudget findByUserAndDate(Long userId, LocalDate date);
}
