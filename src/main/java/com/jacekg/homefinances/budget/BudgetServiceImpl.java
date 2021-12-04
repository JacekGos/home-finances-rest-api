package com.jacekg.homefinances.budget;

import java.time.LocalDate;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BudgetServiceImpl implements BudgetService {
	
	private MonthlyBudgetRepository monthlyBudgetRepository;
	
	private ModelMapper modelMapper;
	
	@Autowired
	public BudgetServiceImpl(MonthlyBudgetRepository monthlyBudgetRepository, ModelMapper modelMapper) {
		this.monthlyBudgetRepository = monthlyBudgetRepository;
		this.modelMapper = modelMapper;
	}
	
	@Override
	public MonthlyBudget findByUserIdAndDate(Long userId, LocalDate date) {
		return monthlyBudgetRepository.findByUserIdAndDate(userId, date);
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
