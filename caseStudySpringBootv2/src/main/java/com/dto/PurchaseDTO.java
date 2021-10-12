package com.dto;

import com.entitymodels.ProductEntity;
import com.entitymodels.UserEntity;

import java.time.LocalDateTime;

public class PurchaseDTO {

    private Long id;
    private UserEntity user;
    private ProductEntity product;
    private LocalDateTime purchasedAt;

    public PurchaseDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }

    public LocalDateTime getPurchasedAt() {
        return purchasedAt;
    }

    public void setPurchasedAt(LocalDateTime purchasedAt) {
        this.purchasedAt = purchasedAt;
    }
}
