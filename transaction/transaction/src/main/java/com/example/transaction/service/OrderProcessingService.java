package com.example.transaction.service;

import com.example.transaction.entity.Order;
import com.example.transaction.entity.Product;
import com.example.transaction.handler.*;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderProcessingService {
    OrderHandler orderHandler;
    InventoryHandler inventoryHandler;
    AuditLogHandler auditLogHandler;
    PaymentValidatorHandler paymentValidatorHandler;
    NotificationHandler notificationHandler;
    ProductRecommendationHandler productRecommendationHandler;

    //NOT_SUPPORTED: Executes the method non-transactionally, suspending any active transaction
    @Transactional(propagation = Propagation.REQUIRED)
    public Order placeAnOrder(Order order) {
        //get product inventory
        Product product = inventoryHandler.getProduct(order.getProductId());
        //validate stock availability(<5)
        validateStockAvailability(order, product);
        //update total price in order entity
        order.setTotalPrice(product.getPrice() * order.getQuantity());

        Order saveOrder = null;

        try {
            //save order
            saveOrder = orderHandler.saveOrder(order);
            //update stock in inventory
            updateInventoryStock(order,product);
            auditLogHandler.logAuditDetails(order, "order placement succeeded");

        } catch (Exception e) {
            auditLogHandler.logAuditDetails(order, "order placement failed");
        }

        productRecommendationHandler.getRecommendations();

        return saveOrder;

    }
//    //Call this method after placeAnOrder is successfully completed
//    public void processOrder(Order order){
//        //Step 1: Place the order
//        Order saveOrder = placeAnOrder(order);
//
//        //Step 2: Send Notification (No-transactional)
//        notificationHandler.sendOrderConfirmationNotification(order);
//    }

    private static void validateStockAvailability(Order order, Product product) {
        if (order.getQuantity() > product.getStockQuantity()) {
            throw new RuntimeException("Insufficient stock !");
        }
    }

    private void updateInventoryStock(Order order, Product product) {
        int availableStock = product.getStockQuantity() - order.getQuantity();
        product.setStockQuantity(availableStock);
        inventoryHandler.updateProductsDetails(product);
    }

}
