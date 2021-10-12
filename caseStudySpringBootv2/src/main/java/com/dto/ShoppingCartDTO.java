package com.dto;

import com.entitymodels.UserEntity;

import java.sql.Timestamp;

public class ShoppingCartDTO {

    private Integer id;
    private Timestamp updatedDate;
    private UserEntity userEntity;

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

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }
}
