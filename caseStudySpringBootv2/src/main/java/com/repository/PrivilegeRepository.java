package com.repository;

import com.entitymodels.PrivilegeEntity;
import com.entitymodels.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PrivilegeRepository extends JpaRepository<PrivilegeEntity, Long> {

    PrivilegeEntity findByName(String name);


}