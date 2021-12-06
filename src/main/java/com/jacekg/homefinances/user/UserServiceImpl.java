package com.jacekg.homefinances.user;

import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.modelmapper.spi.Mapping;
import org.modelmapper.spi.MappingContext;
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
//		this.modelMapper.typeMap(UserDTO.class, User.class)
//			.addMapping(UserDTO::getRoleName, User::setRole);
//		this.modelMapper.typeMap(User.class, UserDTO.class)
//			.addMapping(User::getRoleName, UserDTO::setRole);
	}
	
	@Transactional
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
		
//		User user = convertToEntity(userDTO);
//		user.setEnabled(true);
//		user.setNonExpired(true);
//		user.setCredentialsNonExpired(true);
//		user.setNonLocked(true);
//		System.out.println("created user: " + user.toString());
		
		System.out.println("getRoleName: " + user.getRoleName());
		UserDTO returnedUserDTO = convertToDTO(user);
		System.out.println("test user: " + returnedUserDTO);
		
		return convertToDTO(userRepository.save(user));
	}
	
	@Transactional
	@Override
	public List<User> findAll() {
		
		List<User> users = userRepository.findAll();
		System.out.println("users: " + users.get(0).toString());
		
		return users;
	}

	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}
	
	@Override
	public User findByUserId(Long userId) {
		return userRepository.findByUserId(userId);
	}
	
//	private UserDTO convertToDTO(User user) {
//		
//		UserDTO userDTO = modelMapper.map(user, UserDTO.class);
//		
//		return userDTO;
//	}
	
	private UserDTO convertToDTO(User user) {
		
		UserDTO userDTO = modelMapper.map(user, UserDTO.class);
		
		return userDTO;
	}
	
//	Converter<User, UserDTO> userDTOConverter = new Converter<User, UserDTO>() {
//
//		@Override
//		public UserDTO convert(MappingContext<User, UserDTO> context) {
//
//			User source = context.getSource();
//			UserDTO destination = context.getDestination();
//			
//			System.out.println("convert: " + source.getRoleName());
//			
//			destination.setRole(source.getRoleName());
//			return destination;
//		}
//	};
	
	private User convertToEntity(UserDTO userDTO) {

		User user = modelMapper.map(userDTO, User.class);
		return user;
	}

//	Converter<UserDTO, User> userConverter = new Converter<UserDTO, User>() {
//
//		@Override
//		public User convert(MappingContext<UserDTO, User> context) {
//			UserDTO userDTO = context.getSource();
//			User user = context.getDestination();
//			user.setRoles(() -> {
//				return Arrays.asList(roleRepository.findByName("ROLE_USER"));
//			});
//			return user;
//		}
//	};
}








