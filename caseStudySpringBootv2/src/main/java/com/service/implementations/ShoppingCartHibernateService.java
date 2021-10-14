package com.service.implementations;

import com.dto.ShoppingCartDTO;
import com.dto.UserDTO;
import com.entitymodels.ShoppingCartEntity;
import com.entitymodels.UserEntity;
import com.repository.ShoppingCartRepository;
import com.service.ShoppingCartService;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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

    public ShoppingCartDTO convertToShoppingCartDTO(ShoppingCartEntity shoppingCartEntity){
        ShoppingCartDTO shoppingCartDTO = new ShoppingCartDTO();
        shoppingCartDTO.setId(shoppingCartEntity.getId());
        shoppingCartDTO.setUpdatedDate(shoppingCartEntity.getUpdatedDate());
        UserDTO user = userService.convertToUserDTO(shoppingCartEntity.getUserEntity());
        shoppingCartDTO.setUserDTO(user);

        return shoppingCartDTO;
    }

    public ShoppingCartEntity convertToShoppingCartEntity(ShoppingCartDTO shoppingCartDTO){
        ShoppingCartEntity shoppingCart = new ShoppingCartEntity();
        shoppingCart.setId(shoppingCartDTO.getId());
        shoppingCart.setUpdatedDate(shoppingCartDTO.getUpdatedDate());
        UserEntity user = userService.convertToUserEntity(shoppingCartDTO.getUserDTO());
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
