package com.service.implementations;

import com.dto.ShoppingCartDTO;
import com.dto.UserDTO;
import com.entitymodels.ShoppingCartEntity;
import com.entitymodels.UserEntity;
import com.repository.ShoppingCartRepository;
import com.service.ShoppingCartService;
import com.service.UserService;
import com.userdetails.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartHibernateService implements ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;
    private final UserService userService;

    @Autowired
    public ShoppingCartHibernateService(ShoppingCartRepository shoppingCartRepository,
                                        UserService userService){
        this.shoppingCartRepository = shoppingCartRepository;
        this.userService = userService;
    }

    public ShoppingCartDTO findById(int id){
        ShoppingCartEntity shoppingCart = shoppingCartRepository.findById(id);
        return convertToShoppingCartDTO(shoppingCart);
    }

    public ShoppingCartEntity findShoppingCartEntityById(int id){
        ShoppingCartEntity shoppingCart = shoppingCartRepository.findById(id);
        return shoppingCart;
    }

    public ShoppingCartDTO findByUserEntity(UserEntity userEntity){
        ShoppingCartEntity cart = shoppingCartRepository.findByUserEntity(userEntity);
        ShoppingCartDTO cartDTO = convertToShoppingCartDTO(cart);
        return cartDTO;
    }

    public ShoppingCartEntity findCartEntityByUserID(Long id){
        UserEntity user = userService.findUserEntityById(id);
        ShoppingCartEntity cart = shoppingCartRepository.findByUserEntity(user);

        return cart;
    }

    public ShoppingCartDTO convertToShoppingCartDTO(ShoppingCartEntity shoppingCartEntity){
        ShoppingCartDTO shoppingCartDTO = new ShoppingCartDTO();
        shoppingCartDTO.setId(shoppingCartEntity.getId());
        shoppingCartDTO.setUpdatedDate(shoppingCartEntity.getUpdatedDate());
        Long userId = shoppingCartEntity.getUserEntity().getId();
        shoppingCartDTO.setUserId(userId);


        return shoppingCartDTO;
    }

    public ShoppingCartEntity convertToShoppingCartEntity(ShoppingCartDTO shoppingCartDTO){
        ShoppingCartEntity shoppingCart = new ShoppingCartEntity();
        shoppingCart.setId(shoppingCartDTO.getId());
        shoppingCart.setUpdatedDate(shoppingCartDTO.getUpdatedDate());
        UserEntity user = userService.findUserEntityById(shoppingCartDTO.getUserId());
        shoppingCart.setUserEntity(user);

        return shoppingCart;
    }

    public ShoppingCartDTO save(ShoppingCartDTO shoppingCartDTO){
        ShoppingCartEntity shoppingCart = convertToShoppingCartEntity(shoppingCartDTO);
        ShoppingCartEntity savedShoppingCart = shoppingCartRepository.save(shoppingCart);
        ShoppingCartDTO savedDTO = convertToShoppingCartDTO(savedShoppingCart);

        return savedDTO;
    }

    public void deleteById(Integer id){
        shoppingCartRepository.deleteById(id);
    }

}
