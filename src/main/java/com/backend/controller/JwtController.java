package com.backend.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.backend.config.CusotmUserDetailsService;
import com.backend.entity.JwtResponse;
import com.backend.entity.User;
import com.backend.repository.UserRepository;
import com.backend.security.JwtUtil;

@RestController
public class JwtController {
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	@Autowired
	private CusotmUserDetailsService cusotmUserDetailsService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@RequestMapping(value = "/token/{id}",method = RequestMethod.POST)
	public ResponseEntity<?> generateToken(@RequestBody User user,@PathVariable long id) throws Exception{
		
		User byId = repository.findById(id);

		System.out.println(byId.getEmail() + user.getPassword());
		try {
			this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(byId.getEmail(),user.getPassword()) );
			
			
		} catch (UsernameNotFoundException e) {
			e.printStackTrace();
			throw new Exception("Bed Credentials");
		}
		
		UserDetails userDetails = this.cusotmUserDetailsService.loadUserByUsername(byId.getEmail());
		String token = this.jwtUtil.generateToken(userDetails);
		System.out.println("JWT"+ token );
		
		return ResponseEntity.ok(new JwtResponse(token));

	}

}
