package com.jacekg.homefinances;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.jacekg.homefinances.user.User;
import com.jacekg.homefinances.user.UserDTO;

@SpringBootApplication
public class HomeFinancesApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomeFinancesApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		
		ModelMapper modelMapper = new ModelMapper();
		
		modelMapper.addMappings(new PropertyMap<User, UserDTO>() {
			protected void configure() {
				map().setRole(source.getRoleName());
			}
		});
		
		return modelMapper;
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
