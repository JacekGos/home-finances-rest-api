package com.jacekg.homefinances.budget;

public class BudgetDoesntExistsException extends RuntimeException {

	public BudgetDoesntExistsException(String message, Throwable cause) {
		super(message, cause);
	}

	public BudgetDoesntExistsException(String message) {
		super(message);
	}

	public BudgetDoesntExistsException(Throwable cause) {
		super(cause);
	}
}
