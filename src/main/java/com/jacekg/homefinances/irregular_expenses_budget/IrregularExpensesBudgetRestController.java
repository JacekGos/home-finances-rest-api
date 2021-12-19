package com.jacekg.homefinances.irregular_expenses_budget;

import static org.springframework.http.ResponseEntity.status;

import java.security.Principal;
import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
public class IrregularExpensesBudgetRestController {
	
	UserService userService;
	
	IrregularExpensesBudgetService irregularExpensesBudgetService;
	
	@PostMapping("/irregular-exepnses-budgets")
	public ResponseEntity<IrregularExpensesBudgetDTO> addIrreguarExpenseBudget
		(@RequestBody IrregularExpensesBudgetDTO irregularExpensesBudgetDTO, Principal principal) {
		
		LocalDate date = LocalDate.now().withMonth(1).withDayOfMonth(1); 
		
		User loggedUser = userService.findByUsername(principal.getName());
		
		irregularExpensesBudgetDTO.setDate(date);
		irregularExpensesBudgetDTO.setUserId(loggedUser.getId());
		
		return  status(HttpStatus.CREATED)
				.body(irregularExpensesBudgetService.save(irregularExpensesBudgetDTO));
	}
	
	
}
