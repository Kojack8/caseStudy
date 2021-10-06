package com.controller;

import com.entitymodels.CartItemEntity;
import com.entitymodels.ProductEntity;
import com.entitymodels.RoleEntity;
import com.entitymodels.UserEntity;
import com.logging.LombokLoggingController;
import com.repository.CartItemRepository;
import com.repository.ProductRepository;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/cartitem")
public class CartItemController {

    Logger logger = LoggerFactory.getLogger(LombokLoggingController.class);
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepo;

    public CartItemController(CartItemRepository cartItemRepository, ProductRepository productRepository){
        this.cartItemRepository = cartItemRepository;
        this.productRepo = productRepository;
    }

    @GetMapping
    public List<CartItemEntity> getUsers() {
        try {
            return cartItemRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/{id}")
    public CartItemEntity getCartItem(@PathVariable Integer id) {
        return cartItemRepository.findById(id).orElseThrow(RuntimeException::new);
    }


    @PostMapping
    public ResponseEntity createCartItem(@RequestBody String id) throws URISyntaxException {
        String rxId = id.replaceAll("[^0-9]+", "");
        Integer intId = (Integer.valueOf(rxId));
        logger.warn(String.valueOf(intId));
        CartItemEntity cartItem = new CartItemEntity();
        ProductEntity productEntity = productRepo.findById(intId).get();

        cartItem.setProduct(productEntity);
        cartItem.setQuantity(1);

        CartItemEntity savedCartItem = cartItemRepository.save(cartItem);
        return ResponseEntity.created(new URI("/cartItemEntities" + savedCartItem.getId())).body(savedCartItem);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateCartItem(@PathVariable Integer id, @RequestBody CartItemEntity cartItem) {
        CartItemEntity currentCartItem = cartItemRepository.findById(id).orElseThrow(RuntimeException::new);
        currentCartItem.setQuantity(cartItem.getQuantity());
        currentCartItem = cartItemRepository.save(cartItem);

        return ResponseEntity.ok(currentCartItem);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCartItem(@PathVariable Integer id) {
        cartItemRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
