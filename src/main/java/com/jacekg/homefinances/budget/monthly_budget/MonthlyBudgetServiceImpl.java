package com.jacekg.homefinances.budget.monthly_budget;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//import javax.transaction.Transactional;
import org.springframework.transaction.annotation.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.jacekg.homefinances.budget.BudgetUtilities;
import com.jacekg.homefinances.budget.irregular_expenses_budget.IrregularExpensesBudget;
import com.jacekg.homefinances.budget.irregular_expenses_budget.IrregularExpensesBudgetRepository;
import com.jacekg.homefinances.expenses.ConstantExpenseRepository;
import com.jacekg.homefinances.expenses.OneTimeExpenseRepository;
import com.jacekg.homefinances.expenses.model.ConstantExpense;
import com.jacekg.homefinances.expenses.model.OneTimeExpense;
import com.jacekg.homefinances.expenses.model.UserPreferenceConstantExpense;
import com.jacekg.homefinances.user.User;
import com.jacekg.homefinances.user.UserNotExistsException;
import com.jacekg.homefinances.user.UserRepository;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
@AllArgsConstructor
public class MonthlyBudgetServiceImpl implements MonthlyBudgetService {

	private MonthlyBudgetRepository monthlyBudgetRepository;

	private UserRepository userRepository;
	
	private ConstantExpenseRepository constantExpenseRepository;
	
	private OneTimeExpenseRepository oneTimeExpenseRepository;
	
	private IrregularExpensesBudgetRepository irregularExpensesBudgetRepository;

	private ModelMapper modelMapper;

	@Override
	@Transactional
	public MonthlyBudgetDTO findByUserIdAndDate(Long userId, LocalDate date) {
		
		MonthlyBudget monthlyBudget = monthlyBudgetRepository.findByUserIdAndDate(userId, date);
		
		if (monthlyBudget == null) {
			return null;
		} else {
			return modelMapper.map(monthlyBudget, MonthlyBudgetDTO.class);
		}
	}

	@Override
	@Transactional
	public MonthlyBudgetDTO save(MonthlyBudgetDTO monthlyBudgetDTO) {
		
		if (findByUserIdAndDate(monthlyBudgetDTO.getUserId(), monthlyBudgetDTO.getDate()) != null) {
			throw new MonthlyBudgetAlreadyExistsException("Budżet na dany miesiąc istnieje!");
		}

		MonthlyBudget monthlyBudget = modelMapper.map(monthlyBudgetDTO, MonthlyBudget.class);

		User user = userRepository.findByUserId(monthlyBudgetDTO.getUserId());

		List<ConstantExpense> constantExpenses = new ArrayList<ConstantExpense>();
		List<OneTimeExpense> oneTimeExpenses = new ArrayList<OneTimeExpense>();
		
		System.out.println("prefs: " + user.getUserPreferenceConstantExpenses());
		
		for (UserPreferenceConstantExpense preferencedConstantExpense : user.getUserPreferenceConstantExpenses()) {
			ConstantExpense constantExpense = new ConstantExpense(preferencedConstantExpense.getName(), 0, 0);
			constantExpenses.add(constantExpense);
		}
		
		IrregularExpensesBudget irregularExpensesBudget
			= irregularExpensesBudgetRepository
				.findByUserIdAndDate(user.getId(), LocalDate.now().withMonth(1).withDayOfMonth(1));
		
		if (irregularExpensesBudget != null) {
			
			ConstantExpense irregularExpense = new ConstantExpense(
					"wydatki nieregularne",
					irregularExpensesBudget.getNecessaryMonthlySavings(),
					0);

			constantExpenses.add(irregularExpense);
		}
		
		monthlyBudget.setConstantExpenses(constantExpenses);
		monthlyBudget.setOneTimeExpenses(oneTimeExpenses); 
		monthlyBudget.setUser(user);
		
		monthlyBudget.setFinalBalance(BudgetUtilities.calculateFinalBalance
				(monthlyBudget.getConstantExpenses(),
						monthlyBudget.getOneTimeExpenses(), 
						monthlyBudget.getPreviousMonthEarnings()));

		return modelMapper.map(monthlyBudgetRepository.save(monthlyBudget), MonthlyBudgetDTO.class);
	}

	@Override
	@Transactional
	public List<MonthlyBudgetDTO> findAllByUserId(Long userId) {
		
		return monthlyBudgetRepository.findAllByUserId(userId)
				.stream()
				.map(budget -> modelMapper.map(budget, MonthlyBudgetDTO.class))
				.collect(Collectors.toList());
	}

	@Override
	@Transactional
	public MonthlyBudgetDTO update(MonthlyBudgetDTO monthlyBudgetDTO) {
		
		User user = userRepository.findByUserId(monthlyBudgetDTO.getUserId());
		
		if (user == null) {
			throw new UserNotExistsException("Dany użytkownik nie istnieje!");
		}
		
		System.out.println("current budget: " + findByUserIdAndDate(user.getId(), monthlyBudgetDTO.getDate()) + "  |");
		
		if (findByUserIdAndDate(user.getId(), monthlyBudgetDTO.getDate()).getId() != monthlyBudgetDTO.getId()) {
			throw new MonthlyBudgetAlreadyExistsException
				("Budżet o podanym Id: " + monthlyBudgetDTO.getId() + ", nie istnieje w danym miesiącu!");
		}
		
		MonthlyBudget monthlyBudget = modelMapper.map(monthlyBudgetDTO, MonthlyBudget.class);
		monthlyBudget.setUser(user);
		
		List<ConstantExpense> currentConstantExpenses 
			= constantExpenseRepository.findAllByMonthlyBudgetId(monthlyBudget.getId());
		List<ConstantExpense> updatedConstantExpenses
			= monthlyBudget.getConstantExpenses();
		
		List<OneTimeExpense> currentOneTimeExpenses 
			= oneTimeExpenseRepository.findAllByMonthlyBudgetId(monthlyBudget.getId());
		List<OneTimeExpense> updatedOneTimeExpenses
			= monthlyBudget.getOneTimeExpenses();
		
		monthlyBudget.setConstantExpenses(BudgetUtilities.removeDuplicatedExpenses
				(currentConstantExpenses, updatedConstantExpenses)); 
		monthlyBudget.setOneTimeExpenses(BudgetUtilities.removeDuplicatedExpenses
				(currentOneTimeExpenses, updatedOneTimeExpenses)); 
		
		updatedConstantExpenses = BudgetUtilities.manageIrregularConstantExpense
				(updatedConstantExpenses, currentConstantExpenses);
		
		monthlyBudget.setFinalBalance(BudgetUtilities.calculateFinalBalance
				(monthlyBudget.getConstantExpenses(),
						monthlyBudget.getOneTimeExpenses(), 
						monthlyBudget.getPreviousMonthEarnings()));
		
		return modelMapper.map(monthlyBudgetRepository.save(monthlyBudget), MonthlyBudgetDTO.class);
	}

	@Override
	@Transactional
	public void deleteByDate(LocalDate monthlyBudgetDate, Long userId) {
		
		MonthlyBudget monthlyBudget = monthlyBudgetRepository.findByUserIdAndDate(userId, monthlyBudgetDate);
		
		if (monthlyBudget == null) {
			throw new MonthlyBudgetDoesntExistsException("Budget with this date doesn't exists!");
		}
		
		monthlyBudgetRepository.delete(monthlyBudget);
	}
}











