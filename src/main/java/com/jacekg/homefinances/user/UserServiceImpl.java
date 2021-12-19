package com.jacekg.homefinances.user;

import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.jacekg.homefinances.role.RoleRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
	
	private UserRepository userRepository;
	
	private RoleRepository roleRepository;
	
	private BCryptPasswordEncoder passwordEncoder;
	
	private ModelMapper modelMapper;
	
	@PostConstruct
	public void postConstruct() {
		
		modelMapper.addMappings(new PropertyMap<User, UserDTO>() {
			protected void configure() {
				map().setRole(source.getRoleName());
			}
		});
	}
	
	@Override
	@Transactional
	public UserDTO save(UserDTO userDTO) {
		
		if (userRepository.findByUsername(userDTO.getUsername()) != null) {
			throw new UserNotValidException("Podana nazwa jest zajęta");
		} 
		
		userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
		
		User user = modelMapper.map(userDTO, User.class);
		user.setEnabled(true);
		user.setNonExpired(true);
		user.setCredentialsNonExpired(true);
		user.setNonLocked(true);
		
		if (userDTO.getRole().equals("USER")) {
			user.setRoles(Arrays.asList(roleRepository.findByName("ROLE_USER")));
		} 
		else if (userDTO.getRole().equals("ADMIN")) {
			user.setRoles(Arrays.asList(
					roleRepository.findByName("ROLE_USER"),
					roleRepository.findByName("ROLE_ADMIN")));
		} else {
			user.setRoles(Arrays.asList(roleRepository.findByName("ROLE_USER")));
		}
		 
		UserDTO responseUser = modelMapper.map(userRepository.save(user), UserDTO.class);
		responseUser.setPassword(null);
		
		return responseUser;
	}
	
	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}
	
	@Override
	public User findByUserId(Long userId) {
		return userRepository.findByUserId(userId);
	}
}








