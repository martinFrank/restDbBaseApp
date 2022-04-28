package com.github.martinfrank.baseApp.controller;


import com.github.martinfrank.baseApp.model.Person;
import com.github.martinfrank.baseApp.service.PersonService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class PersonController {

    @Autowired
    private PersonService personService;

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
        return ResponseEntity.ok().body(this.personService.create(person));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Person> update(@PathVariable long id, @RequestBody Person person) {
        person.setId(id);
        return ResponseEntity.ok().body(this.personService.update(person));
    }

    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable long id) {
        this.personService.delete(id);
        return HttpStatus.OK;
    }
}
