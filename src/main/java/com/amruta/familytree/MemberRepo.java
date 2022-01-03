package com.amruta.familytree;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.amruta.familytree.domain.Member;

@Repository
public interface MemberRepo extends JpaRepository<Member, Long>{

}

