package com.jacekg.homefinances.expenses.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Table(name = "one_time_expense")
public class OneTimeExpense extends Expense {
	
	public OneTimeExpense(String name, int plannedAmount, int currentAmount) {
		super(name, plannedAmount, currentAmount);
	}
}
