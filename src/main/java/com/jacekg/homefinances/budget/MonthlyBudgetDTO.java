package com.jacekg.homefinances.budget;

import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.jacekg.homefinances.expenses.model.ConstantExpense;
import com.jacekg.homefinances.expenses.model.ConstantExpenseDTO;
import com.jacekg.homefinances.expenses.model.ExpenseDTO;
import com.jacekg.homefinances.expenses.model.OneTimeExpense;
import com.jacekg.homefinances.expenses.model.OneTimeExpenseDTO;

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
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate date;
	
	@NotNull(message = "wymagane")
	@Min(value = 0)
	@Max(value = 1000)
	private int previousMonthEarnings;
	
	private int finalBalance;
	
	@NotNull(message = "wymagane")
	private List<ConstantExpenseDTO> constantExpenses;
	
	private List<OneTimeExpenseDTO> oneTimeExpenses;
}






