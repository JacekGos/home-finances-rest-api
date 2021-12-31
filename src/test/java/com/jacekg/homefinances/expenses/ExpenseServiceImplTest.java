package com.jacekg.homefinances.expenses;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
	void testSave() {
		
	
	}

	@Test
	void testDelete() {
		fail("Not yet implemented");
	}

}
