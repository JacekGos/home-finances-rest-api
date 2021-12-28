package com.jacekg.homefinances.expenses.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Table(name = "irregular_expense")
public class IrregularExpense extends Expense {
	
	public IrregularExpense(String name, int plannedAmount, int currentAmount) {
		super(name, plannedAmount, currentAmount);
	}
}
