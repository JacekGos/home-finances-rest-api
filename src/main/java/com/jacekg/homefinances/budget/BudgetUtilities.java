package com.jacekg.homefinances.budget;

import java.util.List;

import com.jacekg.homefinances.expenses.model.ConstantExpense;

public class BudgetUtilities {

	public static List<ConstantExpense> removeDuplicatedConstantExpenses(List<ConstantExpense> currentConstantExpenses,
			List<ConstantExpense> updatedConstantExpenses) {

		ConstantExpense currentConstantExpense;
		ConstantExpense updatedConstantExpense;

		for (int i = 0; i < updatedConstantExpenses.size(); i++) {

			updatedConstantExpense = updatedConstantExpenses.get(i);

			for (int j = 0; j < currentConstantExpenses.size(); j++) {

				currentConstantExpense = currentConstantExpenses.get(j);

				if (updatedConstantExpense.getId() != (currentConstantExpense.getId())
						&& updatedConstantExpense.getName().equals(currentConstantExpense.getName())) {
					updatedConstantExpenses.remove(i);
				}
			}
		}

		return updatedConstantExpenses;
	}

}
