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
public class AuditLogHandler {
    AuditLogRepository auditLogRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    //Log audit details (runs in an independent transaction)
    public void logAuditDetails(Order order, String action){
        AuditLog auditLog = new AuditLog();
        auditLog.setOrderId(Long.valueOf(order.getId()));
        auditLog.setAction(action);
        auditLog.setTimeStamp(LocalDateTime.now());

        //save the audit log
        auditLogRepository.save(auditLog);

    }
}
