package com.jacekg.homefinances.budget.irregular_expenses_budget;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import com.jacekg.homefinances.budget.monthly_budget.MonthlyBudget;
import com.jacekg.homefinances.budget.monthly_budget.MonthlyBudgetAlreadyExistsException;
import com.jacekg.homefinances.budget.monthly_budget.MonthlyBudgetDTO;
import com.jacekg.homefinances.budget.monthly_budget.MonthlyBudgetRepository;
import com.jacekg.homefinances.budget.monthly_budget.MonthlyBudgetService;
import com.jacekg.homefinances.expenses.IrregularExpenseRepository;
import com.jacekg.homefinances.role.Role;
import com.jacekg.homefinances.user.User;
import com.jacekg.homefinances.user.UserNotExistsException;
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
	void findAllByUserId_ShouldReturn_IrregularExpensesBudgetDTOList() {
	
		Long userId = 1L;
		
		List<IrregularExpensesBudget> irregularExpensesBudgets = new ArrayList<>();
		irregularExpensesBudgets.add(new IrregularExpensesBudget());
		
		when(irregularExpensesBudgetRepository.findAllByUserId(userId)).thenReturn(irregularExpensesBudgets);
		
		List<IrregularExpensesBudgetDTO> returnedIrregularExpensesBudgetsDTO = serviceUnderTest.findAllByUserId(userId);

		verify(irregularExpensesBudgetRepository).findAllByUserId(userId);
		
		assertTrue(returnedIrregularExpensesBudgetsDTO.size() > 0);
	}

	@Test
	void update_ShouldReturn_IrregularExpensesBudgetDTO() {
	
		LocalDate date = LocalDate.now().withDayOfMonth(1);
		
		IrregularExpensesBudgetDTO irregularExpensesBudgetDTO = new IrregularExpensesBudgetDTO
				(1L, 1L, date, 0, 0, null);
		
		IrregularExpensesBudget irregularExpensesBudget = new IrregularExpensesBudget
				(1L, date, 0, 0, null, new ArrayList<>());
		
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
		
		when(modelMapper.map(irregularExpensesBudgetDTO, IrregularExpensesBudget.class)).thenReturn(irregularExpensesBudget);
		when(modelMapper.map(irregularExpensesBudget, IrregularExpensesBudgetDTO.class)).thenReturn(irregularExpensesBudgetDTO);
		when(userRepository.findByUserId(1L)).thenReturn(user);
		doReturn(irregularExpensesBudgetDTO).when(serviceUnderTest).findByUserIdAndDate(1L, date);
		when(irregularExpensesBudgetRepository.save(any(IrregularExpensesBudget.class))).thenReturn(irregularExpensesBudget);
		
		IrregularExpensesBudgetDTO returnedIrregularExpensesBudgetDTO = serviceUnderTest.update(irregularExpensesBudgetDTO);
		
		verify(userRepository).findByUserId(1L);
		verify(irregularExpensesBudgetRepository).save(irregularExpensesBudget);
		
		assertEquals(1L, returnedIrregularExpensesBudgetDTO.getId());
	}
	
	@Test
	void update_ShouldThrow_UserNotExistsException() {
		
		LocalDate date = LocalDate.now().withDayOfMonth(1);
		
		IrregularExpensesBudgetDTO irregularExpensesBudgetDTO = new IrregularExpensesBudgetDTO
				(1L, 1L, date, 0, 0, null);
		
		when(userRepository.findByUserId(1L)).thenReturn(null);
		
		assertThrows(UserNotExistsException.class, () -> {
			serviceUnderTest.update(irregularExpensesBudgetDTO);
		});
		
		verify(userRepository).findByUserId(1L);
	}
	
	@Test
	void update_ShouldThrow_IrregularExpensesBudgetAlreadyExistsException() {
		
		User user = new User();
		user.setId(1L);
		
		LocalDate date = LocalDate.now().withDayOfMonth(1);
		
		IrregularExpensesBudgetDTO irregularExpensesBudgetDTO = new IrregularExpensesBudgetDTO
				(1L, 1L, date, 0, 0, null);
		
		IrregularExpensesBudgetDTO searchedIrregularExpensesBudgetDTO = new IrregularExpensesBudgetDTO
				(2L, 1L, date, 0, 0, null);
		
		when(userRepository.findByUserId(1L)).thenReturn(user);
		doReturn(searchedIrregularExpensesBudgetDTO).when(serviceUnderTest).findByUserIdAndDate(1L, date);
		
		assertThrows(IrregularExpensesBudgetAlreadyExistsException.class, () -> {
			serviceUnderTest.update(irregularExpensesBudgetDTO);
		});
		
		verify(userRepository).findByUserId(1L);
	}
	
	@Test
	void deleteByDate_ShouldNotThrow_Exception() {
		
		User user = new User();
		user.setId(1L);
		
		LocalDate date = LocalDate.now().withDayOfMonth(1);
		
		IrregularExpensesBudget irregularExpensesBudget = new IrregularExpensesBudget
				(1L, date, 0, 0, user, null);
		
		MonthlyBudget monthlyBudget = new MonthlyBudget
				(1L, date, 0, 0, null, new ArrayList<>(), new ArrayList<>());
		
		when(irregularExpensesBudgetRepository
				.findByUserIdAndDate(1L, date)).thenReturn(irregularExpensesBudget);
		when(monthlyBudgetRepository
				.findByUserIdAndDate(1L, date)).thenReturn(monthlyBudget);
		doNothing().when(irregularExpensesBudgetRepository).delete(irregularExpensesBudget);
		
		serviceUnderTest.deleteByDate(date, 1L);
		
		verify(irregularExpensesBudgetRepository).findByUserIdAndDate(1L, date);
		verify(irregularExpensesBudgetRepository).delete(irregularExpensesBudget);
	}
	
	@Test
	void testDeleteByDate() {
		fail("Not yet implemented");
	}

}
