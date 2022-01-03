package com.amruta.familytree;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.amruta.familytree.domain.Contact;

@Repository
public interface ContactRepo extends JpaRepository<Contact, Long>{
	Optional<Contact> findByMemberId(Long memberId);
}