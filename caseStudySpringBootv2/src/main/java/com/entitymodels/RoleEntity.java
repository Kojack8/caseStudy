package com.entitymodels;

import org.apache.catalina.User;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "user")
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


}
