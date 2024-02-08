package com.register.registration.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.Setter;

import java.util.List;

@Entity

@Data
@Table(name = "bill")
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long billId;

    @Column(name = "total_price")
    private double totalPrice;

    @Setter
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @ElementCollection
    @CollectionTable(name = "bill_product_ids", joinColumns = @JoinColumn(name = "bill_id"))
    @Column(name = "product_id")
    private List<Long> productIds;

    // Other fields and methods...

}
