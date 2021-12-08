package com.jacekg.homefinances.budget;

import java.security.Principal;
import java.time.LocalDate;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jacekg.homefinances.user.User;
import com.jacekg.homefinances.user.UserService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/budget")
@AllArgsConstructor
public class BudgetRestController {

	private final BudgetService budgetService;
	
	private final UserService userService;

//	@Autowired
//	public BudgetRestController(BudgetService budgetService, UserService userService) {
//		this.budgetService = budgetService;
//		this.userService = userService;
//	}

	@PostMapping("/monthly-budgets")
	public MonthlyBudgetDTO addMonthlyBudget(
			@Valid @RequestBody MonthlyBudgetDTO monthlyBudgetDTO, Principal principal) {

		LocalDate date = LocalDate.now().withDayOfMonth(1); 
		
		User user = userService.findByUsername(principal.getName());
		
		monthlyBudgetDTO.setDate(date);
		monthlyBudgetDTO.setUserId(user.getId());
		
		if (budgetService.findByUserIdAndDate(user.getId(), date) != null) {
			throw new BudgetAlreadyExistsException("Budżet na dany miesiąc istnieje!");
		}

		return budgetService.save(monthlyBudgetDTO);
	}
}
