package com.jacekg.homefinances.budget;

import java.time.LocalDate;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	@Override
	public MonthlyBudgetDTO save(MonthlyBudgetDTO monthlyBudgetDTO) {
		
		System.out.println("start save...1");
		MonthlyBudget monthlyBudget = convertToEntity(monthlyBudgetDTO);
		System.out.println("monthlybudget: " + monthlyBudget.toString());
		User user = userRepository.findByUserId(monthlyBudgetDTO.getUserId());
		System.out.println("user: " + user.toString());
		System.out.println("start save...2");
		monthlyBudget.setUser(user);
		System.out.println("monthly budget: " + monthlyBudget);
//		monthlyBudget.setUser(userRepository.findByUserId(monthlyBudget.getUser().getId()));
//		System.out.println("user: " + monthlyBudget.getUser().toString());
//		monthlyBudget.setConstantExpenses(monthlyBudget.getConstantExpenses());
//		System.out.println("budget: " + monthlyBudget.toString());
		return convertToDTO(monthlyBudgetRepository.save(monthlyBudget));
	}
	
	private MonthlyBudgetDTO convertToDTO(MonthlyBudget monthlyBudget) {
		
		MonthlyBudgetDTO monthlyBudgetDTO = modelMapper.map(monthlyBudget, MonthlyBudgetDTO.class);
		
		return monthlyBudgetDTO;
	}
	
	private MonthlyBudget convertToEntity(MonthlyBudgetDTO monthlyBudgetDTO) {
		
//		modelMapper.addMappings(new PropertyMap<MonthlyBudgetDTO, MonthlyBudget>() {
//
//			@Override
//			protected void configure() {
//				skip(destination.getUser());
//			}
//		});
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		MonthlyBudget monthlyBudget = modelMapper.map(monthlyBudgetDTO, MonthlyBudget.class);
		
		return monthlyBudget;
	}

}
