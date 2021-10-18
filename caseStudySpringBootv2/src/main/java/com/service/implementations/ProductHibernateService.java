package com.service.implementations;

import com.dto.ProductDTO;
import com.entitymodels.ProductEntity;
import com.entitymodels.UserEntity;
import com.repository.ProductRepository;
import com.service.CartItemService;
import com.service.ProductService;
import com.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductHibernateService implements ProductService {

    private final ProductRepository productRepository;
    private final CartItemService cartItemService;
    private final PurchaseService purchaseService;

    @Autowired
    public ProductHibernateService(ProductRepository productRepository, CartItemService cartItemService,
                                   @Lazy PurchaseService purchaseService) {
        this.productRepository = productRepository;
        this.cartItemService = cartItemService;
        this.purchaseService = purchaseService;
    }

    public ProductDTO findByName(String name) {
        ProductEntity product = productRepository.findByName(name);
        return convertToProductDTO(product);
    }

    public ProductDTO findById(Integer id){
        ProductEntity product = productRepository.findById(id).orElseThrow(RuntimeException::new);
        return convertToProductDTO(product);
    }

    public ProductEntity findProductEntityById(Integer id){
        ProductEntity product = productRepository.findById(id)
                .orElseThrow(RuntimeException::new);
        return product;
    }

    public List<ProductDTO> findAllProducts() {
        return ((List<ProductEntity>) productRepository
                .findAll())
                .stream()
                .map(this::convertToProductDTO)
                .collect(Collectors.toList());
    }

    public ProductDTO convertToProductDTO(ProductEntity product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setDescription(product.getDescription());
        productDTO.setStock(product.getStock());
        productDTO.setUpdatedDate(product.getUpdatedDate());
        productDTO.setPrice(product.getPrice());
        productDTO.setPurchaseIds(product.getPurchases()
                .stream()
                .map(purchases -> purchases.getId())
                .collect(Collectors.toList()));
        productDTO.setCartItemIds(product.getCarts()
                .stream()
                .map(cartItems -> cartItems.getId())
                .collect(Collectors.toList()));


        return productDTO;
    }

    public ProductEntity convertToEntity(ProductDTO productDTO){
        ProductEntity product = new ProductEntity();
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setStock(productDTO.getStock());
        product.setUpdatedDate(productDTO.getUpdatedDate());
        product.setPrice(productDTO.getPrice());
        product.setPurchases(productDTO.getPurchaseIds()
                .stream()
                .map(purchases -> purchaseService.findPurchaseEntityById(purchases))
                .collect(Collectors.toList()));
        product.setCarts(productDTO.getCartItemIds()
                .stream()
                .map(cartItems -> cartItemService.findCartItemEntityById(cartItems))
                .collect(Collectors.toList()));

        return product;
    }

    public ProductEntity convertToEntityWithID(ProductDTO productDTO){
        ProductEntity product = new ProductEntity();
        product.setId(productDTO.getId());
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setStock(productDTO.getStock());
        product.setUpdatedDate(productDTO.getUpdatedDate());
        product.setPrice(productDTO.getPrice());
        product.setPurchases(productDTO.getPurchaseIds()
                .stream()
                .map(purchases -> purchaseService.findPurchaseEntityById(purchases))
                .collect(Collectors.toList()));
        product.setCarts(productDTO.getCartItemIds()
                .stream()
                .map(cartItems -> cartItemService.findCartItemEntityById(cartItems))
                .collect(Collectors.toList()));

        return product;
    }


    public void changeQuantity(ProductDTO productDTO){
        ProductDTO originalProduct = findByName(productDTO.getName());
        originalProduct.setStock(productDTO.getStock());
        saveQuantityChange(originalProduct);
    }

    public void saveQuantityChange(ProductDTO productDTO){
        ProductEntity product = convertToEntityWithID(productDTO);
        ProductEntity savedProduct = productRepository.save(product);
    }

    public ProductDTO save(ProductDTO productDTO){
        ProductEntity product = convertToEntity(productDTO);
        ProductEntity savedProduct = productRepository.save(product);
        ProductDTO savedDTO = convertToProductDTO(savedProduct);

        return savedDTO;
    }

    public ProductDTO saveNew(ProductDTO productDTO){
        ProductEntity product = createNew(productDTO);
        ProductEntity savedProduct = productRepository.save(product);
        return newProductDTO(savedProduct);
    }

    public ProductEntity createNew(ProductDTO productDTO){
        ProductEntity product = new ProductEntity();
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setStock(productDTO.getStock());
        product.setPrice(productDTO.getPrice());

        return product;
    }

    public ProductDTO newProductDTO(ProductEntity product){
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setDescription(product.getDescription());
        productDTO.setStock(product.getStock());
        productDTO.setUpdatedDate(product.getUpdatedDate());
        productDTO.setPrice(product.getPrice());
        return productDTO;
    }

    public void deleteById(Integer id) {
        productRepository.deleteById(id);
    }

}
