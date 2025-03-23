package com.example.transaction.handler;

import com.example.transaction.entity.Order;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import com.example.transaction.repository.OrderRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true , level = AccessLevel.PRIVATE)
public class OrderHandler {
    OrderRepository orderRepository;
    @Transactional(propagation = Propagation.REQUIRED)
    public Order saveOrder(Order order){
        return orderRepository.save(order);
    }

}
