package com.jacekg.homefinances.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("/users")
public class UserRestController {
	
	private UserService userService;
	
	@Autowired
	public UserRestController(UserService userService) {
		this.userService = userService;
	}
	
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public UserDTO addUser(@RequestBody UserDTO userDTO) {
		
		userService.save(userDTO);
		
		userDTO.setEnabled(true);
		userDTO.setCredentialsNonExpired(true);
		userDTO.setNonExpired(true);
		userDTO.setNonLocked(true);
		
		return userDTO;
	}
}
