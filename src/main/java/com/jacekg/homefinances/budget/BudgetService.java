package com.jacekg.homefinances.budget;

import java.time.LocalDate;

public interface BudgetService {
	
	MonthlyBudget findByUserIdAndDate(Long userId, LocalDate date);
}
