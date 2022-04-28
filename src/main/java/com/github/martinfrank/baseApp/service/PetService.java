package com.github.martinfrank.baseApp.service;

import com.github.martinfrank.baseApp.model.Person;
import com.github.martinfrank.baseApp.model.Pet;

import java.util.List;

public interface PetService {

    Pet create(Pet pet);

    Pet update(Pet pet);

    List<Pet> getAll();

    Pet getById(long id);

    void delete(long id);

    List<Pet> getByIds(List<Long> petIds);
}
