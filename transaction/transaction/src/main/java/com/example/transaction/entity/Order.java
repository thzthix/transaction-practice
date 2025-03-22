package com.example.transaction.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private int productId;
    private int quantity;
    @Setter
    private double totalPrice;
}
