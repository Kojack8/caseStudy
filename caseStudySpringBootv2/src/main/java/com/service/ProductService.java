package com.service;

import com.dto.ProductDTO;
import com.entitymodels.ProductEntity;

import java.util.List;

public interface ProductService {

    ProductDTO findById(Integer id);

    ProductDTO findByName(String name);

    List<ProductDTO> findAllProducts();

    ProductEntity findProductEntityById(Integer id);

    ProductDTO convertToProductDTO(ProductEntity product);

    ProductEntity convertToEntity(ProductDTO productDTO);

    ProductDTO save(ProductDTO productDTO);

    void deleteById(Integer id);
}
