package com.service.implementations;

import com.dto.ProductDTO;
import com.entitymodels.ProductEntity;
import com.entitymodels.UserEntity;
import com.repository.ProductRepository;
import com.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductHibernateService implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductHibernateService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductDTO findByName(String name) {
        ProductEntity product = productRepository.findByName(name);
        return convertToProductDTO(product);
    }

    public ProductDTO findById(Integer id){
        ProductEntity product = productRepository.findById(id).orElseThrow(RuntimeException::new);
        return convertToProductDTO(product);

    }

    public List<ProductDTO> findAllProducts() {
        return ((List<ProductEntity>) productRepository
                .findAll())
                .stream()
                .map(this::convertToProductDTO)
                .collect(Collectors.toList());
    }

    public ProductDTO convertToProductDTO(ProductEntity product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setDescription(product.getDescription());
        productDTO.setStock(product.getStock());
        productDTO.setUpdatedDate(product.getUpdatedDate());
        productDTO.setPrice(product.getPrice());
        productDTO.setPurchases(product.getPurchases());
        productDTO.setCarts(product.getCarts());

        return productDTO;
    }

    public ProductEntity convertToEntity(ProductDTO productDTO){
        ProductEntity product = new ProductEntity();
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setStock(productDTO.getStock());
        product.setUpdatedDate(productDTO.getUpdatedDate());
        product.setPrice(productDTO.getPrice());
        product.setPurchases(productDTO.getPurchases());
        product.setCarts(productDTO.getCarts());
        return product;
    }

    public ProductDTO save(ProductDTO productDTO){
        ProductEntity product = convertToEntity(productDTO);
        ProductEntity savedProduct = productRepository.save(product);
        ProductDTO savedDTO = convertToProductDTO(savedProduct);

        return savedDTO;
    }

    public void deleteById(Integer id) {
        productRepository.deleteById(id);
    }

}
