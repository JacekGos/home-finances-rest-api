package com.jacekg.homefinances.expenses;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IrregularExpenseRepository extends JpaRepository<IrregularExpense, Long> {

}
