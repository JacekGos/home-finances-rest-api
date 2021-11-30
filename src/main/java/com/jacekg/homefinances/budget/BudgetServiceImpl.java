package com.jacekg.homefinances.budget;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BudgetServiceImpl implements BudgetService {
	
	private MonthlyBudgetRepository monthlyBudgetRepository;
	
	@Autowired
	public BudgetServiceImpl(MonthlyBudgetRepository monthlyBudgetRepository) {
		this.monthlyBudgetRepository = monthlyBudgetRepository;
	}
	
	@Override
	public MonthlyBudget findByUserAndDate(Long userId, LocalDate date) {
		return monthlyBudgetRepository.findByUserAndDate(userId, date);
	}

}
