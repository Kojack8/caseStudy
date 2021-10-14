package com.service.implementations;

import com.dto.ProductDTO;
import com.dto.PurchaseDTO;
import com.dto.UserDTO;
import com.entitymodels.ProductEntity;
import com.entitymodels.PurchaseEntity;
import com.entitymodels.UserEntity;
import com.repository.PurchaseRepository;
import com.service.ProductService;
import com.service.PurchaseService;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PurchaseHibernateService implements PurchaseService {

    private final PurchaseRepository purchaseRepository;
    private final UserService userService;
    private final ProductService productService;

    @Autowired
    public PurchaseHibernateService(PurchaseRepository purchaseRepository, UserService userService,
                                    ProductService productService) {
        this.purchaseRepository = purchaseRepository;
        this.userService = userService;
        this.productService = productService;
    }

    public PurchaseDTO findPurchaseById(Long id) {
        PurchaseEntity purchase = purchaseRepository.findById(id).orElseThrow(RuntimeException::new);
        return convertToPurchaseDTO(purchase);
    }

    public List<PurchaseDTO> findAllPurchases() {
        return ((List<PurchaseEntity>) purchaseRepository
                .findAll())
                .stream()
                .map(this::convertToPurchaseDTO)
                .collect(Collectors.toList());
    }

    public PurchaseDTO convertToPurchaseDTO(PurchaseEntity purchaseEntity) {
        PurchaseDTO purchaseDTO = new PurchaseDTO();
        purchaseDTO.setId(purchaseEntity.getId());
        UserDTO user = userService.convertToUserDTO(purchaseEntity.getUser());
        purchaseDTO.setUser(user);
        ProductDTO product = productService.convertToProductDTO(purchaseEntity.getProduct());
        purchaseDTO.setProduct(product);
        purchaseDTO.setPurchasedAt(purchaseEntity.getPurchasedAt());

        return purchaseDTO;
    }

    public PurchaseEntity convertToPurchaseEntity(PurchaseDTO purchaseDTO) {
        PurchaseEntity purchase = new PurchaseEntity();
        purchase.setId(purchaseDTO.getId());
        UserEntity user = userService.convertToUserEntity(purchaseDTO.getUser());
        purchase.setUser(user);
        ProductEntity product = productService.convertToEntity(purchaseDTO.getProduct());
        purchase.setProduct(product);
        purchase.setPurchasedAt(purchaseDTO.getPurchasedAt());

        return purchase;
    }

    public PurchaseDTO save(PurchaseDTO purchaseDTO) {
        PurchaseEntity purchase = convertToPurchaseEntity(purchaseDTO);
        PurchaseEntity savedPurchase = purchaseRepository.save(purchase);
        PurchaseDTO savedDTO = convertToPurchaseDTO(savedPurchase);

        return savedDTO;
    }

    public void deleteById(Long id) {
        purchaseRepository.deleteById(id);
    }
}
