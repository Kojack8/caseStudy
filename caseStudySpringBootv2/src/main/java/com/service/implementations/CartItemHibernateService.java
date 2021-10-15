package com.service.implementations;

import com.dto.CartItemDTO;
import com.dto.ShoppingCartDTO;
import com.dto.UserDTO;
import com.entitymodels.CartItemEntity;
import com.entitymodels.ProductEntity;
import com.entitymodels.ShoppingCartEntity;
import com.repository.CartItemRepository;
import com.repository.ProductRepository;
import com.service.CartItemService;
import com.service.ProductService;
import com.service.ShoppingCartService;
import com.service.UserService;
import com.userdetails.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartItemHibernateService implements CartItemService {

    private final CartItemRepository cartItemRepository;
    private final UserService userService;
    private final ShoppingCartService shoppingCartService;
    private final ProductService productService;
    private final ProductRepository productRepository;

    @Autowired
    public CartItemHibernateService(CartItemRepository cartItemRepository, UserService userService,
                                    ShoppingCartService shoppingCartService, @Lazy ProductService productService,
                                    ProductRepository productRepository){
        this.cartItemRepository = cartItemRepository;
        this.userService = userService;
        this.shoppingCartService = shoppingCartService;
        this.productService = productService;
        this.productRepository = productRepository;
    }

    public CartItemDTO findById(int id){
        CartItemEntity cartItem = cartItemRepository.findById(id);
        return convertToCartItemDTO(cartItem);
    }

    public List<CartItemDTO> findByUserId(long id){
        UserDTO user = userService.findUserById(id);
        ShoppingCartDTO cart = shoppingCartService.findByUserEntity(userService.convertToUserEntity(user));
        List<CartItemEntity> cartItems = cartItemRepository.findAllByShoppingCartEntity(shoppingCartService.convertToShoppingCartEntity(cart));
        return (cartItems
                .stream()
                .map(this::convertToCartItemDTO)
                .collect(Collectors.toList()));

    }

    public List<CartItemDTO> findAllCartItems(){
        return ((List<CartItemEntity>) cartItemRepository
                .findAll())
                .stream()
                .map(this::convertToCartItemDTO)
                .collect(Collectors.toList());
    }

    public CartItemDTO addCartItem(int id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            CustomUserDetails userPrincipal = (CustomUserDetails) authentication.getPrincipal();
            Long userID = userPrincipal.getId();
            CartItemEntity cartItem = new CartItemEntity();
            ProductEntity productEntity = productRepository.findById(id);
            cartItem.setProduct(productEntity);
            ShoppingCartEntity cart = shoppingCartService.findCartEntityByUserID(userID);
            cartItem.setShoppingCartEntity(cart);
            cartItem.setQuantity(1);
            CartItemEntity savedCartItem = cartItemRepository.save(cartItem);
            return convertToCartItemDTO(savedCartItem);
        }

        return null;
    }

    public CartItemDTO convertToCartItemDTO(CartItemEntity cartItemEntity){
        CartItemDTO cartItemDTO = new CartItemDTO();
        cartItemDTO.setId(cartItemEntity.getId());
        cartItemDTO.setProduct(productService.convertToProductDTO(cartItemEntity.getProduct()));
        cartItemDTO.setQuantity(cartItemEntity.getQuantity());
        cartItemDTO.setUpdatedDate(cartItemEntity.getUpdatedDate());
        cartItemDTO.setShoppingCartDTO(shoppingCartService.convertToShoppingCartDTO(cartItemEntity.getShoppingCartEntity()));

        return cartItemDTO;
    }

    public CartItemEntity convertToEntity(CartItemDTO cartItemDTO){
        CartItemEntity cartItem = new CartItemEntity();
        cartItem.setProduct(productService.convertToEntity(cartItemDTO.getProduct()));
        cartItem.setQuantity(cartItemDTO.getQuantity());
        cartItem.setUpdatedDate(cartItemDTO.getUpdatedDate());
        cartItem.setShoppingCartEntity(shoppingCartService.convertToShoppingCartEntity(cartItemDTO.getShoppingCartDTO()));

        return cartItem;
    }

    public CartItemDTO save(CartItemDTO cartItemDTO){
        CartItemEntity cartItem = convertToEntity(cartItemDTO);
        CartItemEntity savedCartItem = cartItemRepository.save(cartItem);
        CartItemDTO savedDTO = convertToCartItemDTO(savedCartItem);

        return savedDTO;
    }

    public void deleteById(Integer id){
        cartItemRepository.deleteById(id);
    }
}
