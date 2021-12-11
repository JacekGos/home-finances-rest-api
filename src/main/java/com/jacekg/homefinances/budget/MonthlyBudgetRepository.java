package com.jacekg.homefinances.budget;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MonthlyBudgetRepository extends JpaRepository<MonthlyBudget, Long> {
	
	@Query("FROM MonthlyBudget mb WHERE user_id=:userId AND date=:date")
	MonthlyBudget findByUserIdAndDate(@Param("userId") Long userId, @Param("date") LocalDate date);
	
	MonthlyBudget save(MonthlyBudget monthlyBudget);
	
	List<MonthlyBudget> findAllByUserId(Long userId);
}
