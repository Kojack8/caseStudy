package com.repository;

import com.entitymodels.RoleEntity;
import com.entitymodels.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

    List<RoleEntity> findAll();

    RoleEntity findById(long id);





}
