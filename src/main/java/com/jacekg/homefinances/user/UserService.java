package com.jacekg.homefinances.user;

import java.util.List;

public interface UserService {
	
	public UserDTO save(UserDTO user);
	
	public User findByUsername(String username);
	
	public User findByUserId(Long userId);
}
