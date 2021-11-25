package com.jacekg.homefinances.budget;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.jacekg.homefinances.expenses.ConstantExpense;
import com.jacekg.homefinances.expenses.IrregularExpense;
import com.jacekg.homefinances.expenses.OneTimeExpense;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "irregular_expenses_budget")
public class IrregularExpensesBudget {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "date")
	private LocalDate date;
	
	@Column(name = "annual_exepnses_sum")
	private int annualExpensesSum;
	
	@Column(name = "necessary_monthly_savings")
	private int necessaryMonthlySavings;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "irregular_expenses_budget_id")
	private List<IrregularExpense> irregularExpenses;

}











