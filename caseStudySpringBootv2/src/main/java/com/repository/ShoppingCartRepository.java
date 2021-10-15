package com.repository;

import com.dto.ShoppingCartDTO;
import com.entitymodels.CartItemEntity;
import com.entitymodels.ShoppingCartEntity;
import com.entitymodels.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCartEntity, Integer> {

    List<ShoppingCartEntity> findAll();

    ShoppingCartEntity findById(int id);

    ShoppingCartEntity findByUserEntity(UserEntity userEntity);


}
