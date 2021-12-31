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
	public void addUserPreferenceConstantExpense(ConstantExpenseDTO constantExpenseDTO, User user) {

		UserPreferenceConstantExpense userPreferenceConstantExpense 
			= new UserPreferenceConstantExpense(constantExpenseDTO.getName());

		Set<UserPreferenceConstantExpense> userPreferenceConstantExpenses 
			= userPreferenceConstantExpenseRepository.findAllByUserId(user.getId());
		
		if (ExpenseUtilities.isUserPreferenceConstantExpenseDuplicated
				(userPreferenceConstantExpenses, userPreferenceConstantExpense) != true) {
			
			userPreferenceConstantExpenses.add(userPreferenceConstantExpense);
			user.setUserPreferenceConstantExpenses(userPreferenceConstantExpenses);
			
			userRepository.save(user);
		} 
	}

	@Override
	@Transactional
	public void removeUserPreferenceConstantExpense(ConstantExpenseDTO constantExpenseDTO, User user) {

		Set<UserPreferenceConstantExpense> userPreferenceConstantExpenses 
			= userPreferenceConstantExpenseRepository.findAllByUserId(user.getId());
		
		UserPreferenceConstantExpense userPreferenceConstantExpense 
			= new UserPreferenceConstantExpense(constantExpenseDTO.getName());
		
		userPreferenceConstantExpenses
			.removeIf(item -> (item.getName().equals(userPreferenceConstantExpense.getName())));
		
		user.setUserPreferenceConstantExpenses(userPreferenceConstantExpenses);
		
		userRepository.save(user);
	}
}
