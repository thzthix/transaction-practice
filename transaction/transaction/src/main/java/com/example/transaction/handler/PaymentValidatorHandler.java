package com.example.transaction.handler;

import com.example.transaction.entity.AuditLog;
import com.example.transaction.entity.Order;
import com.example.transaction.repository.AuditLogRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PaymentValidatorHandler {
    AuditLogRepository auditLogRepository;

    @Transactional(propagation = Propagation.MANDATORY)
    public void validatePayment(Order order){
        //Assume payment processing happens here
        boolean paymentSuccessful = false;

        //If payment is unsuccessful, we log the payment fail
        if(!paymentSuccessful){
            AuditLog paymentFailureLog = new AuditLog();
            paymentFailureLog.setOrderId(Long.valueOf(order.getId()));
            paymentFailureLog.setAction("Payment failed for order");
            paymentFailureLog.setTimeStamp(LocalDateTime.now());

            //Save the payment failure log
            auditLogRepository.save(paymentFailureLog);

        }

    }

}
