package com.repo;

import com.entitymodels.RoleEntity;
import com.repository.RoleRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import javax.management.relation.Role;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RoleRepoTest {

    @Autowired
    RoleRepository roleRepository;

    @Test
    @Order(1)
    @Rollback(value=false)
    public void saveRoleTest(){
        RoleEntity role = new RoleEntity();
        role.setName("ROLE_USER");
        RoleEntity savedRole = roleRepository.save(role);
        assertEquals(savedRole.getId(), 1);

    }

    @Test
    @Order(2)
    public void getRoleTest(){
        RoleEntity role = roleRepository.findById(1);
        assertEquals(role.getName(), "ROLE_USER");
        assertEquals(role.getId(), 1);
    }

    @Test
    @Order(3)
    public void getAllRolesTest(){
        List<RoleEntity> roles = roleRepository.findAll();
        assertThat(roles.size()).isGreaterThan(0);
        roles.forEach(role -> assertThat(role.getId()).isGreaterThan(0));
    }

    @Test
    @Order(4)
    public void updateRoleTest(){
        RoleEntity role = roleRepository.findById(1);
        assertEquals(roleRepository.findById(1).getName(), "ROLE_USER");

        role.setName("ROLE_ADMIN");
        roleRepository.save(role);
        assertEquals(roleRepository.findById(1).getName(), "ROLE_ADMIN");
    }

    @Test
    @Order(5)
    public void deleteRoleTest(){
        roleRepository.deleteById((long) 1);
        Optional<RoleEntity> optionalRole = Optional.ofNullable(roleRepository.findById(1));
        assertEquals(optionalRole, Optional.empty());
    }


}
