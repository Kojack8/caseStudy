package com.dto;

import com.entitymodels.UserEntity;

import java.util.Collection;

public class RoleDTO {

    private Long id;
    private String name;
    private Collection<Long> userIDs;

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

    public Collection<Long> getUsers() {
        return userIDs;
    }

    public void setUsers(Collection<Long> userIDs) {
        this.userIDs = userIDs;
    }
}
