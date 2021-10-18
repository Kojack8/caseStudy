package com.entitymodels;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "cart_item")
public class CartItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "updated_date", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp updatedDate;

    @OneToOne
    @JoinColumn(name = "shopping_cart_id", referencedColumnName = "id")
    private ShoppingCartEntity shoppingCartEntity;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
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

    public ShoppingCartEntity getShoppingCartEntity() {
        return shoppingCartEntity;
    }

    public void setShoppingCartEntity(ShoppingCartEntity shoppingCartEntity) {
        this.shoppingCartEntity = shoppingCartEntity;
    }
}
