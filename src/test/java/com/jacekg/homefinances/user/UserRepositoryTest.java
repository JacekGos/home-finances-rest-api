package com.jacekg.homefinances.user;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.jacekg.homefinances.role.Role;

@DataJpaTest
class UserRepositoryTest {
	
	@Autowired
	private UserRepository userRepository;
	
	private User user;
	
	@Test
	void findByUsername_ShouldReturn_Valid_User() {
		
		String username = "user";
		
		User user = new User(
				1L,
				"user",
				"password",
				"name",
				"lastName",
				"email@",
				true,
				true,
				true,
				true, 
				null, 
				null, 
				Arrays.asList(new Role(1L, "ROLE_USER"), new Role(2L, "ROLE_ADMIN")));
		
		userRepository.save(user);
		
		User returnedUser = userRepository.findByUsername(username);
		
		assertEquals("user", returnedUser.getUsername());
	}

	@Test
	void testFindByUserId_ShouldReturn_Valid_User() {
		
		Long userID = 1L;
		
		User user = new User(
				1L,
				"user",
				"password",
				"name",
				"lastName",
				"email@",
				true,
				true,
				true,
				true, 
				null, 
				null, 
				Arrays.asList(new Role(1L, "ROLE_USER"), new Role(2L, "ROLE_ADMIN")));
		
		userRepository.save(user);
		
		User returnedUser = userRepository.findByUserId(userID);
		
		assertEquals(1L, returnedUser.getId());
	}
}









