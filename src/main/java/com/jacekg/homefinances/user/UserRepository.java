package com.jacekg.homefinances.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {
	
	@Query("FROM User u JOIN FETCH u.roles WHERE username=:username ")
	User findByUsername(@Param("username") String username);
	
	@Query("FROM User WHERE id=:userId")
	User findByUserId(@Param("userId") Long userId);
}
