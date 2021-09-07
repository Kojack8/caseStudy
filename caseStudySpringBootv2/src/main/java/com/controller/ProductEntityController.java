package com.controller;

import com.entitymodels.ProductEntity;
import com.repository.ProductRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/products")
public class ProductEntityController {

    private final ProductRepository productRepository;

    public ProductEntityController(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @GetMapping
    public List<ProductEntity> getProducts(){
        return productRepository.findAll();
    }

    @PostMapping
    public ResponseEntity createProduct(@RequestBody ProductEntity product) throws URISyntaxException {
        ProductEntity savedProduct = productRepository.save(product);
        return ResponseEntity.created(new URI("/productEntities" + savedProduct.getId())).body(savedProduct);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateProduct(@PathVariable Long id, @RequestBody ProductEntity product) {
        ProductEntity currentProduct = productRepository.findById(id).orElseThrow(RuntimeException::new);
        currentProduct.setName(product.getName());
        currentProduct.setDescription(product.getDescription());
        currentProduct = productRepository.save(product);

        return ResponseEntity.ok(currentProduct);
    }

    @DeleteMapping("/{id")
    public ResponseEntity deleteProduct(@PathVariable long id) {
        productRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }


}
