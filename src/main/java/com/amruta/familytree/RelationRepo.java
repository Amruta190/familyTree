package com.amruta.familytree;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.amruta.familytree.domain.Relation;

@Repository
public interface RelationRepo extends JpaRepository<Relation, Long>{
	Optional<Relation> findByMemberId(Long memberId);
}
