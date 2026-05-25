package com.utility.utility.model;
import com.utility.utility.enums.DisputeStatus;

import jakarta.persistence.*;
import java.time.LocalDate;


@Entity
@Table(name = "disputes")
public class Dispute {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String reason;
    private LocalDate submittedDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bill_id")
    private Bill bill;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agent_id")
    private CallCenterAgent assignedAgent;
    
    @Enumerated(EnumType.STRING)
    private DisputeStatus status = DisputeStatus.OPEN;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public LocalDate getSubmittedDate() {
        return submittedDate;
    }

    public void setSubmittedDate(LocalDate submittedDate) {
        this.submittedDate = submittedDate;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }
    
    public CallCenterAgent getAssignedAgent() {
        return assignedAgent;
    }

    public void setAssignedAgent(CallCenterAgent assignedAgent) {
        this.assignedAgent = assignedAgent;
    }
    
    public DisputeStatus getStatus() {
        return status;
    }

    public void setStatus(DisputeStatus status) {
        this.status = status;
    }
    
    public void markAsResolved() {
        this.status = DisputeStatus.RESOLVED;
    }
    
    public boolean isResolved() {
        return status == DisputeStatus.RESOLVED;
    }
}