package com.service;

import com.dto.CartItemDTO;
import com.entitymodels.CartItemEntity;

import java.util.List;

public interface CartItemService {

    public CartItemDTO findById(int id);

    public List<CartItemDTO> findAllCartItems();

    public CartItemDTO convertToCartItemDTO(CartItemEntity cartItemEntity);

    public CartItemEntity convertToEntity(CartItemDTO cartItemDTO);

    public CartItemDTO save(CartItemDTO cartItemDTO);

    public void deleteById(Integer id);

}
