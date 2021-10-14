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
    private List<PurchaseDTO> purchases;
    private List<CartItemDTO> carts;

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

    public List<PurchaseDTO> getPurchases() {
        return purchases;
    }

    public void setPurchases(List<PurchaseDTO> purchases) {
        this.purchases = purchases;
    }

    public List<CartItemDTO> getCarts() {
        return carts;
    }

    public void setCarts(List<CartItemDTO> carts) {
        this.carts = carts;
    }
}
