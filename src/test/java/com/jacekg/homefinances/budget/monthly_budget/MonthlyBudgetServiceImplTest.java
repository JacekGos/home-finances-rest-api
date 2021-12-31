package com.jacekg.homefinances.budget.monthly_budget;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import com.jacekg.homefinances.budget.irregular_expenses_budget.IrregularExpensesBudgetRepository;
import com.jacekg.homefinances.expenses.ConstantExpenseRepository;
import com.jacekg.homefinances.expenses.OneTimeExpenseRepository;
import com.jacekg.homefinances.expenses.model.UserPreferenceConstantExpense;
import com.jacekg.homefinances.role.Role;
import com.jacekg.homefinances.user.User;
import com.jacekg.homefinances.user.UserNotExistsException;
import com.jacekg.homefinances.user.UserRepository;

@ExtendWith(MockitoExtension.class)
class MonthlyBudgetServiceImplTest {
	
	@InjectMocks
	@Spy
	MonthlyBudgetServiceImpl serviceUnderTest;
	
	@Mock
	private MonthlyBudgetRepository monthlyBudgetRepository;

	@Mock
	private UserRepository userRepository;
	
	@Mock
	private ConstantExpenseRepository constantExpenseRepository;
	
	@Mock
	private OneTimeExpenseRepository oneTimeExpenseRepository;
	
	@Mock
	private IrregularExpensesBudgetRepository irregularExpensesBudgetRepository;

	@Mock
	private ModelMapper modelMapper;
	
	@Test
	void findByUserIdAndDate_ShouldReturn_MonthlyBudgetDTO() {

		LocalDate date = LocalDate.now().withDayOfMonth(1);
		
		MonthlyBudgetDTO monthlyBudgetDTO = new MonthlyBudgetDTO
				(1L, 1L, date, 0, 0, null, null);
		
		MonthlyBudget monthlyBudget = new MonthlyBudget
				(1L, date, 0, 0, null, null, null);
		
		when(monthlyBudgetRepository
				.findByUserIdAndDate(any(Long.class), any(LocalDate.class))).thenReturn(monthlyBudget);
		when(modelMapper.map(monthlyBudget, MonthlyBudgetDTO.class)).thenReturn(monthlyBudgetDTO);
		
		MonthlyBudgetDTO returnedMonthlyBudgetDTO = serviceUnderTest.findByUserIdAndDate(1L, date);
		
		verify(monthlyBudgetRepository).findByUserIdAndDate(1L, date);
		
		assertNotNull(returnedMonthlyBudgetDTO);
		assertEquals(1L, returnedMonthlyBudgetDTO.getId());
		assertEquals(date, returnedMonthlyBudgetDTO.getDate());
	}

	@Test
	void save_ShouldReturn_MonthlyBudgetDTO() {
		
		LocalDate date = LocalDate.now().withDayOfMonth(1);
		
		MonthlyBudgetDTO monthlyBudgetDTO = new MonthlyBudgetDTO
				(1L, 1L, date, 0, 0, null, null);
		
		MonthlyBudget monthlyBudget = new MonthlyBudget
				(1L, date, 0, 0, null, null, null);
		
		Set<UserPreferenceConstantExpense> userPreferenceConstantExpenses = new HashSet<>();
		
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
		
		user.setUserPreferenceConstantExpenses(userPreferenceConstantExpenses);
		
		doReturn(null).when(serviceUnderTest).findByUserIdAndDate(1L, date);
		when(modelMapper.map(monthlyBudgetDTO, MonthlyBudget.class)).thenReturn(monthlyBudget);
		when(modelMapper.map(monthlyBudget, MonthlyBudgetDTO.class)).thenReturn(monthlyBudgetDTO);
		when(userRepository.findByUserId(1L)).thenReturn(user);
		when(monthlyBudgetRepository.save(monthlyBudget)).thenReturn(monthlyBudget);
		
		MonthlyBudgetDTO returnedMonthlyBudgetDTO = serviceUnderTest.save(monthlyBudgetDTO);
		
		verify(userRepository).findByUserId(1L);
		verify(monthlyBudgetRepository).save(monthlyBudget);
		
		assertEquals(1L, returnedMonthlyBudgetDTO.getUserId());
	}
	
	@Test
	void save_ShouldThrow_MonthlyBudgetAlreadyExistsException() {
		
		LocalDate date = LocalDate.now().withDayOfMonth(1);
		
		MonthlyBudgetDTO monthlyBudgetDTO = new MonthlyBudgetDTO
				(1L, 1L, date, 0, 0, null, null);
		
		doReturn(new MonthlyBudgetDTO()).when(serviceUnderTest).findByUserIdAndDate(1L, date);
		
		assertThrows(MonthlyBudgetAlreadyExistsException.class, () -> {
			serviceUnderTest.save(monthlyBudgetDTO);
		});
		
		verify(serviceUnderTest).findByUserIdAndDate(1L, date);
	}
	
	@Test
	void findAllByUserId_ShouldReturn_MonthlyBudgetDTOList() {
	
		Long userId = 1L;
		
		List<MonthlyBudget> monthlyBudgets = new ArrayList<>();
		monthlyBudgets.add(new MonthlyBudget());
		
		when(monthlyBudgetRepository.findAllByUserId(userId)).thenReturn(monthlyBudgets);
		
		List<MonthlyBudgetDTO> returnedMonthlyBudgetsDTO = serviceUnderTest.findAllByUserId(userId);

		verify(monthlyBudgetRepository).findAllByUserId(userId);
		
		assertTrue(returnedMonthlyBudgetsDTO.size() > 0);
	}

	@Test
	void update_ShouldReturn_MonthlyBudgetDTO() {
		
		LocalDate date = LocalDate.now().withDayOfMonth(1);
		
		MonthlyBudgetDTO monthlyBudgetDTO = new MonthlyBudgetDTO
				(1L, 1L, date, 0, 0, null, null);
		
		MonthlyBudget monthlyBudget = new MonthlyBudget
				(1L, date, 0, 0, null, new ArrayList<>(), new ArrayList<>());
		
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
		
		when(modelMapper.map(monthlyBudgetDTO, MonthlyBudget.class)).thenReturn(monthlyBudget);
		when(modelMapper.map(monthlyBudget, MonthlyBudgetDTO.class)).thenReturn(monthlyBudgetDTO);
		when(userRepository.findByUserId(1L)).thenReturn(user);
		doReturn(monthlyBudgetDTO).when(serviceUnderTest).findByUserIdAndDate(1L, date);
		when(monthlyBudgetRepository.save(any(MonthlyBudget.class))).thenReturn(monthlyBudget);
		
		MonthlyBudgetDTO returnedMonthlyBudgetDTO = serviceUnderTest.update(monthlyBudgetDTO);
		
		verify(userRepository).findByUserId(1L);
		verify(monthlyBudgetRepository).save(monthlyBudget);
		
		assertEquals(1L, returnedMonthlyBudgetDTO.getId());
	}
	
	@Test
	void update_ShouldThrow_UserNotExistsException() {
		
		LocalDate date = LocalDate.now().withDayOfMonth(1);
		
		MonthlyBudgetDTO monthlyBudgetDTO = new MonthlyBudgetDTO
				(1L, 1L, date, 0, 0, null, null);
		
		when(userRepository.findByUserId(1L)).thenReturn(null);
		
		assertThrows(UserNotExistsException.class, () -> {
			serviceUnderTest.update(monthlyBudgetDTO);
		});
		
		verify(userRepository).findByUserId(1L);
	}
	
	@Test
	void update_ShouldThrow_MonthlyBudgetAlreadyExistsException() {
		
		User user = new User();
		user.setId(1L);
		
		LocalDate date = LocalDate.now().withDayOfMonth(1);
		
		MonthlyBudgetDTO monthlyBudgetDTO = new MonthlyBudgetDTO
				(1L, 1L, date, 0, 0, null, null);
		
		MonthlyBudgetDTO searchedMonthlyBudgetDTO = new MonthlyBudgetDTO
				(2L, 1L, date, 0, 0, null, null);
		
		when(userRepository.findByUserId(1L)).thenReturn(user);
		doReturn(searchedMonthlyBudgetDTO).when(serviceUnderTest).findByUserIdAndDate(1L, date);
		
		assertThrows(MonthlyBudgetAlreadyExistsException.class, () -> {
			serviceUnderTest.update(monthlyBudgetDTO);
		});
		
		verify(userRepository).findByUserId(1L);
	}

	@Test
	void deleteByDate_ShouldNotThrow_Exception() {
		
		LocalDate date = LocalDate.now().withDayOfMonth(1);
		
		MonthlyBudget monthlyBudget = new MonthlyBudget
				(1L, date, 0, 0, null, null, null);
		
		when(monthlyBudgetRepository
				.findByUserIdAndDate(1L, date)).thenReturn(monthlyBudget);
		doNothing().when(monthlyBudgetRepository).delete(monthlyBudget);
		
		serviceUnderTest.deleteByDate(date, 1L);
		
		verify(monthlyBudgetRepository).findByUserIdAndDate(1L, date);
		verify(monthlyBudgetRepository).delete(monthlyBudget);
	}
	
	@Test
	void deleteByDate_ShouldThrow_MonthlyBudgetDoesntExistsException() {
		
		LocalDate date = LocalDate.now().withDayOfMonth(1);
		
		when(monthlyBudgetRepository
				.findByUserIdAndDate(1L, date)).thenReturn(null);
		
		assertThrows(MonthlyBudgetDoesntExistsException.class, () -> {
			serviceUnderTest.deleteByDate(date, 1L);
		});
		
		verify(monthlyBudgetRepository).findByUserIdAndDate(1L, date);
	}

}
