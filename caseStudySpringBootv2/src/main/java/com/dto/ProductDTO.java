package com.dto;

import com.entitymodels.CartItemEntity;
import com.entitymodels.ProductEntity;
import com.entitymodels.PurchaseEntity;
import com.entitymodels.UserEntity;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public class ProductDTO {

    private Integer id;
    private String name;
    private String description;
    private Integer stock;
    private Timestamp updatedDate;
    private Double price;
    private List<Long> purchaseIds;
    private List<Integer> cartItemIds;

    public ProductDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Timestamp getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Timestamp updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public List<Long> getPurchaseIds() {
        return purchaseIds;
    }

    public void setPurchaseIds(List<Long> purchaseIds) {
        this.purchaseIds = purchaseIds;
    }

    public List<Integer> getCartItemIds() {
        return cartItemIds;
    }

    public void setCartItemIds(List<Integer> cartItemIds) {
        this.cartItemIds = cartItemIds;
    }
}
