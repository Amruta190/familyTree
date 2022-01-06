package com.amruta.familytree.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepo extends JpaRepository<Member, Long>
{
    Optional<List<Member>> findMembersByRelationFatherId(Long id);
    Optional<List<Member>> findMembersByRelationMotherId(Long id);
}

