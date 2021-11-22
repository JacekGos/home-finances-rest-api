package com.jacekg.homefinances.expenses;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "irregular-expense")
public class OneTimeExpense extends Expense {
}
