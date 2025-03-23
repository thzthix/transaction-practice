package com.example.transaction.handler;

import com.example.transaction.entity.Product;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductRecommendationHandler {

    //fetch product recommendations (NOT_SUPPORTED)
    @Transactional (propagation = Propagation.NOT_SUPPORTED)
    public List<Product> getRecommendations(){
        //Simulate hardcoded product recommendations
        List<Product> recommendations = new ArrayList<>();

        recommendations.add(new Product(101,"Wireless Headphones", 99.99,30));
        recommendations.add(new Product(102,"Smartphone case", 49.99,20));
        System.out.println("Recommendations fetched for customer ID: ");
        return  recommendations;
    }
}
