package com.jacekg.homefinances.irregular_expenses_budget;

import java.time.LocalDate;

public interface IrregularExpensesBudgetService {

	IrregularExpensesBudgetDTO save(IrregularExpensesBudgetDTO irregularExpensesBudgetDTO);

	IrregularExpensesBudgetDTO findByUserIdAndDate(Long userId, LocalDate date);
}
