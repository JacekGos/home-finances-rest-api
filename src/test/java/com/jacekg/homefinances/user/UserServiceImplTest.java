package com.jacekg.homefinances.user;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.jacekg.homefinances.role.RoleRepository;

class UserServiceImplTest {

	@Mock
	private UserRepository userRepository;
	
	@Mock
	private RoleRepository roleRepository;
	
	@InjectMocks
	UserServiceImpl service;

	@Test
	void save_ShouldReturn_User() {
		
		
		
	}

}
