package com.backend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.backend.entity.User;
import com.backend.repository.UserRepository;

@Service
public class CusotmUserDetailsService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		//fetching user from database
		User user = userRepository.getUserByUserName(username); 
		
		if(user==null) {
			throw new UsernameNotFoundException("Could not found User !!");
		}
		
		CustomUserDetails CustomUserDetails = new CustomUserDetails(user);
		return CustomUserDetails;
	}

}
