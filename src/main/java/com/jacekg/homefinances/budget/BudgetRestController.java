package com.jacekg.homefinances.budget;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/budget")
public class BudgetRestController {

	private BudgetService budgetService;

	@Autowired
	public BudgetRestController(BudgetService budgetService) {
		this.budgetService = budgetService;
	}

	@PostMapping("/monthly-budgets")
	public MonthlyBudgetDTO addMonthlyBudget(@Valid @RequestBody MonthlyBudgetDTO monthlyBudgetDTO) {

		MonthlyBudget monthlyBudget = new MonthlyBudget();
		
		LocalDate date = LocalDate.now().withDayOfMonth(1);
		
		System.out.println("date: " + date);
		
		if (budgetService.findByUserIdAndDate(1L, date) != null) {
			throw new BudgetAlreadyExistsException("Budżet na dany miesiąc istnieje!");
		}

		return new MonthlyBudgetDTO();
	}
}
