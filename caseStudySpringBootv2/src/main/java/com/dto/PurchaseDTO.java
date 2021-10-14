package com.dto;

import com.entitymodels.ProductEntity;
import com.entitymodels.UserEntity;

import java.time.LocalDateTime;

public class PurchaseDTO {

    private Long id;
    private UserDTO user;
    private ProductDTO product;
    private LocalDateTime purchasedAt;

    public PurchaseDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public ProductDTO getProduct() {
        return product;
    }

    public void setProduct(ProductDTO product) {
        this.product = product;
    }

    public LocalDateTime getPurchasedAt() {
        return purchasedAt;
    }

    public void setPurchasedAt(LocalDateTime purchasedAt) {
        this.purchasedAt = purchasedAt;
    }
}
