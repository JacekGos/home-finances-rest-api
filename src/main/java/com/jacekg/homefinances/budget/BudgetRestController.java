package com.jacekg.homefinances.budget;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
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
	public MonthlyBudget addMonthlyBudget(@RequestBody MonthlyBudgetDTO monthlyBudgetDTO) {

		MonthlyBudget monthlyBudget = new MonthlyBudget();
		monthlyBudget.setFinalBalance(1000);
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		LocalDate date = LocalDate.now();
		String text = date.format(formatter);
		LocalDate parsedDate = LocalDate.parse(text, formatter);
		
		System.out.println("date: " + parsedDate);
		
		if (budgetService.findByUserAndDate(1L, parsedDate) != null) {
			throw new BudgetAlreadyExistsException("Budżet na dany miesiąc istnieje!");
		}

		return monthlyBudget;
	}

}
