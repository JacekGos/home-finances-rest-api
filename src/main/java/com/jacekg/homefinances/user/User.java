package com.jacekg.homefinances.user;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jacekg.homefinances.budget.MonthlyBudget;
import com.jacekg.homefinances.expenses.model.UserPreferenceConstantExpense;
import com.jacekg.homefinances.role.Role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "\"user\"")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "username")
	private String username;

	@Column(name = "password")
	private String password;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "email")
	private String email;

	@Column(name = "is_enabled")
	private boolean isEnabled;

	@Column(name = "is_non_expired")
	private boolean isNonExpired;

	@Column(name = "is_credentials_non_expired")
	private boolean isCredentialsNonExpired;

	@Column(name = "is_non_locked")
	private boolean isNonLocked;
	
	@OneToMany(fetch = FetchType.LAZY,
			mappedBy = "user",
			cascade = {CascadeType.DETACH, CascadeType.MERGE,
					CascadeType.PERSIST, CascadeType.REFRESH})
	private List<MonthlyBudget> monthlyBudgets;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id", nullable = false)
	private Set<UserPreferenceConstantExpense> userPreferenceConstantExpenses;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "users_roles", 
	joinColumns = @JoinColumn(name = "user_id"), 
	inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Collection<Role> roles;
	
	public String getRoleName() {
		
		List<Role> roles = new ArrayList<>(this.roles);
		
		int roleIndex = roles.size() - 1;
		
		return roles.get(roleIndex).getName();
	}
}















