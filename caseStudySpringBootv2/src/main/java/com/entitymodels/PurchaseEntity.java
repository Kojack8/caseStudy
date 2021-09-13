package com.entitymodels;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "purchases")
public class PurchaseEntity {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    UserEntity user;

    @ManyToOne
    @JoinColumn(name = "product_id")
    ProductEntity product;

    LocalDateTime purchasedAt;

}
