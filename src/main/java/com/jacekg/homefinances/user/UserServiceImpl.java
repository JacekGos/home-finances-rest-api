package com.jacekg.homefinances.user;

import java.util.Arrays;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.jacekg.homefinances.role.RoleRepository;

@Service
public class UserServiceImpl implements UserService {
	
	private UserRepository userRepository;
	
	private RoleRepository roleRepository;
	
	private BCryptPasswordEncoder passwordEncoder;
	
	private ModelMapper modelMapper;
	
	@Autowired
	public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder, ModelMapper modelMapper) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.passwordEncoder = passwordEncoder;
		this.modelMapper = modelMapper;
	}
	
	@Override
	public UserDTO save(UserDTO userDTO) {
		
		User user = new User();
		
		user.setId(userDTO.getId());
		user.setUsername(userDTO.getUsername());
		user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
		user.setFirstName(StringUtils.capitalize(userDTO.getFirstName()));
		user.setLastName(StringUtils.capitalize(userDTO.getLastName()));
		user.setEmail(userDTO.getEmail());
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
		
		return convertToDTO(userRepository.save(user));
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}
	
	@Override
	public User findByUserId(Long userId) {
		return userRepository.findByUserId(userId);
	}
	
	private UserDTO convertToDTO(User user) {
		
		UserDTO userDTO = modelMapper.map(user, UserDTO.class);
		return userDTO;
	}
	
	private User convertToEntity(UserDTO userDTO) {
		
		User user = modelMapper.map(userDTO, User.class);
		return user;
	}
}








