package com.jacekg.homefinances.expenses.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Table(name = "constant_expense")
public class ConstantExpense extends Expense{
	
	public ConstantExpense(String name, int plannedAmount, int currentAmount) {
		super(name, plannedAmount, currentAmount);
	}
}
