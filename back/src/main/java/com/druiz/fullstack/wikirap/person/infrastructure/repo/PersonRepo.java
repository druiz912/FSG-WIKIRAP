package com.druiz.fullstack.wikirap.person.infrastructure.repo;

import com.druiz.fullstack.wikirap.person.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonRepo extends JpaRepository<Person, Integer> {

    List<Person> findPersonByName(String name);
}
