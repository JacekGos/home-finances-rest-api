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
		
		User user = userService.findByUsername(principal.getName());
		
		monthlyBudgetDTO.setDate(date);
		monthlyBudgetDTO.setUserId(user.getId());
		
		if (budgetService.findByUserIdAndDate(user.getId(), date) != null) {
			throw new BudgetAlreadyExistsException("Budżet na dany miesiąc istnieje!");
		}

		return budgetService.save(monthlyBudgetDTO);
	}
	
	@GetMapping("/monthly-budgets") 
	public List<MonthlyBudgetDTO> getAllMonthlyBudgets(Principal principal) {
		
		User user = userService.findByUsername(principal.getName());
		
		return budgetService.findAllByUserId(user.getId());
	}
	
	@PutMapping("/monthly-budgets") 
	public MonthlyBudgetDTO updateMonthlyBudget(@RequestBody MonthlyBudgetDTO monthlyBudgetDTO) {
		return budgetService.updatate(monthlyBudgetDTO);
	}
	
	
}

















