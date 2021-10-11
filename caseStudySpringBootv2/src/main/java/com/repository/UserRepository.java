package com.repository;

import com.entitymodels.RoleEntity;
import com.entitymodels.UserEntity;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    List<UserEntity> findAll();

    UserEntity findById(long id);

    UserEntity findByEmail(String email);

    UserEntity save(UserEntity user);

    void deleteById(Long id);







}
