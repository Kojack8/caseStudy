package com.service;

import com.dto.CartItemDTO;
import com.entitymodels.CartItemEntity;

import java.util.List;

public interface CartItemService {

    CartItemDTO findById(int id);

    List<CartItemDTO> findAllCartItems();

    CartItemDTO convertToCartItemDTO(CartItemEntity cartItemEntity);

    CartItemEntity convertToEntity(CartItemDTO cartItemDTO);

    CartItemDTO save(CartItemDTO cartItemDTO);

    void deleteById(Integer id);

}
