package com.controller;

import com.dto.CartItemDTO;
import com.dto.CartItemDTOPrice;
import com.dto.ShoppingCartDTO;
import com.dto.UserDTO;
import com.entitymodels.ShoppingCartEntity;
import com.entitymodels.UserEntity;
import com.repository.ShoppingCartRepository;
import com.repository.UserRepository;
import com.service.CartItemService;
import com.service.RoleService;
import com.service.ShoppingCartService;
import com.service.UserService;
import com.userdetails.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/cart")
public class ShoppingCartController {


    private final ShoppingCartService shoppingCartService;
    private final CartItemService cartItemService;


    @Autowired
    public ShoppingCartController(ShoppingCartService shoppingCartService, CartItemService cartItemService) {
        this.shoppingCartService = shoppingCartService;
        this.cartItemService = cartItemService;
    }

    @GetMapping
    public List<CartItemDTOPrice> getMyCart() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            CustomUserDetails userPrincipal = (CustomUserDetails) authentication.getPrincipal();
            Long userID = userPrincipal.getId();
            //UserDTO user = userService.findUserById(userID);
            //ShoppingCartDTO cart = shoppingCartService.findByUserEntity(userService.convertToUserEntity(user));
            List<CartItemDTOPrice> items = cartItemService.findAllCartItemsPriceByUserId(userID);
            return items;
        } else {
            return null;
        }
    }

    /*@GetMapping("/{id}")
    public UserDTO getUser(@PathVariable Long id) {

        return userService.findUserById(id);
    }


    @PostMapping
    public ResponseEntity createUser(@RequestBody UserDTO user) throws URISyntaxException {
            return null;
            //return ResponseEntity.created(new URI("/userEntities/" + savedUser.getId())).body(savedUser);

    }

    @PutMapping("/{id}")
    public ResponseEntity updateUser(@PathVariable Long id, @RequestBody UserDTO user) {
        return null;
        //return ResponseEntity.ok(savedUser);
    }*/

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable long id) {
        //userService.deleteById(id);
        return ResponseEntity.ok().build();
    }


}
