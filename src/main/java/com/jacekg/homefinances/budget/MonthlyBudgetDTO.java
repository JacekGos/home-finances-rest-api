package com.jacekg.homefinances.budget;

import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.jacekg.homefinances.expenses.ConstantExpense;
import com.jacekg.homefinances.expenses.ExpenseDTO;
import com.jacekg.homefinances.expenses.OneTimeExpense;
import com.jacekg.homefinances.expenses.OneTimeExpenseDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class MonthlyBudgetDTO {

	private Long id;
	
	private Long userId;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull(message = "wymagane")
	private LocalDate date;
	
	@NotNull(message = "wymagane")
	@Min(value = 0)
	private int previousMonthEarnings;
	
	private int finalBalance;
	
	@NotNull(message = "wymagane")
	private List<ExpenseDTO> constantExpenses;
	
	private List<OneTimeExpenseDTO> oneTimeExpenses;
}






