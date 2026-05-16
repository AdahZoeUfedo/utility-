package com.utility.utility.model;

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

    @ManyToOne
    private Bill bill;

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
}