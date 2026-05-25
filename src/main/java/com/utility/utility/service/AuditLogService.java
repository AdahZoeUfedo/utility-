package com.utility.utility.service;

import com.utility.utility.model.AuditLog;

public interface AuditLogService {

    AuditLog saveLog(AuditLog auditLog);
    
}