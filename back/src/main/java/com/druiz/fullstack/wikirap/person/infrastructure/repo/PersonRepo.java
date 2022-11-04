package com.druiz.fullstack.wikirap.person.infrastructure.repo;

import com.druiz.fullstack.wikirap.person.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface PersonRepo extends JpaRepository<Person, Integer> {


    @Query(nativeQuery = true, value = "SELECT * FROM Personas WHERE name LIKE %:name%")
    List<Person> findByName(String name);

    @Query(nativeQuery = true, value = "SELECT * FROM Personas WHERE origen LIKE %:origen%")
    List<Person> findByOrigen(String origen);

    @Query(nativeQuery = true, value = "SELECT * FROM Personas LIMIT :initial,:offset")
    List<Person> getPersonRange (int initial, int offset);

    @Query(nativeQuery = true,value="SELECT COUNT(*) FROM Personas")
    Integer getNumberOfPersons();

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value="DELETE FROM Personas where id>101")
    void clear();
}
