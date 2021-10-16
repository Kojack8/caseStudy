package com.dto;

import com.entitymodels.UserEntity;

import java.sql.Timestamp;

public class ShoppingCartDTO {

    private Integer id;
    private Timestamp updatedDate;
    private Long userId;

    public ShoppingCartDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Timestamp getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Timestamp updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
