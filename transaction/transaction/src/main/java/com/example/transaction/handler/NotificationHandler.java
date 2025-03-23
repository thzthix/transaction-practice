package com.example.transaction.handler;

import com.example.transaction.entity.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class NotificationHandler {

    @Transactional(propagation = Propagation.NEVER)
        public void sendOrderConfirmationNotification(Order order){
            //Send an emil notification to the customer
            System.out.println(order.getId() + "Order placed successfully");

        }

}
