package com.jacekg.homefinances.budget;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MonthlyBudgerRepository extends JpaRepository<MonthlyBudget, Long> {
	
}
