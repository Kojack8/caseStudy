package com.controller;

import com.dto.*;
import com.entitymodels.ProductEntity;
import com.entitymodels.ShoppingCartEntity;
import com.logging.LombokLoggingController;
import com.service.CartItemService;
import com.service.ProductService;
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
    private final ProductService productService;

    @Autowired
    public PurchaseController(PurchaseService purchaseService, ShoppingCartService shoppingCartService,
                              CartItemService cartItemService, ProductService productService){
        this.purchaseService = purchaseService;
        this.shoppingCartService = shoppingCartService;
        this.cartItemService = cartItemService;
        this.productService = productService;
        this.logger = LoggerFactory.getLogger(LombokLoggingController.class);
    }

    @GetMapping
    public List<PurchaseDTOProductName> getUserPurchases(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            CustomUserDetails userPrincipal = (CustomUserDetails) authentication.getPrincipal();
            Long userId = userPrincipal.getId();
            List<PurchaseDTOProductName> purchases = purchaseService.findAllPurchasesByUserId(userId);
            return purchases;
        } else {
            return null;
        }
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
            for (CartItemDTO cartItem:cartItems){
                String productName = cartItem.getProduct();
                if (cartItem.getQuantity() > (productService.findByName(productName).getStock())){
                    return ResponseEntity.badRequest().build();
                }
            }
            List<PurchaseDTO> purchases = purchaseService.convertCartItemsToPurchase(cartItems, userId);
            purchaseService.saveMultiplePurchases(purchases);
            for (PurchaseDTO purchase:purchases){
                ProductDTO product = productService.findById(purchase.getProductId());
                product.setStock(product.getStock() - purchase.getQuantity());
                productService.changeQuantity(product);
            }
            //2) REMOVE CART ITEMS FROM CURRENT CART
            //logger.warn(String.valueOf(savedPurchases));

            cartItemService.deleteMultipleCartItems(cartItems);

            return ResponseEntity.ok().build();
        } else {
            return null;
        }
    }
}
