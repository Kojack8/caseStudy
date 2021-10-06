package com.repository;

import com.entitymodels.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {

    List<ProductEntity> findAll();

    ProductEntity findById(int id);

    ProductEntity findByName(String name);

    List<ProductEntity> findAllByName(String name);
}
