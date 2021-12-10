package com.jacekg.homefinances.budget;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

	@PostMapping("/monthly-budgets")
	public MonthlyBudgetDTO addMonthlyBudget(
			@Valid @RequestBody MonthlyBudgetDTO monthlyBudgetDTO, Principal principal) {

		LocalDate date = LocalDate.now().withDayOfMonth(1);  
		
		User loggedUser = userService.findByUsername(principal.getName());
		
		monthlyBudgetDTO.setDate(date);
		monthlyBudgetDTO.setUserId(loggedUser.getId());
		
		if (budgetService.findByUserIdAndDate(loggedUser.getId(), date) != null) {
			throw new BudgetAlreadyExistsException("Budżet na dany miesiąc istnieje!");
		}
		System.out.println("budget: " + monthlyBudgetDTO);
		return budgetService.save(monthlyBudgetDTO);
	}
	
	@GetMapping("/monthly-budgets") 
	public List<MonthlyBudgetDTO> getAllMonthlyBudgets(Principal principal) {
		
		User loggedUser = userService.findByUsername(principal.getName());
		
		return budgetService.findAllByUserId(loggedUser.getId());
	}
	
	@PutMapping("/monthly-budgets") 
	public MonthlyBudgetDTO updateMonthlyBudget(@Valid @RequestBody MonthlyBudgetDTO monthlyBudgetDTO, Principal principal) {
		return budgetService.update(monthlyBudgetDTO);
	}
}

















