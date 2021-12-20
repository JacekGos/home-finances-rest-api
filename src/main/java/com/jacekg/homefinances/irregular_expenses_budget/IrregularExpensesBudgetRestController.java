package com.jacekg.homefinances.irregular_expenses_budget;

import static org.springframework.http.ResponseEntity.status;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jacekg.homefinances.monthly_budget.MonthlyBudgetDTO;
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
	
	@GetMapping("/irregular-exepnses-budgets")
	public ResponseEntity<List<IrregularExpensesBudgetDTO>> 
		getAllIrregularExpensesBudgets(Principal principal) {
		
		User loggedUser = userService.findByUsername(principal.getName());
		
		return status(HttpStatus.OK)
				.body(irregularExpensesBudgetService.findAllByUserId(loggedUser.getId()));
	}
	
	@PutMapping("/irregular-exepnses-budgets")
	public ResponseEntity<IrregularExpensesBudgetDTO> updateIrregularExpensesBudget(
			@Valid @RequestBody IrregularExpensesBudgetDTO irregularExpensesBudgetDTO, Principal principal) {
		
		return status(HttpStatus.OK)
				.body(irregularExpensesBudgetService.update(irregularExpensesBudgetDTO));
	}
}
