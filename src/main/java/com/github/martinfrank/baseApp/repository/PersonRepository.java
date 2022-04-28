package com.github.martinfrank.baseApp.repository;

import com.github.martinfrank.baseApp.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {


}
