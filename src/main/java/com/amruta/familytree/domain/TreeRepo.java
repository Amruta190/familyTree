package com.amruta.familytree.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TreeRepo extends JpaRepository<Tree, Long>
{
    Optional<Tree> findTreesByTreeId(Long id);
}
