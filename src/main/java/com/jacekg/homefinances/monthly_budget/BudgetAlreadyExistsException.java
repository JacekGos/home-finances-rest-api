package com.jacekg.homefinances.monthly_budget;

public class BudgetAlreadyExistsException extends RuntimeException {
	
	public BudgetAlreadyExistsException(String message, Throwable cause) {
		super(message, cause);
	}

	public BudgetAlreadyExistsException(String message) {
		super(message);
	}

	public BudgetAlreadyExistsException(Throwable cause) {
		super(cause);
	}
}
