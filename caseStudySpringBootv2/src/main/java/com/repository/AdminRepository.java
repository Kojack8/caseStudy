package com.repository;

import com.entitymodels.AdminEntity;
import org.aspectj.apache.bcel.util.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface AdminRepository extends JpaRepository<AdminEntity, Long> {

    AdminEntity save(AdminEntity admin);

    AdminEntity findByName(String name);
}
