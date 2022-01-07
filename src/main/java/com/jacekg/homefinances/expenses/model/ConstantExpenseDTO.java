package com.jacekg.homefinances.expenses.model;

import com.fasterxml.jackson.annotation.JsonTypeName;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@JsonTypeName("constantExpenseDTO")
public class ConstantExpenseDTO extends ExpenseDTO {
	
	public ConstantExpenseDTO(Long id, String name, int plannedAmount, int currentAmount) {
		super(id, name, plannedAmount, currentAmount);
	}
}
