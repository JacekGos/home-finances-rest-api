package com.jacekg.homefinances.user;

import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;


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
		
		User addedUser = new User();
		UserDTO returnedUser = new UserDTO();
		
		try {
			addedUser = userService.save(userDTO);
			userDTO.setId(addedUser.getId());
			userDTO.setEnabled(true);
			userDTO.setCredentialsNonExpired(true);
			userDTO.setNonExpired(true);
			userDTO.setNonLocked(true);
		} catch (ConstraintViolationException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
		}
		
		return userDTO;
	}
	
	@GetMapping("/users")
	public List<User> findAll() {
		return userService.findAll();
	}
}





