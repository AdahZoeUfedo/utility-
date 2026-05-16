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

    public void setBill(Bill bill) {
    }

    public void setReason(String reason) {
    }

    public void setSubmittedDate(LocalDate now) {
    }
    // Getters and Setters
}
