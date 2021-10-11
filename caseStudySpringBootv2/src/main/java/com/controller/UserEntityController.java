package com.controller;

import com.dto.UserDTO;
import com.entitymodels.RoleEntity;
import com.entitymodels.ShoppingCartEntity;
import com.entitymodels.UserEntity;
import com.repository.RoleRepository;
import com.repository.ShoppingCartRepository;
import com.service.UserService;
import com.service.UserHibernateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.repository.UserRepository;

import javax.persistence.EntityNotFoundException;
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
    private final UserService userService;

    @Autowired
    public UserEntityController(UserRepository userRepository, RoleRepository roleRepository,
                                ShoppingCartRepository shoppingCartRepository, UserService userService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.shoppingCartRepository = shoppingCartRepository;
        this.userService = userService;
    }

    @GetMapping
    public List<UserDTO> getUsers() {
        try {
            List<UserDTO> users = userService.findAllUsers();
            return users;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/{id}")
    public UserDTO getUser(@PathVariable Long id) {
        //return userRepository.findById(id).orElseThrow(RuntimeException::new);
        return userService.findUserById(id);
    }


    @PostMapping
    public ResponseEntity createUser(@RequestBody UserDTO user) throws URISyntaxException {
        try {
            RoleEntity userRole = roleRepository.findByName("ROLE_USER");
            Collection<RoleEntity> roles = new ArrayList<RoleEntity>();
            roles.add(userRole);
            user.setRoles(roles);

            /*ShoppingCartEntity cart = new ShoppingCartEntity();

            ShoppingCartEntity savedCart = shoppingCartRepository.save(cart);
            ArrayList<ShoppingCartEntity> carts = new ArrayList<ShoppingCartEntity>();
            carts.add(cart);
            user.setCarts(carts);*/
            UserDTO savedUser = userService.save(user);
            //UserEntity savedUser = userRepository.save(user);
            UserEntity savedEntity = userRepository.findById(savedUser.getId())
                    .orElseThrow(() -> new EntityNotFoundException(String.valueOf(savedUser)));
            ShoppingCartEntity cart = new ShoppingCartEntity();
            cart.setUserEntity(savedEntity);
            shoppingCartRepository.save(cart);


            /*savedCart.setUserEntity(savedUser);
            shoppingCartRepository.save(savedCart);*/


            return ResponseEntity.created(new URI("/userEntities/" + savedUser.getId())).body(savedUser);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity updateUser(@PathVariable Long id, @RequestBody UserDTO user) {
        //UserEntity currentUser = userRepository.findById(id).orElseThrow(RuntimeException::new);
        UserDTO currentUser = userService.findUserById(id);
        currentUser.setFullName(user.getFullName());
        currentUser.setEmail(user.getEmail());
        UserDTO savedUser = userService.save(currentUser);
        //userService.convertToEntity(currentUser);
        //UserEntity savedUser = userRepository.save(userService.convertToEntity(currentUser));

        return ResponseEntity.ok(savedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable long id) {
        userService.deleteById(id);
        return ResponseEntity.ok().build();
    }


}