package com.jacekg.homefinances.expenses;

import com.jacekg.homefinances.expenses.model.ConstantExpenseDTO;
import com.jacekg.homefinances.expenses.model.UserPreferenceConstantExpense;

public interface ExpenseService {
	
	void save(ConstantExpenseDTO constantExpenseDTO);
}
