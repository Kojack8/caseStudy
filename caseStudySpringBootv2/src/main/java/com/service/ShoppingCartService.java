package com.service;

import com.dto.ShoppingCartDTO;
import com.entitymodels.ShoppingCartEntity;

public interface ShoppingCartService {

    ShoppingCartDTO findById(int id);

    ShoppingCartDTO convertToShoppingCartDTO(ShoppingCartEntity shoppingCartEntity);

    ShoppingCartEntity convertToShoppingCartEntity(ShoppingCartDTO shoppingCartDTO);

    ShoppingCartDTO save(ShoppingCartDTO shoppingCartDTO);

    void deleteById(Integer id);

}
