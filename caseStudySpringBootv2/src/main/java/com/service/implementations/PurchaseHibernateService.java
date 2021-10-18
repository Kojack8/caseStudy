package com.service.implementations;

import com.dto.CartItemDTO;
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

    public PurchaseEntity findPurchaseEntityById(Long id){
        PurchaseEntity purchase = purchaseRepository.findById(id).orElseThrow(RuntimeException::new);
        return purchase;
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
        //UserDTO user = userService.convertToUserDTO(purchaseEntity.getUser());
        purchaseDTO.setUserId(purchaseEntity.getUser().getId());
        //ProductDTO product = productService.convertToProductDTO(purchaseEntity.getProduct());
        purchaseDTO.setProductId(purchaseEntity.getProduct().getId());
        purchaseDTO.setPurchasedAt(purchaseEntity.getPurchasedAt());

        return purchaseDTO;
    }

    public PurchaseEntity convertToPurchaseEntity(PurchaseDTO purchaseDTO) {
        PurchaseEntity purchase = new PurchaseEntity();
        purchase.setId(purchaseDTO.getId());
        purchase.setQuantity(purchaseDTO.getQuantity());
        //UserEntity user = userService.convertToUserEntity(purchaseDTO.getUser());
        purchase.setUser(userService.findUserEntityById(purchaseDTO.getUserId()));
        //ProductEntity product = productService.convertToEntity(purchaseDTO.getProduct());
        purchase.setProduct(productService.findProductEntityById(purchaseDTO.getProductId()));
        purchase.setPurchasedAt(purchaseDTO.getPurchasedAt());

        return purchase;
    }

    public PurchaseDTO save(PurchaseDTO purchaseDTO) {
        PurchaseEntity purchase = convertToPurchaseEntity(purchaseDTO);
        PurchaseEntity savedPurchase = purchaseRepository.save(purchase);
        PurchaseDTO savedDTO = convertToPurchaseDTO(savedPurchase);

        return savedDTO;
    }

    public PurchaseDTO convertCartItemToPurchase(CartItemDTO cartItem, Long userId){
        PurchaseDTO purchase = new PurchaseDTO();
        purchase.setUserId(userId);
        purchase.setQuantity(cartItem.getQuantity());
        purchase.setProductId(productService.findByName(cartItem.getProduct()).getId());
        return purchase;
    }

    public List<PurchaseDTO> convertCartItemsToPurchase(List<CartItemDTO> cartItems, Long userId){
        return cartItems
                .stream()
                .map(cartItem -> convertCartItemToPurchase(cartItem, userId))
                .collect(Collectors.toList());
    }

    public List<PurchaseDTO> saveMultiplePurchases(List<PurchaseDTO> purchases){
        return purchases
                .stream()
                .map(this::save)
                .collect(Collectors.toList());

    }

    public void deleteById(Long id) {
        purchaseRepository.deleteById(id);
    }
}
