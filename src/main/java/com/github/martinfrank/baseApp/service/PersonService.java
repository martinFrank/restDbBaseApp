package com.github.martinfrank.baseApp.service;

import com.github.martinfrank.baseApp.model.Person;

import java.util.List;

public interface PersonService {

    Person create(Person person);

    Person update(Person person);

    List<Person> getAll();

    Person getById(long personId);

    void delete(long id);

}
