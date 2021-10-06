package com.controller;

import com.entitymodels.RoleEntity;
import com.entitymodels.ShoppingCartEntity;
import com.entitymodels.UserEntity;
import com.repository.RoleRepository;
import com.repository.ShoppingCartRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.repository.UserRepository;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/users")
public class UserEntityController {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ShoppingCartRepository shoppingCartRepository;

    public UserEntityController(UserRepository userRepository, RoleRepository roleRepository,
            ShoppingCartRepository shoppingCartRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.shoppingCartRepository = shoppingCartRepository;
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
            RoleEntity userRole = roleRepository.findByName("ROLE_USER");
            Collection<RoleEntity> roles = new ArrayList<RoleEntity>();
            roles.add(userRole);
            user.setRoles(roles);

            ShoppingCartEntity cart = new ShoppingCartEntity();

            ShoppingCartEntity savedCart = shoppingCartRepository.save(cart);
            ArrayList<ShoppingCartEntity> carts = new ArrayList<ShoppingCartEntity>();
            carts.add(cart);
            user.setCarts(carts);
            UserEntity savedUser = userRepository.save(user);


            savedCart.setUserEntity(savedUser);
            shoppingCartRepository.save(savedCart);




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