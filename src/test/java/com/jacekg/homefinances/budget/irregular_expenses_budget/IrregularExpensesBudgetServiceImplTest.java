package com.jacekg.homefinances.budget.irregular_expenses_budget;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import com.jacekg.homefinances.budget.monthly_budget.MonthlyBudgetAlreadyExistsException;
import com.jacekg.homefinances.budget.monthly_budget.MonthlyBudgetDTO;
import com.jacekg.homefinances.budget.monthly_budget.MonthlyBudgetRepository;
import com.jacekg.homefinances.budget.monthly_budget.MonthlyBudgetService;
import com.jacekg.homefinances.expenses.IrregularExpenseRepository;
import com.jacekg.homefinances.role.Role;
import com.jacekg.homefinances.user.User;
import com.jacekg.homefinances.user.UserRepository;

@ExtendWith(MockitoExtension.class)
class IrregularExpensesBudgetServiceImplTest {
	
	@InjectMocks
	@Spy
	IrregularExpensesBudgetServiceImpl serviceUnderTest;
	
	@Mock
	private MonthlyBudgetService monthlyBudgetService;
	
	@Mock
	private UserRepository userRepository;
	
	@Mock
	private IrregularExpensesBudgetRepository irregularExpensesBudgetRepository;
	
	@Mock
	private IrregularExpenseRepository irregularExpenseRepository;
	
	@Mock
	private MonthlyBudgetRepository monthlyBudgetRepository;
	
	@Mock
	private ModelMapper modelMapper;
	
	@Test
	void findByUserIdAndDate_ShouldReturn_IrregularExpensesBudgetDTO() {
		
		LocalDate date = LocalDate.now().withDayOfMonth(1);
		
		IrregularExpensesBudgetDTO irregularExpensesBudgetDTO = new IrregularExpensesBudgetDTO
				(1L, 1L, date, 0, 0, null);
		
		IrregularExpensesBudget irregularExpensesBudget = new IrregularExpensesBudget
				(1L, date, 0, 0, null, null);
		
		when(irregularExpensesBudgetRepository
				.findByUserIdAndDate(any(Long.class), any(LocalDate.class))).thenReturn(irregularExpensesBudget);
		when(modelMapper.map(irregularExpensesBudget, IrregularExpensesBudgetDTO.class)).thenReturn(irregularExpensesBudgetDTO);
		
		IrregularExpensesBudgetDTO returnedIrregularExpensesBudgetDTO = serviceUnderTest.findByUserIdAndDate(1L, date);
		
		verify(irregularExpensesBudgetRepository).findByUserIdAndDate(1L, date);
		
		assertNotNull(returnedIrregularExpensesBudgetDTO);
		assertEquals(1L, returnedIrregularExpensesBudgetDTO.getId());
		assertEquals(date, returnedIrregularExpensesBudgetDTO.getDate());
	}

	@Test
	void save_ShouldReturn_IrregularExpensesBudgetDTO() {
	
		LocalDate date = LocalDate.now().withDayOfMonth(1);
		
		IrregularExpensesBudgetDTO irregularExpensesBudgetDTO = new IrregularExpensesBudgetDTO
				(1L, 1L, date, 0, 0, null);
		
		IrregularExpensesBudget irregularExpensesBudget = new IrregularExpensesBudget
				(1L, date, 0, 0, null, null);
		
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
		
		doReturn(null).when(serviceUnderTest).findByUserIdAndDate(1L, date);
		when(modelMapper.map(irregularExpensesBudgetDTO, IrregularExpensesBudget.class)).thenReturn(irregularExpensesBudget);
		when(modelMapper.map(irregularExpensesBudget, IrregularExpensesBudgetDTO.class)).thenReturn(irregularExpensesBudgetDTO);
		when(userRepository.findByUserId(1L)).thenReturn(user);
		when(irregularExpensesBudgetRepository.save(irregularExpensesBudget)).thenReturn(irregularExpensesBudget);
		
		IrregularExpensesBudgetDTO returnedIrregularExpensesBudgetDTO = serviceUnderTest.save(irregularExpensesBudgetDTO);
		
		verify(userRepository).findByUserId(1L);
		verify(irregularExpensesBudgetRepository).save(irregularExpensesBudget);
		
		assertEquals(1L, returnedIrregularExpensesBudgetDTO.getUserId());
	}
	
	@Test
	void save_ShouldThrow_IrregularExpensesBudgetAlreadyExistsException() {
		
		LocalDate date = LocalDate.now().withDayOfMonth(1);
		
		IrregularExpensesBudgetDTO irregularExpensesBudgetDTO = new IrregularExpensesBudgetDTO
				(1L, 1L, date, 0, 0, null);
		
		doReturn(new IrregularExpensesBudgetDTO()).when(serviceUnderTest).findByUserIdAndDate(1L, date);
		
		assertThrows(IrregularExpensesBudgetAlreadyExistsException.class, () -> {
			serviceUnderTest.save(irregularExpensesBudgetDTO);
		});
		
		verify(serviceUnderTest).findByUserIdAndDate(1L, date);
	}
	

	@Test
	void testFindAllByUserId() {
		fail("Not yet implemented");
	}

	@Test
	void testUpdate() {
		fail("Not yet implemented");
	}

	@Test
	void testDeleteByDate() {
		fail("Not yet implemented");
	}

}
