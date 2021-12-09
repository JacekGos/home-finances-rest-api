package com.jacekg.homefinances.budget;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MonthlyBudgetRepository extends JpaRepository<MonthlyBudget, Long> {
	
	MonthlyBudget findByUserIdAndDate(Long userId, LocalDate date);
	
	MonthlyBudget save(MonthlyBudget monthlyBudget);
	
	List<MonthlyBudget> findAllByUserId(Long userId);
}
