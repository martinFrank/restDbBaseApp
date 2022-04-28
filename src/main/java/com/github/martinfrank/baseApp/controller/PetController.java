package com.github.martinfrank.baseApp.controller;

import com.github.martinfrank.baseApp.model.Pet;
import com.github.martinfrank.baseApp.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pets")
public class PetController {

    @Autowired
    private PetService petService;

    @GetMapping()
    public ResponseEntity<List<Pet>> getAll() {
        return ResponseEntity.ok().body(petService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pet> getById(@PathVariable long id) {
        return ResponseEntity.ok().body(petService.getById(id));
    }

    @PostMapping()
    public ResponseEntity<Pet> create(@RequestBody Pet pet) {
        return ResponseEntity.ok().body(this.petService.create(pet));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pet> update(@PathVariable long id, @RequestBody Pet pet) {
        pet.setId(id);
        return ResponseEntity.ok().body(this.petService.update(pet));
    }

    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable long id) {
        this.petService.delete(id);
        return HttpStatus.OK;
    }
}
