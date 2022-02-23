package com.amruta.familytree.protocol;

import com.amruta.familytree.domain.*;
import com.amruta.familytree.security.CustomMemberDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Controller
public class UIController
{

	@Autowired
	private MemberRepo memberRepo;

	@Autowired
	private TreeRepo treeRepo;

	@Autowired
	private ProfileConverter profileConverter;
	
	@GetMapping("")
	public String viewMainPage() {
		return "main";
	}

	@GetMapping("/index")
	public String viewHomePage() { return "index"; }
	
	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
		model.addAttribute("user", new Member());
		List<Member> existingUsers = memberRepo.findAll();
		model.addAttribute("listUsers", existingUsers);
		model.addAttribute("tree", new Tree());
		List<Tree> existingTrees=treeRepo.findAll();
		model.addAttribute("listTrees",existingTrees);
		return "signup_form";
	}
	
	@PostMapping("/process_register")
	public String processRegister(Member user) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		// Save the member and get his details with member id.
		Member newMember = memberRepo.save(user);
		Relation relation = newMember.getRelation();
		Member spouse;
		// Update spouse details of Spouse of the User.
		if (Objects.nonNull(relation) && relation.getSpouseId() != null)
		{
			Long spouseId = relation.getSpouseId();
			Optional<Member> spouseOptional = memberRepo.findById(spouseId);

			if (spouseOptional.isPresent())
			{
				spouse = spouseOptional.get();
				Relation spouseRelation = spouse.getRelation();
				if (Objects.isNull(spouseRelation))
				{
					spouseRelation = new Relation();
				}
				spouseRelation.setSpouseId(newMember.getMemberId());
				spouse.setRelation(spouseRelation);
				memberRepo.save(spouse);
			}
		}

		return "register_success";
	}
	
	@GetMapping("/users")
	public String listUsers(Model model, HttpServletRequest httpServletRequest) {
		List<Member> members = memberRepo.findAll();
		List<ProfileResponse> profileResponseList = new ArrayList<>();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CustomMemberDetails myUserDetails = (CustomMemberDetails) authentication.getPrincipal();
		Long userId = myUserDetails.getMember().getTreeId();
		for (Member member:
			 members)
		{
			if(member.getTreeId()== userId )
				profileResponseList.add(profileConverter.convertDomainToProfileResponse(member));
		}

		model.addAttribute("listUsers", profileResponseList);
		
		return "users";
	}

    @GetMapping("/trees")
    public String listTrees(Model model, HttpServletRequest httpServletRequest){
        List<Tree> trees = treeRepo.findAll();
        model.addAttribute("listTrees",trees);
        return "trees";
    }
	@GetMapping("/newFamily")
	public String createFamily(Model model)
	{
		model.addAttribute("tree", new Tree());
		return "newFamily";
	}

	@PostMapping("/tree_register")
	public String treeRegister (Tree tree)
	{
		Tree newTree=treeRepo.save(tree);
		return "tree_creation_successful";
	}

	@PostMapping("/treeD3rect")
	public String family() {
		return "treeD3rect";
	}
}
