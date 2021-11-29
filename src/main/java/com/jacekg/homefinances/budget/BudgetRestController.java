package com.jacekg.homefinances.budget;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/budget")
public class BudgetRestController {
	
	@PostMapping("/monthly-budgets")
	public MonthlyBudget addMonthlyBudget() {
		
	}
	
}
