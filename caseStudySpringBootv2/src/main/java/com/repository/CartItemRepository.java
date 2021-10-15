package com.repository;

import com.entitymodels.CartItemEntity;
import com.entitymodels.ShoppingCartEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItemEntity, Integer> {

    List<CartItemEntity> findAll();

    CartItemEntity findById(int id);

    List<CartItemEntity> findAllByShoppingCartEntity(ShoppingCartEntity shoppingCartEntity);
}
