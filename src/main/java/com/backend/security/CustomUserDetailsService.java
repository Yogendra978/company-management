package com.backend.security;

import java.util.ArrayList;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;


@Component
public class CustomUserDetailsService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if(username.equals("yogendra")) {
			return new User("yogendra","12345",new ArrayList<>());
		}else {
			throw new UsernameNotFoundException("User not Found !");
		}
	}

}