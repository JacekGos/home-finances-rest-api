package com.jacekg.homefinances.monthly_budget;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IrregularExpensesBudgetRepository extends JpaRepository<IrregularExpensesBudget, Long> {

}
