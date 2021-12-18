package com.jacekg.homefinances.expenses;

import java.util.Set;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jacekg.homefinances.expenses.model.ConstantExpenseDTO;
import com.jacekg.homefinances.expenses.model.UserPreferenceConstantExpense;
import com.jacekg.homefinances.user.User;
import com.jacekg.homefinances.user.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ExpenseServiceImpl implements ExpenseService {

	UserPreferenceConstantExpenseRepository userPreferenceConstantExpenseRepository;
	
	UserRepository userRepository;

	@Override
	@Transactional
	public void save(ConstantExpenseDTO constantExpenseDTO, User user) {

		UserPreferenceConstantExpense userPreferenceConstantExpense 
			= new UserPreferenceConstantExpense(constantExpenseDTO.getName());
		
		Set<UserPreferenceConstantExpense> userPreferenceConstantExpenses 
			= user.getUserPreferenceConstantExpenses();
		
		if (ExpenseUtilities.isUserPreferenceConstantExpenseIsDuplicated
				(userPreferenceConstantExpenses, userPreferenceConstantExpense) == true) {
			user.setUserPreferenceConstantExpenses(Set.of(userPreferenceConstantExpense));
		} 
	}

}
