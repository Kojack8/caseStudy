package com.service;

import com.dto.CartItemDTO;
import com.entitymodels.CartItemEntity;

import java.util.List;

public interface CartItemService {

    CartItemDTO findById(int id);

    CartItemEntity findCartItemEntityById(int id);

    List<CartItemDTO> findAllCartItemsByUserId(long id);

    List<CartItemDTO> findAllCartItems();

    CartItemDTO addCartItem(int id, int quantity);

    CartItemDTO convertToCartItemDTO(CartItemEntity cartItemEntity);

    CartItemEntity convertToEntity(CartItemDTO cartItemDTO);

    CartItemDTO save(CartItemDTO cartItemDTO);

    void deleteMultipleCartItems(List<CartItemDTO> cartItems);

    void deleteById(Integer id);

}
