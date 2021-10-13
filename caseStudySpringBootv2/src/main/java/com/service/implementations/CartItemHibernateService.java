package com.service.implementations;

import com.dto.CartItemDTO;
import com.entitymodels.CartItemEntity;
import com.entitymodels.ProductEntity;
import com.repository.CartItemRepository;
import com.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartItemHibernateService implements CartItemService {

    private final CartItemRepository cartItemRepository;

    @Autowired
    public CartItemHibernateService(CartItemRepository cartItemRepository){
        this.cartItemRepository = cartItemRepository;
    }

    public CartItemDTO findById(int id){
        CartItemEntity cartItem = cartItemRepository.findById(id);
        return convertToCartItemDTO(cartItem);
    }

    public List<CartItemDTO> findAllCartItems(){
        return ((List<CartItemEntity>) cartItemRepository
                .findAll())
                .stream()
                .map(this::convertToCartItemDTO)
                .collect(Collectors.toList());
    }

    public CartItemDTO convertToCartItemDTO(CartItemEntity cartItemEntity){
        CartItemDTO cartItemDTO = new CartItemDTO();
        cartItemDTO.setId(cartItemEntity.getId());
        cartItemDTO.setProduct(cartItemEntity.getProduct());
        cartItemDTO.setQuantity(cartItemEntity.getQuantity());
        cartItemDTO.setUpdatedDate(cartItemEntity.getUpdatedDate());
        cartItemDTO.setShoppingCartEntity(cartItemEntity.getShoppingCartEntity());

        return cartItemDTO;
    }

    public CartItemEntity convertToEntity(CartItemDTO cartItemDTO){
        CartItemEntity cartItem = new CartItemEntity();
        cartItem.setProduct(cartItemDTO.getProduct());
        cartItem.setQuantity(cartItemDTO.getQuantity());
        cartItem.setUpdatedDate(cartItemDTO.getUpdatedDate());
        cartItem.setShoppingCartEntity(cartItemDTO.getShoppingCartEntity());

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
