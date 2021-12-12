package com.jacekg.homefinances.expenses;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jacekg.homefinances.expenses.model.OneTimeExpense;

public interface OneTimeExpenseRepository extends JpaRepository<OneTimeExpense, Long> {

}
