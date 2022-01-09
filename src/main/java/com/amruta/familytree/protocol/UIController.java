package com.amruta.familytree.protocol;

import com.amruta.familytree.domain.Member;
import com.amruta.familytree.domain.MemberRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class UIController
{

	@Autowired
	private MemberRepo memberRepo;

	@Autowired
	private ProfileConverter profileConverter;
	
	@GetMapping("")
	public String viewHomePage() {
		return "index";
	}
	
	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
		model.addAttribute("user", new Member());
		List<Member> existingUsers = memberRepo.findAll();
		model.addAttribute("listUsers", existingUsers);
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
		List<Member> members = memberRepo.findAll();
		List<ProfileResponse> profileResponseList = new ArrayList<>();
		for (Member member:
			 members)
		{
			profileResponseList.add(profileConverter.convertDomainToProfileResponse(member));
		}

		model.addAttribute("listUsers", profileResponseList);
		
		return "users";
	}
}
