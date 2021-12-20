package com.jacekg.homefinances.budget.monthly_budget;

import java.time.LocalDate;
import java.util.List;

public interface MonthlyBudgetService {
	
	MonthlyBudgetDTO findByUserIdAndDate(Long userId, LocalDate date);
	
	MonthlyBudgetDTO save(MonthlyBudgetDTO monthlyBudgetDTO);

	List<MonthlyBudgetDTO> findAllByUserId(Long userId);
	
	MonthlyBudgetDTO update(MonthlyBudgetDTO monthlyBudgetDTO);
	
	void deleteByDate(LocalDate localDate, Long userId);
}
