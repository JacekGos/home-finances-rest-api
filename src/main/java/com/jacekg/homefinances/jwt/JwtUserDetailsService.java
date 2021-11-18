package com.jacekg.homefinances.jwt;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jacekg.homefinances.role.Role;
import com.jacekg.homefinances.user.User;
import com.jacekg.homefinances.user.UserDAO;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	private UserDAO userDAO;

	@Autowired
	public JwtUserDetailsService(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userDAO.findByUsername(username);

		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password");
		}
		
		return new org.springframework.security.core.userdetails.User(
				user.getUserName(), 
				user.getPassword(),
				user.isEnabled(),
				user.isNonExpired(),
				user.isCredentialsNonExpired(),
				user.isNonLocked(),
				mapRolesToAuthorities(user.getRoles()));
	}
	
	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}
}
