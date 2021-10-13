package com.service;

import com.dto.ProductDTO;
import com.entitymodels.ProductEntity;

import java.util.List;

public interface ProductService {

    public ProductDTO findById(Integer id);

    public ProductDTO findByName(String name);

    public List<ProductDTO> findAllProducts();

    public ProductDTO convertToProductDTO(ProductEntity product);

    public ProductEntity convertToEntity(ProductDTO productDTO);

    public ProductDTO save(ProductDTO productDTO);

    void deleteById(Integer id);
}
