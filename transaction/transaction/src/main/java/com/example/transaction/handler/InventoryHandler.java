package com.example.transaction.handler;

import com.example.transaction.entity.Product;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import com.example.transaction.repository.InventoryRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class InventoryHandler {
    InventoryRepository inventoryRepository;

    public Product updateProductsDetails(Product product){
        //forcefully throwing exception to simulate the use of transaction
        if(product.getPrice()>5000){
            throw new RuntimeException("DB crashed...");
        }
        return inventoryRepository.save(product);
    }
    public Product getProduct(int id){
        return inventoryRepository.findById(id).orElseThrow(()-> new RuntimeException("Product not found"));
    }
}
