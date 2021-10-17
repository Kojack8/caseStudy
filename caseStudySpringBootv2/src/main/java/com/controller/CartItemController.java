package com.controller;

import com.dto.CartItemDTO;
import com.dto.ProductDTO;
import com.entitymodels.CartItemEntity;
import com.entitymodels.ProductEntity;
import com.entitymodels.RoleEntity;
import com.entitymodels.UserEntity;
import com.logging.LombokLoggingController;
import com.repository.CartItemRepository;
import com.repository.ProductRepository;
import com.service.CartItemService;
import com.service.ProductService;
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
    private final CartItemService cartItemService;
    private final ProductService productService;

    public CartItemController(CartItemRepository cartItemRepository, ProductRepository productRepository,
                              CartItemService cartItemService, ProductService productService){
        this.cartItemRepository = cartItemRepository;
        this.productRepo = productRepository;
        this.cartItemService = cartItemService;
        this.productService = productService;
    }

    @GetMapping
    public List<CartItemDTO> getUsers() {
        try {
            return cartItemService.findAllCartItems();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/{id}")
    public CartItemDTO getCartItem(@PathVariable Integer id) {
        //return cartItemRepository.findById(id).orElseThrow(RuntimeException::new);
        return cartItemService.findById(id);
    }


    @PostMapping
    public ResponseEntity createCartItem(@RequestParam Integer id, @RequestParam Integer quantity) throws URISyntaxException {

        //THIS IS WHERE I LEFT OFF. TIME TO MAKE THE ADD CART ITEMS WORK
        //String rxId = id.replaceAll("[^0-9]+", "");
        //Integer intId = (Integer.valueOf(id));
        logger.warn(String.valueOf(quantity));

        CartItemDTO savedCartItem = cartItemService.addCartItem(id, quantity);
        //CartItemEntity cartItem = new CartItemEntity();
        //ProductEntity productEntity = productRepo.findById(intId).get();
        //ProductDTO productDTO = productService.findById(intId);

        //cartItem.setProduct(productEntity);
        //cartItem.setQuantity(1);

        //CartItemEntity savedCartItem = cartItemRepository.save(cartItem);
        return ResponseEntity.created(new URI("/cartItemEntities" + savedCartItem.getId())).body(savedCartItem);
        //return null;
    }

    @PutMapping("/{id}")
    public ResponseEntity updateCartItem(@PathVariable Integer id, @RequestBody CartItemDTO cartItemDTO) {
        //CartItemEntity currentCartItem = cartItemRepository.findById(id).orElseThrow(RuntimeException::new);
        CartItemDTO currentCartItem = cartItemService.findById(id);
        currentCartItem.setQuantity(cartItemDTO.getQuantity());
        CartItemDTO savedCartItem = cartItemService.save(currentCartItem);

        return ResponseEntity.ok(currentCartItem);
    }

    @DeleteMapping
    public ResponseEntity deleteCartItem(@RequestParam Integer id) {
        cartItemService.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
