package com.example.restfulwebservices.user;

import com.example.restfulwebservices.post.Post;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class UserJpaController {
    @Autowired
    private UserDaoService userDaoService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    public UserJpaController(UserDaoService userDaoService, UserRepository userRepository) {
        this.userDaoService = userDaoService;
        this.userRepository = userRepository;
    }

    @GetMapping("/jpa/users")
    public List<User> retrieveAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/jpa/users/{id}")
    public User retrieveOneUser(@PathVariable Integer id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()){
            throw new UserNotFoundException(id);
        }
        return user.get();
    }

    @PostMapping("/jpa/users")
    public ResponseEntity<User> saveOneUser(@Valid @RequestBody User user) {
        User user1 = userRepository.save(user);
        // Cela sert à faire par exemple une redirection, lorqu'une requête est correctement exécuté, il se peut que l'utilisateur
        // veuille aller sur l'user précedemment créé, cela peut donc se faire avec les information de dans l'header
        URI location = ServletUriComponentsBuilder
                        .fromCurrentRequest().path("/{id}")
                        .buildAndExpand(user1.getId())
                        .toUri();
        return ResponseEntity.created(location).build();
    }

    @PostMapping("/jpa/users/{id}/posts")
    public ResponseEntity<Post> creatPostForUser(@PathVariable Integer id, @Valid @RequestBody Post post) {
        User user = userRepository.getReferenceById(id);
        post.setUser(user);
        Post save = postRepository.save(post);
        // Cela sert à faire par exemple une redirection, lorqu'une requête est correctement exécuté, il se peut que l'utilisateur
        // veuille aller sur l'user précedemment créé, cela peut donc se faire avec les information de dans l'header
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(save.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/jpa/users/{id}/posts")
    public List<Post> retreivePosts(@PathVariable Integer id) {
        User user = userRepository.getReferenceById(id);
        return user.getPosts();

    }
    @DeleteMapping("/jpa/users/{id}")
    public void deleteById(@PathVariable Integer id) {
        userRepository.deleteById(id);
    }

}
