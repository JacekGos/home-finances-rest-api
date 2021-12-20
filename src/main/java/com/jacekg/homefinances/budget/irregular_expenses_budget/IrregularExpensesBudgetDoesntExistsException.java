package com.jacekg.homefinances.budget.irregular_expenses_budget;

public class IrregularExpensesBudgetDoesntExistsException extends RuntimeException {

	public IrregularExpensesBudgetDoesntExistsException(String message, Throwable cause) {
		super(message, cause);
	}

	public IrregularExpensesBudgetDoesntExistsException(String message) {
		super(message);
	}

	public IrregularExpensesBudgetDoesntExistsException(Throwable cause) {
		super(cause);
	}
}
