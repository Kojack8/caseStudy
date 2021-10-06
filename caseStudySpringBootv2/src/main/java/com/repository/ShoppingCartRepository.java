package com.repository;

import com.entitymodels.CartItemEntity;
import com.entitymodels.ShoppingCartEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCartEntity, Integer> {

    List<ShoppingCartEntity> findAll();

    ShoppingCartEntity findById(int id);
}
