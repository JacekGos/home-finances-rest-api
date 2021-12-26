package com.jacekg.homefinances.budget;

import java.util.List;

import com.jacekg.homefinances.expenses.model.ConstantExpense;
import com.jacekg.homefinances.expenses.model.Expense;
import com.jacekg.homefinances.expenses.model.OneTimeExpense;

public class BudgetUtilities {
	
	public static <T extends Expense> List<T> removeDuplicatedExpenses(List<T> currentExpenses,
			List<T> updatedExpenses) {
		
		T currentExpense;
		T updatedExpense;
		
		System.out.println("remove before: " + updatedExpenses);
		
		updatedExpenses = BudgetUtilities.removeDuplicatesFromUpdatedExpenses(updatedExpenses);
		
		System.out.println("remove after: " + updatedExpenses);
		
		for (int i = 0; i < updatedExpenses.size(); i++) {

			updatedExpense = updatedExpenses.get(i);
			
			for (int j = 0; j < currentExpenses.size(); j++) {

				currentExpense = currentExpenses.get(j);
				
				if ((updatedExpense.getId() == null 
						|| !(updatedExpense.getId().equals((currentExpense.getId()))))
						&& (updatedExpense.getName().equals(currentExpense.getName()))) {
					updatedExpenses.remove(i);
				}
			}
		}

		return updatedExpenses;
	}
	
	public static <T extends Expense> List<T> removeDuplicatesFromUpdatedExpenses(List<T> updatedExpenses) {
		
		T updatedExpense;
		T expenseToCheck;
		
		if (updatedExpenses.size() > 1) {

			for (int i = 0; i < updatedExpenses.size(); i++) {

				updatedExpense = updatedExpenses.get(i);
				System.out.println("removing: updated: " + updatedExpense);
				if (updatedExpense.getId() == null) {

					for (int j = i + 1; j < updatedExpenses.size(); j++) {

						expenseToCheck = updatedExpenses.get(j);
						System.out.println("removing: toCheck: " + expenseToCheck);

						if (expenseToCheck.getId() == null
								&& expenseToCheck.getName().equals(updatedExpense.getName())) {
							updatedExpenses.remove(j);
						}
					}
				}
			}
		}

		return updatedExpenses;
	}
	
	public static List<ConstantExpense> manageIrregularConstantExpense
		(List<ConstantExpense> updatedConstantExpenses, List<ConstantExpense> currentConstantExpenses) {
		
		ConstantExpense irregularUpdatedExpense = updatedConstantExpenses
				.stream()
				.filter(expense -> expense.getName().equals("wydatki nieregularne"))
				.findFirst()
				.orElse(null);
		
		ConstantExpense irregularCurrentExpense = currentConstantExpenses
				.stream()
				.filter(expense -> expense.getName().equals("wydatki nieregularne"))
				.findFirst()
				.orElse(null);
		
		if (irregularCurrentExpense != null &&  irregularUpdatedExpense == null) {
			
			updatedConstantExpenses.add(irregularCurrentExpense);
		} else if (irregularCurrentExpense != null && irregularUpdatedExpense != null) {
			
			updatedConstantExpenses.remove(irregularUpdatedExpense);
			updatedConstantExpenses.add(irregularCurrentExpense);
		}  else if (irregularCurrentExpense == null && irregularUpdatedExpense != null) {
			
			updatedConstantExpenses.remove(irregularUpdatedExpense);
		}
		
		return updatedConstantExpenses;
	}
	
	public static int calculateFinalBalance
		(List<ConstantExpense> currentExpenses, 
				List<OneTimeExpense> oneTimeExpenses, 
				int previousMonthEarnings) {
		
		System.out.println("calculate: " + currentExpenses);
		System.out.println("calculate: " + oneTimeExpenses);
		System.out.println("earnings: " + previousMonthEarnings);
		
		return previousMonthEarnings - (calculateExpenses(currentExpenses) + calculateExpenses(oneTimeExpenses));
	}
	
	public static <T extends Expense> int calculateExpenses(List<T> expenses) {
		
		int sumOfExpenses = 0;
		
		for (T expense : expenses) {
			System.out.println("expense: " + expense.getName() + " " + expense.getPlannedAmount());
			sumOfExpenses += expense.getPlannedAmount();
		}
		
		return sumOfExpenses;
	}
	
	public static int calculateNecessaryMonthlySavings(int annualExpensesSum) {
		
		if (annualExpensesSum >= 12) {
			return annualExpensesSum / 12;
		}
		
		return 0;
	}
}






