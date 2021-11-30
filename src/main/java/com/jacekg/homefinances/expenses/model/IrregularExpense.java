package com.jacekg.homefinances.expenses.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "irregular_expense")
public class IrregularExpense extends Expense {
}
