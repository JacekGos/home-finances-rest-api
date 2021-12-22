package com.jacekg.homefinances.budget;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.jacekg.homefinances.expenses.model.ConstantExpense;
import com.jacekg.homefinances.expenses.model.Expense;
import com.jacekg.homefinances.expenses.model.IrregularExpense;
import com.jacekg.homefinances.expenses.model.OneTimeExpense;

import net.bytebuddy.asm.Advice.This;

public class BudgetUtilities {
	
	public static <T extends Expense> List<T> removeDuplicatedExpenses(List<T> currentExpenses,
			List<T> updatedExpenses) {
		
		T currentExpense;
		T updatedExpense;
		
		System.out.println("current " + currentExpenses);
		System.out.println("updated " + updatedExpenses);
		
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






