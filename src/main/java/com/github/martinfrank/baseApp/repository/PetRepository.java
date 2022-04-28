package com.github.martinfrank.baseApp.repository;

import com.github.martinfrank.baseApp.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetRepository extends JpaRepository<Pet, Long> {


}
