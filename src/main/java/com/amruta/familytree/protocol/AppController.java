package com.amruta.familytree.protocol;

import com.amruta.familytree.domain.Member;
import com.amruta.familytree.domain.MemberRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AppController
{

	@Autowired
	private MemberRepo memberRepo;
	
	@GetMapping("")
	public String viewHomePage() {
		return "index";
	}
	
	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
		model.addAttribute("user", new Member());
		
		return "signup_form";
	}
	
	@PostMapping("/process_register")
	public String processRegister(Member user) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		
		memberRepo.save(user);
		
		return "register_success";
	}
	
	@GetMapping("/users")
	public String listUsers(Model model) {
		List<Member> listUsers = memberRepo.findAll();
		model.addAttribute("listUsers", listUsers);
		
		return "users";
	}
}
