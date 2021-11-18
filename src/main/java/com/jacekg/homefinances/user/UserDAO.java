package com.jacekg.homefinances.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface UserDAO extends JpaRepository<User, Long> {
	
	User findByUsername(String username);
}
