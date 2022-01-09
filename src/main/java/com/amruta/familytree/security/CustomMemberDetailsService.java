package com.amruta.familytree.security;

import com.amruta.familytree.domain.Member;
import com.amruta.familytree.domain.MemberRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomMemberDetailsService implements UserDetailsService {

	@Autowired
	private MemberRepo memberRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if (!memberRepo.findMemberByContactEmailId(username).isPresent()) {
			throw new UsernameNotFoundException("User not found");
		}
		Member user = memberRepo.findMemberByContactEmailId(username).get();
		return new CustomMemberDetails(user);
	}

}
