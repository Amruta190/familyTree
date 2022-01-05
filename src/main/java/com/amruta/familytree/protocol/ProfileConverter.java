package com.amruta.familytree.protocol;

import com.amruta.familytree.domain.MemberRepo;
import com.amruta.familytree.protocol.Profile;
import com.amruta.familytree.domain.Contact;
import com.amruta.familytree.domain.Member;
import com.amruta.familytree.domain.Relation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProfileConverter
{
    @Autowired
    private MemberRepo memberRepo;

    public Profile convertDomainToProfile(Member member)
    {
        Profile profile = new Profile();
        if (member.getContact() != null)
        {
            Contact contactDomain = member.getContact();
            com.amruta.familytree.protocol.Contact contact = new com.amruta.familytree.protocol.Contact();
            contact.setFirstName(contactDomain.getFirstName());
            contact.setLastName(contactDomain.getLastName());
            contact.setEmailId(contactDomain.getMailId());
            contact.setPrimaryPhoneNumber(contactDomain.getPrimaryPhoneNumber());
            contact.setSecondaryPhoneNumber(contactDomain.getSecondaryPhoneNumber());
            profile.setContact(contact);
        }
        if (member.getRelation() != null)
        {
            Relation relationDomain = member.getRelation();
            com.amruta.familytree.protocol.Relation relation = new com.amruta.familytree.protocol.Relation();
            relation.setFatherId(relationDomain.getFatherId().toString());
            relation.setMotherId(relationDomain.getMotherId().toString());
            relation.setSpouseId(relationDomain.getSpouseId().toString());
            relation.setGender(relationDomain.getGender());
            profile.setRelation(relation);
        }
        return profile;
    }

    public Member convertProfileToDomain(Profile profile)
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
            contactDomain.setMailId(profile.getContact().getEmailId());
            contactDomain.setPrimaryPhoneNumber(profile.getContact().getPrimaryPhoneNumber());
            contactDomain.setSecondaryPhoneNumber(profile.getContact().getSecondaryPhoneNumber());
        }
        if (profile.getRelation() != null)
        {
            relationDomain.setFatherId(Long.parseLong(profile.getRelation().getFatherId()));
            relationDomain.setMotherId(Long.parseLong(profile.getRelation().getMotherId()));
            relationDomain.setSpouseId(Long.parseLong(profile.getRelation().getSpouseId()));
            relationDomain.setGender(profile.getRelation().getGender());
        }
        memberDomain.setContact(contactDomain);
        memberDomain.setRelation(relationDomain);
        com.amruta.familytree.domain.Member memberSaved = memberRepo.save(memberDomain);
        return memberSaved;
    }
}
