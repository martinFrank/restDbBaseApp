package com.github.martinfrank.baseApp.service;

import com.github.martinfrank.baseApp.model.Person;
import com.github.martinfrank.baseApp.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Override
    public Person create(Person person) {
        return personRepository.save(person);
    }

    @Override
    public Person update(Person person) {
        Optional<Person> userCandidate = personRepository.findById(person.getId());

        if (userCandidate.isPresent()) {
            Person personToUpdate = userCandidate.get();
            personToUpdate.setId(person.getId());
            personToUpdate.setName(person.getName());
            personToUpdate.setEmail(person.getEmail());
            personRepository.save(personToUpdate);
            return personToUpdate;
        } else {
            throw new ResourceNotFoundException("Person Record not found with id : " + person.getId());
        }
    }

    @Override
    public List<Person> getAll() {
        return this.personRepository.findAll();
    }

    @Override
    public Person getById(long personId) {
        Optional<Person> candidate = this.personRepository.findById(personId);
        if (candidate.isPresent()) {
            return candidate.get();
        } else {
            throw new ResourceNotFoundException("Person Record not found with id : " + personId);
        }
    }

    @Override
    public void delete(long personId) {
        Optional<Person> candidate = this.personRepository.findById(personId);
        if (candidate.isPresent()) {
            this.personRepository.delete(candidate.get());
        } else {
            throw new ResourceNotFoundException("Record not found with id : " + personId);
        }
    }
}
