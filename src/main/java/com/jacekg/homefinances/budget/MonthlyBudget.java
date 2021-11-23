package com.jacekg.homefinances.budget;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
	
	private int id;
	
	private LocalDate date;
	
	private int previousMonthEarnings;
	
	private int finalBalance;
	
	@OneToMany(fetch = FetchType.LAZY, 
			cascade = {CascadeType.DETACH, CascadeType.MERGE,
					CascadeType.PERSIST, CascadeType.REFRESH})
	private List<ConstantExpense> constantExpenses;
	
	private List<OneTimeExpense> oneTimeExpenses;
}










