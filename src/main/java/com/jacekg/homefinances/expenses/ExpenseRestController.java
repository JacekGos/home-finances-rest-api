package com.jacekg.homefinances.expenses;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jacekg.homefinances.expenses.model.ConstantExpenseDTO;
import com.jacekg.homefinances.expenses.model.UserPreferenceConstantExpense;

import static org.springframework.http.ResponseEntity.status;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/expense")
@AllArgsConstructor
public class ExpenseRestController {
	
	ExpenseService expenseService;
	
	@PostMapping("/expenses")
	public ResponseEntity<Void> addUserPreferenceConstantExpense
		(@RequestBody ConstantExpenseDTO constantExpenseDTO) {
		
		expenseService.save(constantExpenseDTO);
		
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

}
