package com.repository;

import com.entitymodels.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    List<ProductEntity> findAll();

    ProductEntity findById(long id);

    List<ProductEntity> findAllByName(String name);
}
