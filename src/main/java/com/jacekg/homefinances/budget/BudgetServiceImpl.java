package com.jacekg.homefinances.budget;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jacekg.homefinances.expenses.model.UserPreferenceConstantExpense;
import com.jacekg.homefinances.user.User;
import com.jacekg.homefinances.user.UserRepository;

@Service
public class BudgetServiceImpl implements BudgetService {
	
	private MonthlyBudgetRepository monthlyBudgetRepository;
	
	private UserRepository userRepository;
	
	private ModelMapper modelMapper;
	
	@Autowired
	public BudgetServiceImpl(MonthlyBudgetRepository monthlyBudgetRepository, ModelMapper modelMapper, UserRepository userRepository) {
		
		this.monthlyBudgetRepository = monthlyBudgetRepository;
		this.modelMapper = modelMapper;
		this.userRepository = userRepository;
	}
	
	@Override
	public MonthlyBudget findByUserIdAndDate(Long userId, LocalDate date) {
		return monthlyBudgetRepository.findByUserIdAndDate(userId, date);
	}
	
	@Transactional
	@Override
	public MonthlyBudgetDTO save(MonthlyBudgetDTO monthlyBudgetDTO) {
		
		MonthlyBudget monthlyBudget = convertToEntity(monthlyBudgetDTO);
		
		User user = userRepository.findByUserId(monthlyBudgetDTO.getUserId());
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
