package com.amruta.familytree.controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.amruta.familytree.ContactRepo;
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
}
