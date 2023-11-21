package com.example.restfulwebservices.user;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserDaoService userDaoService;

    @GetMapping("/users")
    public List<User> retrieveAllUsers() {
        return userDaoService.findAll();
    }

    @GetMapping("/users/{id}")
    public User retrieveOneUser(@PathVariable Integer id) {
        User user = userDaoService.findById(id);
        if(user ==null){
            throw new UserNotFoundException(id);
        }
        return user;
    }

    @PostMapping("/users")
    public ResponseEntity<User> saveOneUser(@Valid @RequestBody User user) {
        User user1 = userDaoService.save(user);
        // Cela sert à faire par exemple une redirection, lorqu'une requête est correctement exécuté, il se peut que l'utilisateur
        // veuille aller sur l'user précedemment créé, cela peut donc se faire avec les information de dans l'header
        URI location = ServletUriComponentsBuilder
                        .fromCurrentRequest().path("/{id}")
                        .buildAndExpand(user1.getId())
                        .toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/users/{id}")
    public void deleteById(@PathVariable Integer id) {
        userDaoService.remove(id);
    }

}
