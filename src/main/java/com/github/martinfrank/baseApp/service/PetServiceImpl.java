package com.github.martinfrank.baseApp.service;

import com.github.martinfrank.baseApp.model.Person;
import com.github.martinfrank.baseApp.model.Pet;
import com.github.martinfrank.baseApp.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PetServiceImpl implements PetService {

    @Autowired
    private PetRepository petRepository;

    @Override
    public Pet create(Pet person) {
        return petRepository.save(person);
    }

    @Override
    public Pet update(Pet pet) {
        Optional<Pet> petCandidate = petRepository.findById(pet.getId());

        if (petCandidate.isPresent()) {
            Pet petToUpdate = petCandidate.get();
            petToUpdate.setId(pet.getId());
            petToUpdate.setName(pet.getName());
            petRepository.save(petToUpdate);
            return petToUpdate;
        } else {
            throw new ResourceNotFoundException("Pet Record not found with id : " + pet.getId());
        }
    }

    @Override
    public List<Pet> getAll() {
        return petRepository.findAll();
    }

    @Override
    public Pet getById(long id) {
        Optional<Pet> candidate = petRepository.findById(id);
        if (candidate.isPresent()) {
            return candidate.get();
        } else {
            throw new ResourceNotFoundException("Pet Record not found with id : " + id);
        }
    }

    @Override
    public void delete(long id) {
        Optional<Pet> candidate = petRepository.findById(id);
        if (candidate.isPresent()) {
            petRepository.delete(candidate.get());
        } else {
            throw new ResourceNotFoundException("Pet Record not found with id : " + id);
        }
    }

    @Override
    public List<Pet> getByIds(List<Long> petIds) {
        return petRepository.findAllById(petIds);
    }
}
