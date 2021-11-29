package com.jacekg.homefinances.expenses;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OneTimeExpenseRepository extends JpaRepository<OneTimeExpense, Long> {

}
