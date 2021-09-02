package com.casestudy.project.repository;

import com.casestudy.project.entitymodels.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, Long> {
}
