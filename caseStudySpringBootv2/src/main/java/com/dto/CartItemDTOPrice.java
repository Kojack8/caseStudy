package com.dto;

import java.sql.Timestamp;

public class CartItemDTOPrice {

    private Integer id;
    private String productName;
    private Integer quantity;
    private Timestamp updatedDate;
    private Integer shoppingCartId;
    private Double price;

    public CartItemDTOPrice() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Timestamp getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Timestamp updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Integer getShoppingCartId() {
        return shoppingCartId;
    }

    public void setShoppingCartId(Integer shoppingCartId) {
        this.shoppingCartId = shoppingCartId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
