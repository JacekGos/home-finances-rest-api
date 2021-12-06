package com.jacekg.homefinances.user;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRestController {

	private UserService userService;

	@Autowired
	public UserRestController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping("/signup")
	public UserDTO addUser(@Valid @RequestBody UserDTO userDTO) {
		
		if (userService.findByUsername(userDTO.getUsername()) != null) {
			throw new UserNotValidException("Podana nazwa jest zajęta");
		}
		
		userDTO = userService.save(userDTO);
		userDTO.setPassword(null);
		
		return userDTO;
	}

	@GetMapping("/users")
	public List<User> findAll() {
		return userService.findAll();
	}
}








