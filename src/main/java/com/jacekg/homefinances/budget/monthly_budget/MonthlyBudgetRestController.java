package com.jacekg.homefinances.budget.monthly_budget;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.format.annotation.DateTimeFormat;

import static org.springframework.http.ResponseEntity.status;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
public class MonthlyBudgetRestController {

	private final MonthlyBudgetService monthlyBudgetService;
	
	private final UserService userService;

	@PostMapping("/monthly-budgets")
	public ResponseEntity<MonthlyBudgetDTO> addMonthlyBudget
			(@Valid @RequestBody MonthlyBudgetDTO monthlyBudgetDTO, Principal principal) {

		LocalDate date = LocalDate.now().withDayOfMonth(1); 
		
		User loggedUser = userService.findByUsername(principal.getName());
		
		monthlyBudgetDTO.setDate(date);
		monthlyBudgetDTO.setUserId(loggedUser.getId());
		
		return  status(HttpStatus.CREATED).body(monthlyBudgetService.save(monthlyBudgetDTO));
	}
	
	@GetMapping("/monthly-budgets") 
	public ResponseEntity<List<MonthlyBudgetDTO>> getAllMonthlyBudgets(Principal principal) {
		
		User loggedUser = userService.findByUsername(principal.getName());
		
		return status(HttpStatus.OK).body(monthlyBudgetService.findAllByUserId(loggedUser.getId()));
	}
	
	@PutMapping("/monthly-budgets") 
	public ResponseEntity<MonthlyBudgetDTO> updateMonthlyBudget
			(@Valid @RequestBody MonthlyBudgetDTO monthlyBudgetDTO, Principal principal) {
		
		return status(HttpStatus.OK).body(monthlyBudgetService.update(monthlyBudgetDTO));
	}
	
	@DeleteMapping("/monthly-budgets/{date}")
	public ResponseEntity<String> deleteMonthlyBudget
		(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date, Principal principal) {
		
		User loggedUser = userService.findByUsername(principal.getName());
		
		date = date.withDayOfMonth(1);
		
		monthlyBudgetService.deleteByDate(date, loggedUser.getId());
		
		return status(HttpStatus.OK).body("Monthly budget of date: " + date + " deleted sucessfully");
	}

}














