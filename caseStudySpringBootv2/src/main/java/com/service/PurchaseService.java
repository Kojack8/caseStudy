package com.service;

import com.dto.CartItemDTO;
import com.dto.PurchaseDTO;
import com.entitymodels.PurchaseEntity;

import java.util.List;

public interface PurchaseService {

    PurchaseDTO findPurchaseById(Long id);

    PurchaseEntity findPurchaseEntityById(Long id);

    List<PurchaseDTO> findAllPurchases();

    PurchaseDTO convertToPurchaseDTO(PurchaseEntity purchaseEntity);

    List<PurchaseDTO> convertCartItemsToPurchase(List<CartItemDTO> cartItems, Long userId);

    PurchaseEntity convertToPurchaseEntity(PurchaseDTO purchaseDTO);

    PurchaseDTO save(PurchaseDTO purchaseDTO);

    List<PurchaseDTO> saveMultiplePurchases(List<PurchaseDTO> purchases);

    void deleteById(Long id);

}
