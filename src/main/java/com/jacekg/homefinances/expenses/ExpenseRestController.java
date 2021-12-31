package com.jacekg.homefinances.expenses;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jacekg.homefinances.expenses.model.ConstantExpenseDTO;
import com.jacekg.homefinances.expenses.model.UserPreferenceConstantExpense;
import com.jacekg.homefinances.user.User;
import com.jacekg.homefinances.user.UserService;

import static org.springframework.http.ResponseEntity.status;

import java.security.Principal;
import java.util.Set;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/budget")
@AllArgsConstructor
public class ExpenseRestController {
	
	ExpenseService expenseService;
	
	UserService userService;
	
	@PostMapping("/expenses")
	public ResponseEntity<Void> addUserPreferenceConstantExpense
		(@RequestBody ConstantExpenseDTO constantExpenseDTO, Principal principal) {
		
		User loggedUser = userService.findByUsername(principal.getName());

		expenseService.addUserPreferenceConstantExpense(constantExpenseDTO, loggedUser);
		
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@DeleteMapping("/expenses")
	public ResponseEntity<Void> removeUserPreferenceConstantExpense
		(@RequestBody ConstantExpenseDTO constantExpenseDTO, Principal principal) {
		
		User loggedUser = userService.findByUsername(principal.getName());
		
		expenseService.removeUserPreferenceConstantExpense(constantExpenseDTO, loggedUser);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
}






