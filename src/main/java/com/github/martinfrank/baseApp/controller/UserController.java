package com.github.martinfrank.baseApp.controller;


import com.github.martinfrank.baseApp.model.User;
import com.github.martinfrank.baseApp.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
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

    @ApiOperation(value = "Add a new user", tags = { "user" })
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "User created"),
            @ApiResponse(code = 400, message = "Invalid input"),
            @ApiResponse(code = 409, message = "Contact already exists") })
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
