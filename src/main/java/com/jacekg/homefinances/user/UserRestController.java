package com.jacekg.homefinances.user;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/users")
public class UserRestController {
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public User addUser(@RequestBody User user) {
		
		
		
		return user;
	}
}
