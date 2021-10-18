package com.controller;

import com.dto.CartItemDTO;
import com.dto.PurchaseDTO;
import com.dto.ShoppingCartDTO;
import com.entitymodels.ShoppingCartEntity;
import com.logging.LombokLoggingController;
import com.service.CartItemService;
import com.service.PurchaseService;
import com.service.ShoppingCartService;
import com.userdetails.CustomUserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/purchase")
public class PurchaseController {

    private final Logger logger;
    private final PurchaseService purchaseService;
    private final ShoppingCartService shoppingCartService;
    private final CartItemService cartItemService;

    @Autowired
    public PurchaseController(PurchaseService purchaseService, ShoppingCartService shoppingCartService,
                              CartItemService cartItemService){
        this.purchaseService = purchaseService;
        this.shoppingCartService = shoppingCartService;
        this.cartItemService = cartItemService;
        this.logger = LoggerFactory.getLogger(LombokLoggingController.class);
    }

    @GetMapping
    public List<PurchaseDTO> getPurchases(){
        List<PurchaseDTO> purchases = purchaseService.findAllPurchases();
        return purchases;
    }

    @PostMapping
    public ResponseEntity purchaseFromCart(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            CustomUserDetails userPrincipal = (CustomUserDetails) authentication.getPrincipal();
            Long userId = userPrincipal.getId();
            //ShoppingCartDTO cart = shoppingCartService.findCartDTOByUserId(userId);
           // 1) COPY CART ITEMS FROM THE CURRENT CART INTO PURCHASE
            List<CartItemDTO> cartItems = cartItemService.findAllCartItemsByUserId(userId);
            List<PurchaseDTO> purchases = purchaseService.convertCartItemsToPurchase(cartItems, userId);
            purchaseService.saveMultiplePurchases(purchases);
            //2) REMOVE CART ITEMS FROM CURRENT CART
            //logger.warn(String.valueOf(savedPurchases));

            cartItemService.deleteMultipleCartItems(cartItems);

            return ResponseEntity.ok().build();
        } else {
            return null;
        }
    }
}
