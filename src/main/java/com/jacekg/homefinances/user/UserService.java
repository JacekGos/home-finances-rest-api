package com.jacekg.homefinances.user;

import java.util.List;

public interface UserService {
	
	public void save(UserDTO user);
	
	public List<User> findAll();
}
