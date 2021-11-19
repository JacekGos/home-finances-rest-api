package com.jacekg.homefinances.user;

import java.util.List;

public interface UserService {
	
	public User save(UserDTO user);
	
	public List<User> findAll();
}
