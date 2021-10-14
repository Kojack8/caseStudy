package com.dto;

import com.entitymodels.UserEntity;

import java.util.Collection;

public class RoleDTO {

    private Long id;
    private String name;
    private Collection<UserDTO> users;

    public RoleDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<UserDTO> getUsers() {
        return users;
    }

    public void setUsers(Collection<UserDTO> users) {
        this.users = users;
    }
}
