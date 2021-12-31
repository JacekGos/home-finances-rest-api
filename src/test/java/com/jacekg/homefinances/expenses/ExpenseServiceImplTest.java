package com.jacekg.homefinances.expenses;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import com.jacekg.homefinances.expenses.model.ConstantExpense;
import com.jacekg.homefinances.expenses.model.ConstantExpenseDTO;
import com.jacekg.homefinances.expenses.model.UserPreferenceConstantExpense;
import com.jacekg.homefinances.role.Role;
import com.jacekg.homefinances.user.User;
import com.jacekg.homefinances.user.UserRepository;

@ExtendWith(MockitoExtension.class)
class ExpenseServiceImplTest {
	
	@InjectMocks
	ExpenseServiceImpl serviceUnderTest;
	
	@Mock
	UserPreferenceConstantExpenseRepository userPreferenceConstantExpenseRepository;
	
	@Mock
	UserRepository userRepository;
	
	@Test
	void addUserPreferenceConstantExpense_ShouldSave_UserWithNewPreference() {
		
		ConstantExpenseDTO constantExpenseDTO = new ConstantExpenseDTO();
		constantExpenseDTO.setName("expense");
		
		User user = new User(
				1L,
				"username",
				"password",
				"firstname", 
				"lastname",
				"email",
				true, true, true, true,
				null,
				null,
				Arrays.asList(new Role(1L, "ROLE_USER"), new Role(2L, "ROLE_ADMIN")));
		
		UserPreferenceConstantExpense userPreferenceConstantExpense 
			= new UserPreferenceConstantExpense("expense");
		
		UserPreferenceConstantExpense currentUserPreferenceConstantExpense 
			= new UserPreferenceConstantExpense("current expense");
		
		Set<UserPreferenceConstantExpense> userPreferenceConstantExpenses 
			= new HashSet<UserPreferenceConstantExpense>();
		userPreferenceConstantExpenses.add(currentUserPreferenceConstantExpense);
		
		when(userPreferenceConstantExpenseRepository.findAllByUserId(user.getId())).thenReturn(userPreferenceConstantExpenses);
		
		serviceUnderTest.addUserPreferenceConstantExpense(constantExpenseDTO, user);
		
		verify(userPreferenceConstantExpenseRepository).findAllByUserId(user.getId());
		verify(userRepository).save(user);
	}

	@Test
	void testDelete() {
		fail("Not yet implemented");
	}

}
