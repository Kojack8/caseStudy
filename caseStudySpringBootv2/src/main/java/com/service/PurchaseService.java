package com.service;

import com.dto.PurchaseDTO;
import com.entitymodels.PurchaseEntity;

import java.util.List;

public interface PurchaseService {

    PurchaseDTO findPurchaseById(Long id);

    List<PurchaseDTO> findAllPurchases();

    PurchaseDTO convertToPurchaseDTO(PurchaseEntity purchaseEntity);

    PurchaseEntity convertToPurchaseEntity(PurchaseDTO purchaseDTO);

    PurchaseDTO save(PurchaseDTO purchaseDTO);

    void deleteById(Long id);

}
