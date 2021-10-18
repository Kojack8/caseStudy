package com.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PurchaseDTOProductName {

        private Long id;
        private Long userId;
        private Integer quantity;
        private String productName;
        private String purchasedAt;

        public PurchaseDTOProductName() {
        }

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public Long getUserId() {
                return userId;
        }

        public void setUserId(Long userId) {
                this.userId = userId;
        }

        public Integer getQuantity() {
                return quantity;
        }

        public void setQuantity(Integer quantity) {
                this.quantity = quantity;
        }

        public String getProductName() {
                return productName;
        }

        public void setProductName(String productName) {
                this.productName = productName;
        }

        public String getPurchasedAt() {
                return purchasedAt;
        }

        public void setPurchasedAt(LocalDateTime purchasedAt) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:ma MM/dd/yyyy");
                this.purchasedAt = formatter.format(purchasedAt);
        }
}
