package com.github.martinfrank.baseApp.controller;


import com.github.martinfrank.baseApp.model.Person;
import com.github.martinfrank.baseApp.model.Pet;
import com.github.martinfrank.baseApp.service.PersonService;
import com.github.martinfrank.baseApp.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/persons") //https://restfulapi.net/resource-naming/ should be PLURAL!
public class PersonController {

    @Autowired
    private PersonService personService;

    @Autowired
    private PetService petService;

    @GetMapping()
    public ResponseEntity<List<Person>> getAll() {
        return ResponseEntity.ok().body(personService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> getById(@PathVariable long id) {
        return ResponseEntity.ok().body(personService.getById(id));
    }

    @PostMapping()
    public ResponseEntity<Person> create(@RequestBody Person person) {
        return ResponseEntity.ok().body(personService.create(person));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Person> update(@PathVariable long id, @RequestBody Person person) {
        person.setId(id);
        return ResponseEntity.ok().body(personService.update(person));
    }

    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable long id) {
        personService.delete(id);
        return HttpStatus.OK;
    }

    @PutMapping("{id}/pets")
    public HttpStatus setPets(@PathVariable long id, @RequestBody List<Long> petIds){
        Person person = personService.getById(id);
        removePets(person);
        List<Pet> pets = petService.getByIds(petIds);
        addPets(person, pets);
        return HttpStatus.OK;
    }

    private void addPets(Person person, List<Pet> pets) {
        person.setPets(pets);
        pets.forEach(p -> p.setOwner(person));
        personService.update(person);
        pets.forEach(petService::update);
    }

    private void removePets(Person person) {
        person.getPets().forEach(p -> {
            p.setOwner(null);
            petService.update(p);
        });
    }

    @GetMapping("{id}/pets")
    public ResponseEntity<List<Pet>> getPets(@PathVariable long id){
        Person person = personService.getById(id);
        List<Pet> pets = new ArrayList<>(person.getPets());
        return ResponseEntity.ok().body(pets);
    }
}
