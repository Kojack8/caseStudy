package com.repo;

import com.entitymodels.CartItemEntity;
import com.entitymodels.ProductEntity;
import com.repository.CartItemRepository;
import com.repository.ProductRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProductRepoTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    @Order(1)
    @Rollback(value=false)
    public void saveProductTest(){
        ProductEntity product = new ProductEntity();
        product.setName("Gold Camera");
        product.setDescription("The gold standard of filmography");
        product.setStock(25);
        product.setPrice(10.25);
        ProductEntity testProduct = productRepository.save(product);
        assertEquals(testProduct.getId(), 1);

    }

    @Test
    @Order(2)
    public void getProductTest(){
        ProductEntity product = productRepository.findById(1);
        assertEquals(product.getName(), "Gold Camera");
        assertEquals(product.getId(), 1);
    }

    @Test
    @Order(3)
    public void getAllProductsTest(){
        List<ProductEntity> products = productRepository.findAll();
        assertThat(products.size()).isGreaterThan(0);
        products.forEach(product -> assertThat(product.getId()).isGreaterThan(0));

    }

    @Test
    @Order(4)
    public void updateProductTest(){
        ProductEntity product = productRepository.findById(1);
        assertEquals(productRepository.findById(1).getName(), "Gold Camera");

        product.setName("Black Camera");
        productRepository.save(product);
        assertEquals(productRepository.findById(1).getName(), "Black Camera");

    }

    @Test
    @Order(5)
    public void deleteProductTest(){
        productRepository.deleteById(1);
        Optional<ProductEntity> optionalProduct = Optional.ofNullable(productRepository.findById(1));
        assertEquals(optionalProduct, Optional.empty());
    }

}
