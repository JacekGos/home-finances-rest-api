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
import com.jacekg.homefinances.expenses.ConstantExpenseRepository;
import com.jacekg.homefinances.expenses.OneTimeExpenseRepository;
import com.jacekg.homefinances.expenses.model.ConstantExpense;
import com.jacekg.homefinances.expenses.model.OneTimeExpense;
import com.jacekg.homefinances.expenses.model.UserPreferenceConstantExpense;
import com.jacekg.homefinances.user.User;
import com.jacekg.homefinances.user.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MonthlyBudgetServiceImpl implements MonthlyBudgetService {

	private MonthlyBudgetRepository monthlyBudgetRepository;

	private UserRepository userRepository;
	
	private ConstantExpenseRepository constantExpenseRepository;
	
	private OneTimeExpenseRepository oneTimeExpenseRepository;

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

		for (UserPreferenceConstantExpense preferencedConstantExpense : user.getUserPreferenceConstantExpenses()) {
			ConstantExpense constantExpense = new ConstantExpense(preferencedConstantExpense.getName(), 0, 0);
			constantExpenses.add(constantExpense);
		}

		monthlyBudget.setConstantExpenses(constantExpenses);
		monthlyBudget.setOneTimeExpenses(oneTimeExpenses); 
		monthlyBudget.setUser(user);

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
		
		MonthlyBudget monthlyBudget = modelMapper.map(monthlyBudgetDTO, MonthlyBudget.class);
		
		User user = userRepository.findByUserId(monthlyBudgetDTO.getUserId());
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










