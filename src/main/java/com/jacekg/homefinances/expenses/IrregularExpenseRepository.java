package com.jacekg.homefinances.expenses;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jacekg.homefinances.expenses.model.IrregularExpense;

public interface IrregularExpenseRepository extends JpaRepository<IrregularExpense, Long> {

}
