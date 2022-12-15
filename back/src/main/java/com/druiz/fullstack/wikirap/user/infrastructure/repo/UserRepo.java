package com.druiz.fullstack.wikirap.user.infrastructure.repo;

import com.druiz.fullstack.wikirap.user.domain.UserEnt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<UserEnt, Integer> {

    UserEnt findByEmailAndPassword(String email, String password);

    UserEnt findByUsername(String username);

    boolean existsByUsername(String username);
}
