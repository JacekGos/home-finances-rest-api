package com.jacekg.homefinances.expenses;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.jacekg.homefinances.expenses.model.UserPreferenceConstantExpense;
import com.jacekg.homefinances.role.Role;
import com.jacekg.homefinances.user.User;
import com.jacekg.homefinances.user.UserRepository;

@DataJpaTest
class UserPreferenceConstantExpenseRepositoryTest {
	
	@Autowired
	UserPreferenceConstantExpenseRepository userPreferenceConstantExpenseRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Test
	void findAllByUserId__ShouldReturn_UserPreferenceConstantExpensesList() {
		
		Long userId = 1L;
		
		UserPreferenceConstantExpense userPreferenceConstantExpense 
			= new UserPreferenceConstantExpense("user preference expense");
		
		User user = new User(
				10L,
				"username",
				"password",
				"firstname",
				"lastname",
				"email",
				true, true, true, true,
				null,
				null,
				Arrays.asList(new Role(1L, "ROLE_USER"), new Role(2L, "ROLE_ADMIN")));
		
		Set<UserPreferenceConstantExpense> userPreferenceConstantExpenses = new HashSet<>();
		userPreferenceConstantExpenses.add(userPreferenceConstantExpense);
		
		user.setUserPreferenceConstantExpenses(userPreferenceConstantExpenses);
		userRepository.save(user);
		
		Set<UserPreferenceConstantExpense> foundUserPreferenceConstantExpenses
			= userPreferenceConstantExpenseRepository.findAllByUserId(userId);
		
		assertTrue(foundUserPreferenceConstantExpenses.size() > 0);
	}

}
