package com.amruta.familytree.controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.amruta.familytree.ContactRepo;
import com.amruta.familytree.MemberRepo;
import com.amruta.familytree.Profile;
import com.amruta.familytree.RelationRepo;
import com.amruta.familytree.domain.Contact;
import com.amruta.familytree.domain.Member;
import com.amruta.familytree.domain.Relation;

public class ProfileConverter {
	@Autowired
	private ContactRepo contactRepo;
	
	@Autowired
	private RelationRepo relationRepo;
	
	@Autowired
	private MemberRepo memberRepo;
	public Profile convertMemberToProfile(Member member)
	{
			Contact contactDomain=contactRepo.findByMemberId(member.getMemberId()).get();
			Relation relationDomain=relationRepo.findByMemberId(member.getMemberId()).get();
			Profile profile = new Profile();
			com.amruta.familytree.Contact contact=new com.amruta.familytree.Contact();
			com.amruta.familytree.Relation relation=new com.amruta.familytree.Relation();
			contact.setFirstName(contactDomain.getFirstName());
			contact.setLastName(contactDomain.getLastName());
			contact.setEmailId(contactDomain.getMailId());
			contact.setPrimaryPhoneNumber(contactDomain.getPrimaryPhoneNumber());
			contact.setSecondaryPhoneNumber(contactDomain.getSecondaryPhoneNyumber());
			relation.setFather_id(relationDomain.getFatherId());
			relation.setMother_id(relationDomain.getMotherId());
			relation.setSpouse_id(relationDomain.getSpouseId());
			relation.setGender(relationDomain.getGender());
			profile.setContact(contact);
			profile.setRelation(relation);
			return profile;
	}
	
	public Member convertProfileToDomain(Profile profile)
	{
		com.amruta.familytree.domain.Member memberDomain=new com.amruta.familytree.domain.Member();
		com.amruta.familytree.domain.Contact contactDomain=new com.amruta.familytree.domain.Contact();
		com.amruta.familytree.domain.Relation relationDomain=new com.amruta.familytree.domain.Relation();
		memberDomain.setPassword(profile.getPassword());
		memberDomain.setUsername(profile.getUserName());
		contactDomain.setFirstName(profile.getContact().getFirstName());
		contactDomain.setLastName(profile.getContact().getLastName());
		contactDomain.setMailId(profile.getContact().getEmailId());
		contactDomain.setPrimaryPhoneNumber(profile.getContact().getPrimaryPhoneNumber());
		contactDomain.setSecondaryPhoneNyumber(profile.getContact().getSecondaryPhoneNumber());
		relationDomain.setFatherId(profile.getRelation().getFather_id());
		relationDomain.setMotherId(profile.getRelation().getMother_id());
		relationDomain.setSpouseId(profile.getRelation().getSpouse_id());
		relationDomain.setGender(profile.getRelation().getGender());
		com.amruta.familytree.domain.Member memberSaved=memberRepo.save(memberDomain);
		contactRepo.save(contactDomain);
		relationRepo.save(relationDomain);
		return memberSaved;
	}
}
