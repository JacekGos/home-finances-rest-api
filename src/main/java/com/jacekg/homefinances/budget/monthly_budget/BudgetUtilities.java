package com.jacekg.homefinances.budget.monthly_budget;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.jacekg.homefinances.expenses.model.ConstantExpense;
import com.jacekg.homefinances.expenses.model.Expense;
import com.jacekg.homefinances.expenses.model.OneTimeExpense;

import net.bytebuddy.asm.Advice.This;

public class BudgetUtilities {
	
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
	
	public static <T extends Expense> List<Long> findExpensesIdsToRemove
		(Collection<T> currentExpenses, Collection<T> updatedExpenses) {
		
		List<Long> constantExpensesIdToRemove = new ArrayList<>();

		for (T expense : currentExpenses) {

			T searchedExpense = updatedExpenses.stream()
					.filter(updatedExpense -> expense.getName()
							.equals(updatedExpense.getName()))
					.findFirst().orElse(null);

			if (searchedExpense == null) {
				constantExpensesIdToRemove.add(expense.getId());
			}
		}

		return constantExpensesIdToRemove;
	}
	
	public static int calculateFinalBalance
		(List<ConstantExpense> currentExpenses, 
				List<OneTimeExpense> oneTimeExpenses, 
				int previousMonthEarnings) {
		
		return previousMonthEarnings - calculateExpenses(currentExpenses) + calculateExpenses(oneTimeExpenses);
	}
	
	private static <T extends Expense> int calculateExpenses(List<T> expenses) {
		
		int sumOfExpenses = 0;
		
		for (T expense : expenses) {
			sumOfExpenses += expense.getPlannedAmount();
		}
		
		return sumOfExpenses;
	}
}
