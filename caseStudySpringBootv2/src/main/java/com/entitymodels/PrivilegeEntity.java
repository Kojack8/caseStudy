package com.entitymodels;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "privilege")
public class PrivilegeEntity {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "privileges")
    private Collection<RoleEntity> roles;
}
