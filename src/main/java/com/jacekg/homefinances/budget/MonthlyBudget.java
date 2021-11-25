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
@Table(name = "monthly_budget")
public class MonthlyBudget {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "date")
	private LocalDate date;
	
	@Column(name = "previous_month_earnings")
	private int previousMonthEarnings;
	
	@Column(name = "final_balance")
	private int finalBalance;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "monthly_budget_id")
	private List<ConstantExpense> constantExpenses;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "monthly_budget_id")
	private List<OneTimeExpense> oneTimeExpenses;
}




