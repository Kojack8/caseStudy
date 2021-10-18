package com.repository;

import com.entitymodels.PurchaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PurchaseRepository extends JpaRepository<PurchaseEntity, Long> {

    List<PurchaseEntity> findAll();

    PurchaseEntity findById(long id);

    List<PurchaseEntity> findAllByUserId(long id);

}
