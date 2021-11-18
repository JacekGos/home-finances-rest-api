package com.jacekg.homefinances.user;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.jacekg.homefinances.role.RoleDAO;

@Service
public class UserServiceImpl implements UserService {
	
	private UserDAO userDAO;
	
	private RoleDAO roleDAO;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	public UserServiceImpl(UserDAO userDAO, RoleDAO roleDAO) {
		this.userDAO = userDAO;
		this.roleDAO = roleDAO;
	}
	
	@Override
	public void save(UserDTO userDTO) {
		
		User user = new User();
		
		user.setId(userDTO.getId());
		user.setUserName(userDTO.getUsername());
		user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
		user.setFirstName(StringUtils.capitalize(userDTO.getFirstName()));
		user.setLastName(StringUtils.capitalize(userDTO.getLastName()));
		user.setEmail(userDTO.getEmail());
		user.setEnabled(true);
		user.setNonExpired(true);
		user.setCredentialsNonExpired(true);
		user.setNonLocked(true);
		
		if (userDTO.getRole().equals("USER")) {
			user.setRoles(Arrays.asList(roleDAO.findByName("ROLE_USER")));
		} 
		else if (userDTO.getRole().equals("ADMIN")) {
			user.setRoles(Arrays.asList(
					roleDAO.findByName("ROLE_USER"),
					roleDAO.findByName("ROLE_ADMIN")));
		} 
		
		userDAO.save(user);
	}

}
