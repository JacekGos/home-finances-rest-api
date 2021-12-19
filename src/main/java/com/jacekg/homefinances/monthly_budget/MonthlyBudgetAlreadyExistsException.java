package com.jacekg.homefinances.monthly_budget;

public class MonthlyBudgetAlreadyExistsException extends RuntimeException {
	
	public MonthlyBudgetAlreadyExistsException(String message, Throwable cause) {
		super(message, cause);
	}

	public MonthlyBudgetAlreadyExistsException(String message) {
		super(message);
	}

	public MonthlyBudgetAlreadyExistsException(Throwable cause) {
		super(cause);
	}
}
