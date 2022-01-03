package com.amruta.familytree.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.amruta.familytree.ContactRepo;
import com.amruta.familytree.MemberRepo;
import com.amruta.familytree.Profile;
import com.amruta.familytree.RelationRepo;
import com.amruta.familytree.domain.Contact;
import com.amruta.familytree.domain.Member;
import com.amruta.familytree.domain.Relation;

@Controller
public class DataController {
	@Autowired
	private MemberRepo memberRepo;
	
	@Autowired
	private ContactRepo contactRepo;
	
	@Autowired
	private RelationRepo relationRepo;
	
	private ProfileConverter profileConverter;
	
	@GetMapping("/profiles/{member_id}")
	public ResponseEntity<Profile> getMember(@PathVariable("member_id") Long member_id) {
		Optional<Member> member=memberRepo.findById(member_id);
		Profile profile = null;
		if(member.isPresent()) {
			profile=profileConverter.convertMemberToProfile(member.get());
		}
		return new ResponseEntity<>(profile,HttpStatus.OK);
	} 
	
}
