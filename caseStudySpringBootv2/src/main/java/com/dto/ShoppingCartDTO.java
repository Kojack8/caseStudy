package com.dto;

import com.entitymodels.UserEntity;

import java.sql.Timestamp;

public class ShoppingCartDTO {

    private Integer id;
    private Timestamp updatedDate;
    private UserDTO userDTO;

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

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }
}
