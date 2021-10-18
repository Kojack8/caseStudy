package com.controller;

import com.dto.ProductDTO;
import com.entitymodels.ProductEntity;
import com.repository.ProductRepository;
import com.service.ProductService;
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
    private final ProductService productService;

    public ProductEntityController(ProductRepository productRepository, ProductService productService){
        this.productRepository = productRepository;
        this.productService = productService;
    }
    // WHERE I LEFT OFF: CONVERT CONTROLLER FROM USING ENTITIES TO DTOS
    @GetMapping
    public List<ProductDTO> getProducts(){
        return productService.findAllProducts();
    }

    @PostMapping
    public ResponseEntity createProduct(@RequestBody ProductDTO product) throws URISyntaxException {
        ProductDTO savedProduct = productService.saveNew(product);
        //ProductDTO savedProduct = productService.createNew(product);
        return ResponseEntity.created(new URI("/productEntities" + savedProduct.getId())).body(savedProduct);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateProduct(@PathVariable Integer id, @RequestBody ProductDTO product) {
        //ProductEntity currentProduct = productRepository.findById(id).orElseThrow(RuntimeException::new);
        ProductDTO currentProduct = productService.findById(id);
        currentProduct.setName(product.getName());
        currentProduct.setDescription(product.getDescription());
        ProductDTO savedProduct = productService.save(currentProduct);

        return ResponseEntity.ok(savedProduct);
    }

    @DeleteMapping("/{id")
    public ResponseEntity deleteProduct(@PathVariable Integer id) {
        productService.deleteById(id);
        return ResponseEntity.ok().build();
    }


}
