package com.github.martinfrank.baseApp.controller;


import com.github.martinfrank.baseApp.model.User;
import com.github.martinfrank.baseApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAll() {
        return ResponseEntity.ok().body(userService.getAll());
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getById(@PathVariable long id) {
        return ResponseEntity.ok().body(userService.getById(id));
    }

    @PostMapping("/users")
    public ResponseEntity<User> create(@RequestBody User product) {
        return ResponseEntity.ok().body(this.userService.create(product));
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> update(@PathVariable long id, @RequestBody User product) {
        product.setId(id);
        return ResponseEntity.ok().body(this.userService.update(product));
    }

    @DeleteMapping("/users/{id}")
    public HttpStatus delete(@PathVariable long id) {
        this.userService.delete(id);
        return HttpStatus.OK;
    }
}
