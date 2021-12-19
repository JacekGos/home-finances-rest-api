package com.jacekg.homefinances.monthly_budget;

import java.time.LocalDate;
import java.util.List;

public interface BudgetService {
	
	MonthlyBudgetDTO findByUserIdAndDate(Long userId, LocalDate date);
	
	MonthlyBudgetDTO save(MonthlyBudgetDTO monthlyBudgetDTO);

	List<MonthlyBudgetDTO> findAllByUserId(Long userId);
	
	MonthlyBudgetDTO update(MonthlyBudgetDTO monthlyBudgetDTO);
	
	void deleteByDate(LocalDate localDate, Long userId);
}
