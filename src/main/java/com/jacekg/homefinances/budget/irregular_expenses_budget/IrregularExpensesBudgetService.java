package com.jacekg.homefinances.budget.irregular_expenses_budget;

import java.time.LocalDate;
import java.util.List;

public interface IrregularExpensesBudgetService {

	IrregularExpensesBudgetDTO save(IrregularExpensesBudgetDTO irregularExpensesBudgetDTO);

	IrregularExpensesBudgetDTO findByUserIdAndDate(Long userId, LocalDate date);

	List<IrregularExpensesBudgetDTO> findAllByUserId(Long userId);

	IrregularExpensesBudgetDTO update(IrregularExpensesBudgetDTO irregularExpensesBudgetDTO);

	void deleteByDate(LocalDate localDate, Long userId);
}
