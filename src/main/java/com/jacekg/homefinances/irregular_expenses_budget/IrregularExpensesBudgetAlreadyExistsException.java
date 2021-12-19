package com.jacekg.homefinances.irregular_expenses_budget;

public class IrregularExpensesBudgetAlreadyExistsException extends RuntimeException {
	
	public IrregularExpensesBudgetAlreadyExistsException(String message, Throwable cause) {
		super(message, cause);
	}

	public IrregularExpensesBudgetAlreadyExistsException(String message) {
		super(message);
	}

	public IrregularExpensesBudgetAlreadyExistsException(Throwable cause) {
		super(cause);
	}
}
