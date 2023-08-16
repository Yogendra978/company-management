package com.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.backend.entity.User;
import com.backend.repository.UserRepository;


@Controller
public class HomeController {
	

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@GetMapping("/signin")
	public String customLoginPage(Model model) {
		model.addAttribute("title","Sign In");
		return "signin";
	}
	
	@GetMapping("/signup")
	public String ragisterNow(Model model) {
		model.addAttribute("title","Sign Up");
		return "signup";
	}
	
	@PostMapping("/signup")
	public String saveAllInfo(User user,Model model) {
		User newuser = new User();
		newuser.setName(user.getName());
		newuser.setEmail(user.getEmail());
		newuser.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		newuser.setRole(user.getRole());
		newuser.setEnabled(user.isEnabled());
		userRepository.save(newuser);
		System.out.println(newuser);
		model.addAttribute("msg","Completed Signup user !!");
		return "redirect:/signup";
	}
	
	
	
	

}
