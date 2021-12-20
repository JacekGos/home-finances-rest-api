package com.jacekg.homefinances.expenses.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@MappedSuperclass
public abstract class Expense {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "planned_amount")
	private int plannedAmount;
	
	@Column(name = "current_amount")
	private int currentAmount;
	
	public Expense(String name, int plannedAmount, int currentAmount) {
		this.name = name;
		this.plannedAmount = plannedAmount;
		this.currentAmount = currentAmount;
	}
	
	public void setName(String name) {
		this.name = name.toLowerCase().trim();
	}
}




