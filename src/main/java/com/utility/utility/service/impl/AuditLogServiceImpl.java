package com.utility.utility.service.impl;

import com.utility.utility.model.AuditLog;
import com.utility.utility.repository.AuditLogRepository;
import com.utility.utility.service.AuditLogService;
import org.springframework.stereotype.Service;

@Service
public class AuditLogServiceImpl implements AuditLogService {

    private final AuditLogRepository auditLogRepository;

    public AuditLogServiceImpl(AuditLogRepository auditLogRepository) {
        this.auditLogRepository = auditLogRepository;
    }

    @Override
    public AuditLog saveLog(AuditLog auditLog) {
        return auditLogRepository.save(auditLog);
    }
}