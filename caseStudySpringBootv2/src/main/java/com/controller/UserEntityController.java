package com.controller;

import com.entitymodels.UserEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.repository.UserRepository;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/users")
public class UserEntityController {

    private final UserRepository userRepository;

    public UserEntityController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<UserEntity> getUsers() {
        try {
            return userRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/{id}")
    public UserEntity getUser(@PathVariable Long id) {
        return userRepository.findById(id).orElseThrow(RuntimeException::new);
    }


    @PostMapping
    public ResponseEntity createUser(@RequestBody UserEntity user) throws URISyntaxException {
        try {
            UserEntity savedUser = userRepository.save(user);
            return ResponseEntity.created(new URI("/userEntities/" + savedUser.getId())).body(savedUser);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity updateUser(@PathVariable Long id, @RequestBody UserEntity user) {
        UserEntity currentUser = userRepository.findById(id).orElseThrow(RuntimeException::new);
        currentUser.setFullName(user.getFullName());
        currentUser.setEmail(user.getEmail());
        currentUser = userRepository.save(user);

        return ResponseEntity.ok(currentUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable long id) {
        userRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }


}