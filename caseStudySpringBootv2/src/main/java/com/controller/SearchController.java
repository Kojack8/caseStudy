package com.controller;

import com.entitymodels.ProductEntity;
import com.repository.ProductRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/search")
public class SearchController {

    private final ProductRepository productRepository;

    public SearchController(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @GetMapping
    public List<ProductEntity> getProducts(@RequestParam (required = false) String name){
        if (name == "") {
            return productRepository.findAll();
        }
        return productRepository.findAllByName(name);
    }


}
