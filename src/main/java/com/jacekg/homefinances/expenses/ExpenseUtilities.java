package com.jacekg.homefinances.expenses;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import com.jacekg.homefinances.expenses.model.Expense;
import com.jacekg.homefinances.expenses.model.UserPreferenceConstantExpense;

public class ExpenseUtilities {
	
	public static boolean isUserPreferenceConstantExpenseIsDuplicated
		(Set<UserPreferenceConstantExpense> userPreferenceConstantExpenses
				, UserPreferenceConstantExpense userPreferenceConstantExpense) {
		
		UserPreferenceConstantExpense searchedUserPreferenceConstantExpense
			= userPreferenceConstantExpenses.stream()
				.filter(constantExpense -> userPreferenceConstantExpense.getName()
						.equals(constantExpense.getName()))
				.findFirst().orElse(null);

		return searchedUserPreferenceConstantExpense != null ? true : false;
	}
	
	public static <T extends Expense> List<T> removeDuplicatedExpenses(List<T> currentExpenses,
			List<T> updatedExpenses) {
		
		T currentExpense;
		T updatedExpense;

		for (int i = 0; i < updatedExpenses.size(); i++) {

			updatedExpense = updatedExpenses.get(i);
			
			for (int j = 0; j < currentExpenses.size(); j++) {

				currentExpense = currentExpenses.get(j);
				
				if ((updatedExpense.getId() == null 
						|| !(updatedExpense.getId().equals((currentExpense.getId()))))
						&& (updatedExpense.getName().equals(currentExpense.getName()))) {
					System.out.println("removed:" + updatedExpenses.get(i));
					updatedExpenses.remove(i);
				}
			}
		}

		return updatedExpenses;
	}
	
	public static <T extends Expense> List<Long> findExpensesIdsToRemove(
			Collection<T> currentExpenses, Collection<T> updatedExpenses) {
		
		List<Long> constantExpensesIdToRemove = new ArrayList<>();

		for (T expense : currentExpenses) {

			T searchedExpense = updatedExpenses.stream()
					.filter(updatedExpense -> expense.getName()
							.equals(updatedExpense.getName()))
					.findFirst().orElse(null);

			if (searchedExpense == null) {
				constantExpensesIdToRemove.add( expense.getId());
			}
		}

		return constantExpensesIdToRemove;
	}
	
}
