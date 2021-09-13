package com.entitymodels;

import org.apache.catalina.User;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "role")
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    @ManyToMany(mappedBy = "roles")
    private Collection<UserEntity> users;

    @ManyToMany
    @JoinTable(
            name = "roles_privileges",
            joinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id" ),
                inverseJoinColumns = @JoinColumn(
                    name = "privilege_id", referencedColumnName = "id"))
        private Collection<PrivilegeEntity> privileges;

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

    public Collection<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(Collection<UserEntity> users) {
        this.users = users;
    }

    public Collection<PrivilegeEntity> getPrivileges() {
        return privileges;
    }

    public void setPrivileges(Collection<PrivilegeEntity> privileges) {
        this.privileges = privileges;
    }
}
