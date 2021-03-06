package com.amruta.familytree.protocol;

import com.amruta.familytree.domain.Member;
import com.amruta.familytree.domain.MemberRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.json.JsonMergePatch;
import java.util.Optional;

@RestController
public class DataController
{
    @Autowired
    private MemberRepo memberRepo;

    @Autowired
    private ProfileConverter profileConverter;

    @Autowired
    private PatchHelper patchHelper;

    @GetMapping("/profiles/{memberId}")
    public ResponseEntity<ProfileResponse> getProfile(@PathVariable Long memberId)
    {
        Optional<Member> member = memberRepo.findById(memberId);
        ProfileResponse profile;
        if (!member.isPresent())
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        profile = profileConverter.convertDomainToProfileResponse(member.get());
        return new ResponseEntity<>(profile, HttpStatus.OK);
    }

    @PostMapping("/profiles")
    public ResponseEntity<MemberResponse> createProfile(@RequestBody ProfileRequest profile)
    {
        Member memberDomain = profileConverter.convertProfileToDomain(profile);
        MemberResponse memberResponse = new MemberResponse();
        memberResponse.setMemberId(memberDomain.getMemberId());
        return new ResponseEntity<>(memberResponse, HttpStatus.CREATED);
    }

    @PatchMapping(value = "/profiles/{memberId}", consumes = "application/json-patch+json")
    public ResponseEntity<String> updateProfile(@PathVariable long memberId,
            @RequestBody JsonMergePatch jsonMergePatch)
    {
        if (!memberRepo.findById(memberId).isPresent())
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        ProfileRequest profileDomain = profileConverter.convertDomainToProfileRequest(memberRepo.findById(memberId).get());
        ProfileRequest profile = patchHelper.mergePatch(jsonMergePatch, profileDomain, ProfileRequest.class);
        profileConverter.convertProfileToDomain(profile);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/profiles/{memberId}")
    public ResponseEntity<String> deleteProfile(@PathVariable Long memberId)
    {
        if (!memberRepo.findById(memberId).isPresent())
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        memberRepo.deleteById(memberId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
