package com.jacekg.homefinances.irregular_expenses_budget;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IrregularExpensesBudgetRepository extends JpaRepository<IrregularExpensesBudget, Long> {

	@Query("FROM IrregularExpensesBudget ieb WHERE user_id=:userId AND date=:date")
	IrregularExpensesBudget findByUserIdAndDate
		(@Param("userId") Long userId, @Param("date") LocalDate date);

}
