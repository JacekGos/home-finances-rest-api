package com.jacekg.homefinances.irregular_expenses_budget;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jacekg.homefinances.expenses.model.IrregularExpense;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class IrregularExpensesBudgetDTO {
	
	private Long id;
	
	private Long userId;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate date;
	
	@NotNull(message = "wymagane")
	@Min(value = 0)
	@Max(value = 10000)
	private int annualExpensesSum;
	
	private int necessaryMonthlySavings;
	
	@Valid
	private List<IrregularExpense> irregularExpenses;
}











