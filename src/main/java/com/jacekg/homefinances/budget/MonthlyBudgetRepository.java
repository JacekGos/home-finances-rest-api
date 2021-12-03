package com.jacekg.homefinances.budget;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MonthlyBudgetRepository extends JpaRepository<MonthlyBudget, Long> {
	
	MonthlyBudget findByUserIdAndDate(Long userId, LocalDate date);
	
	MonthlyBudget save(MonthlyBudget monthlyBudget);
}
