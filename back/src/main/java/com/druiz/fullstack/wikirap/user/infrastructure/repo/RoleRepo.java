package com.druiz.fullstack.wikirap.user.infrastructure.repo;

import com.druiz.fullstack.wikirap.user.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepo extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(String name);
}
