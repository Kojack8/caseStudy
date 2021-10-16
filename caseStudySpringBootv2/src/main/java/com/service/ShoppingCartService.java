package com.service;

import com.dto.ShoppingCartDTO;
import com.entitymodels.ShoppingCartEntity;
import com.entitymodels.UserEntity;

public interface ShoppingCartService {

    ShoppingCartDTO findById(int id);

    ShoppingCartDTO findByUserEntity(UserEntity userEntity);

    ShoppingCartEntity findShoppingCartEntityById(int id);

    ShoppingCartEntity findCartEntityByUserID(Long id);

    ShoppingCartDTO convertToShoppingCartDTO(ShoppingCartEntity shoppingCartEntity);

    ShoppingCartEntity convertToShoppingCartEntity(ShoppingCartDTO shoppingCartDTO);

    ShoppingCartDTO save(ShoppingCartDTO shoppingCartDTO);

    void deleteById(Integer id);

}
