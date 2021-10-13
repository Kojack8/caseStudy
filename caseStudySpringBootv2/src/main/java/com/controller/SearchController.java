package com.controller;

import com.dto.ProductDTO;
import com.entitymodels.ProductEntity;
import com.repository.ProductRepository;
import com.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/search")
public class SearchController {

    private final ProductRepository productRepository;
    private final ProductService productService;

    public SearchController(ProductRepository productRepository, ProductService productService){
        this.productRepository = productRepository;
        this.productService = productService;
    }

    @GetMapping
    public List<ProductDTO> getProducts(@RequestParam (required = false) String name){
        if (name.equals("") || name.equals(null)) {
            //return productRepository.findAll();
            return productService.findAllProducts();
        }
        List<ProductDTO> product = new ArrayList<ProductDTO>();
        product.add(productService.findByName(name));
        return product;
    }


}
