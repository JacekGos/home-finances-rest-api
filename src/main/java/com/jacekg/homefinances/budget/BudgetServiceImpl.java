package com.jacekg.homefinances.budget;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jacekg.homefinances.expenses.model.ConstantExpense;
import com.jacekg.homefinances.expenses.model.OneTimeExpense;
import com.jacekg.homefinances.expenses.model.UserPreferenceConstantExpense;
import com.jacekg.homefinances.user.User;
import com.jacekg.homefinances.user.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BudgetServiceImpl implements BudgetService {
	
	private MonthlyBudgetRepository monthlyBudgetRepository;
	
	private UserRepository userRepository;
	
	private ModelMapper modelMapper;
	
	@Override
	public MonthlyBudget findByUserIdAndDate(Long userId, LocalDate date) {
		return monthlyBudgetRepository.findByUserIdAndDate(userId, date);
	}
	
	@Transactional
	@Override
	public MonthlyBudgetDTO save(MonthlyBudgetDTO monthlyBudgetDTO) {
		
		MonthlyBudget monthlyBudget = convertToEntity(monthlyBudgetDTO);
		
		User user = userRepository.findByUserId(monthlyBudgetDTO.getUserId());
		
		List<ConstantExpense> constantExpenses = new ArrayList<ConstantExpense>();
		List<OneTimeExpense> oneTimeExpenses = new ArrayList<OneTimeExpense>();
		
		for (UserPreferenceConstantExpense preferencedConstantExpense : user.getUserPreferenceConstantExpenses()) {
			
			ConstantExpense constantExpense = new ConstantExpense(
					preferencedConstantExpense.getName(), 0, 0);
			
			constantExpenses.add(constantExpense);
			System.out.println("const expense: " + constantExpense);
		}
		
		monthlyBudget.setConstantExpenses(constantExpenses);
		monthlyBudget.setOneTimeExpenses(oneTimeExpenses);
		monthlyBudget.setUser(user);
		
		return convertToDTO(monthlyBudgetRepository.save(monthlyBudget));
	}
	
	private MonthlyBudgetDTO convertToDTO(MonthlyBudget monthlyBudget) {
		
		MonthlyBudgetDTO monthlyBudgetDTO = modelMapper.map(monthlyBudget, MonthlyBudgetDTO.class);
		
		return monthlyBudgetDTO;
	}
	
	private MonthlyBudget convertToEntity(MonthlyBudgetDTO monthlyBudgetDTO) {
		
		MonthlyBudget monthlyBudget = modelMapper.map(monthlyBudgetDTO, MonthlyBudget.class);
		
		return monthlyBudget;
	}

}
