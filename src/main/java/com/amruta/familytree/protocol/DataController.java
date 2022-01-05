package com.amruta.familytree.protocol;

import com.amruta.familytree.domain.MemberRepo;
import com.amruta.familytree.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class DataController
{
    @Autowired
    private MemberRepo memberRepo;

    @Autowired
    private ProfileConverter profileConverter;

    @GetMapping("/profiles/{member_id}")
    public ResponseEntity<Profile> getMember(@PathVariable("member_id") Long member_id)
    {
        Optional<Member> member = memberRepo.findById(member_id);
        Profile profile = null;
        if (member.isPresent())
        {
            profile = profileConverter.convertDomainToProfile(member.get());
        }
        return new ResponseEntity<>(profile, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = { "/profiles" }, headers = "Accept=application/json")
    public ResponseEntity<MemberResponse> createdMember(@RequestBody(required = true) Profile profile)
    {
        Member memberDomain = profileConverter.convertProfileToDomain(profile);
        MemberResponse memberResponse = new MemberResponse();
        memberResponse.setMemberId(memberDomain.getMemberId());
        return new ResponseEntity<>(memberResponse, HttpStatus.CREATED);
    }
}
