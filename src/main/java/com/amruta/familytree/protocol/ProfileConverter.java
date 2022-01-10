package com.amruta.familytree.protocol;

import com.amruta.familytree.domain.Contact;
import com.amruta.familytree.domain.Member;
import com.amruta.familytree.domain.MemberRepo;
import com.amruta.familytree.domain.Relation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ProfileConverter
{
    @Autowired
    private MemberRepo memberRepo;

    public ProfileResponse convertDomainToProfileResponse(Member member)
    {
        ProfileResponse profile = new ProfileResponse();
        profile.setMemberId(member.getMemberId());
        if (member.getContact() != null)
        {
            Contact contactDomain = member.getContact();
            com.amruta.familytree.protocol.Contact contact = new com.amruta.familytree.protocol.Contact();
            contact.setFirstName(contactDomain.getFirstName());
            contact.setLastName(contactDomain.getLastName());
            contact.setEmailId(contactDomain.getEmailId());
            contact.setPrimaryPhoneNumber(contactDomain.getPrimaryPhoneNumber());
            contact.setSecondaryPhoneNumber(contactDomain.getSecondaryPhoneNumber());
            contact.setGender(contactDomain.getGender());
            profile.setContact(contact);
        }
        com.amruta.familytree.protocol.RelationResponse relation = new com.amruta.familytree.protocol.RelationResponse();
        relation.setFather(getFatherFromDomain(member));
        relation.setMother(getMotherFromDomain(member));
        relation.setSpouse(getSpouseFromDomain(member));
        relation.setChildren(getChildrenFromDomain(member));
        profile.setRelation(relation);
        return profile;
    }

    public ProfileRequest convertDomainToProfileRequest(Member member)
    {
        ProfileRequest profile = new ProfileRequest();
        if (member.getContact() != null)
        {
            Contact contactDomain = member.getContact();
            com.amruta.familytree.protocol.Contact contact = new com.amruta.familytree.protocol.Contact();
            contact.setFirstName(contactDomain.getFirstName());
            contact.setLastName(contactDomain.getLastName());
            contact.setEmailId(contactDomain.getEmailId());
            contact.setPrimaryPhoneNumber(contactDomain.getPrimaryPhoneNumber());
            contact.setSecondaryPhoneNumber(contactDomain.getSecondaryPhoneNumber());
            contact.setGender(contactDomain.getGender());
            profile.setContact(contact);
        }
        if (member.getRelation() != null)
        {
            Relation relationDomain = member.getRelation();
            com.amruta.familytree.protocol.RelationRequest relation = new com.amruta.familytree.protocol.RelationRequest();
            relation.setFatherId(relationDomain.getFatherId());
            relation.setMotherId(relationDomain.getMotherId());
            relation.setSpouseId(relationDomain.getSpouseId());
            profile.setRelation(relation);
        }
        profile.setUsername(member.getUsername());
        profile.setPassword(member.getPassword());
        return profile;
    }

    private Person getFatherFromDomain(Member member)
    {
        Person father = null;
        if (member.getRelation() != null && member.getRelation().getFatherId() != null)
        {
            father = new Person();
            long id = member.getRelation().getFatherId();
            Optional<Member> memberOptional = memberRepo.findById(id);
            if (memberOptional.isPresent())
            {
                father.setId(id);
                father.setName(
                        memberOptional.get().getContact().getFirstName() + " " + memberOptional.get().getContact()
                                .getLastName());
            }
        }
        return father;
    }

    private Person getMotherFromDomain(Member member)
    {
        Person mother = null;
        if (member.getRelation() != null && member.getRelation().getMotherId() != null)
        {
            mother = new Person();
            long id = member.getRelation().getMotherId();
            Optional<Member> memberOptional = memberRepo.findById(id);
            if (memberOptional.isPresent())
            {
                mother.setId(id);
                mother.setName(
                        memberOptional.get().getContact().getFirstName() + " " + memberOptional.get().getContact()
                                .getLastName());
            }
        }
        return mother;
    }

    private Person getSpouseFromDomain(Member member)
    {
        Person spouse = null;
        if (member.getRelation() != null && member.getRelation().getSpouseId() != null)
        {
            spouse = new Person();
            long id = member.getRelation().getSpouseId();
            Optional<Member> memberOptional = memberRepo.findById(id);
            if (memberOptional.isPresent())
            {
                spouse.setId(id);
                spouse.setName(
                        memberOptional.get().getContact().getFirstName() + " " + memberOptional.get().getContact()
                                .getLastName());
            }
        }
        return spouse;
    }

    private List<Person> getChildrenFromDomain(Member member)
    {
        List<Person> children = null;
        List<Member> childrenDomain = null;
        if ("F".equals(member.getContact().getGender()))
        {
            Optional<List<Member>> optionalMembers = memberRepo.findMembersByRelationMotherId(member.getMemberId());
            if (optionalMembers.isPresent())
            {
                childrenDomain = optionalMembers.get();
            }
        }
        else if ("M".equals(member.getContact().getGender()))
        {
            Optional<List<Member>> optionalMembers = memberRepo.findMembersByRelationFatherId(member.getMemberId());
            if (optionalMembers.isPresent())
            {
                childrenDomain = optionalMembers.get();
            }
        }
        if (CollectionUtils.isEmpty(childrenDomain))
        {
            return null;
        }
        children = new ArrayList<>();
        for (Member childMember :
                childrenDomain)
        {
            Person p = getMemberFromDomain(childMember);
            children.add(p);
        }
        return children;
    }

    private Person getMemberFromDomain(Member member)
    {
        Person person = null;
        Long id = member.getMemberId();
        if (memberRepo.findById(id).isPresent())
        {
            person = new Person();
            person.setId(id);
            person.setName(memberRepo.findById(id).get().getContact().getFirstName());
        }
        return person;
    }

    public Member convertProfileToDomain(ProfileRequest profile)
    {
        com.amruta.familytree.domain.Member memberDomain = new com.amruta.familytree.domain.Member();
        com.amruta.familytree.domain.Contact contactDomain = new com.amruta.familytree.domain.Contact();
        com.amruta.familytree.domain.Relation relationDomain = new com.amruta.familytree.domain.Relation();
        memberDomain.setPassword(profile.getPassword());
        memberDomain.setUsername(profile.getUsername());
        if (profile.getContact() != null)
        {
            contactDomain.setFirstName(profile.getContact().getFirstName());
            contactDomain.setLastName(profile.getContact().getLastName());
            contactDomain.setEmailId(profile.getContact().getEmailId());
            contactDomain.setPrimaryPhoneNumber(profile.getContact().getPrimaryPhoneNumber());
            contactDomain.setSecondaryPhoneNumber(profile.getContact().getSecondaryPhoneNumber());
            contactDomain.setGender(profile.getContact().getGender());
        }
        if (profile.getRelation() != null)
        {
            relationDomain.setFatherId(profile.getRelation().getFatherId());
            relationDomain.setMotherId(profile.getRelation().getMotherId());
            relationDomain.setSpouseId(profile.getRelation().getSpouseId());
        }
        memberDomain.setContact(contactDomain);
        memberDomain.setRelation(relationDomain);
        com.amruta.familytree.domain.Member memberSaved = memberRepo.save(memberDomain);
        return memberSaved;
    }
}
