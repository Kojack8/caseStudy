package com.repository;

import com.entitymodels.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    List<UserEntity> findAll();

    UserEntity findById(long id);


}
