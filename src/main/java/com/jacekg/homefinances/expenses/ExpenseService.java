package com.jacekg.homefinances.expenses;

import com.jacekg.homefinances.expenses.model.ConstantExpenseDTO;
import com.jacekg.homefinances.expenses.model.UserPreferenceConstantExpense;
import com.jacekg.homefinances.user.User;

public interface ExpenseService {
	
	void save(ConstantExpenseDTO constantExpenseDTO, User user);

	void delete(ConstantExpenseDTO constantExpenseDTO, User user);
}
