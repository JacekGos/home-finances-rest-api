package com.jacekg.homefinances.monthly_budget;

public class MonthlyBudgetDoesntExistsException extends RuntimeException {

	public MonthlyBudgetDoesntExistsException(String message, Throwable cause) {
		super(message, cause);
	}

	public MonthlyBudgetDoesntExistsException(String message) {
		super(message);
	}

	public MonthlyBudgetDoesntExistsException(Throwable cause) {
		super(cause);
	}
}
