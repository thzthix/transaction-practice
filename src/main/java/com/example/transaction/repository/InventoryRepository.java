package com.example.transaction.repository;

import com.example.transaction.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Product, Integer> {
}
