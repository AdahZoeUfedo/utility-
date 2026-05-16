package com.utility.utility.model;

import jakarta.persistence.*;

import java.time.Instant;
import java.time.LocalDate;

@Entity
@Table(name = "bills")
public class Bill {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private Double amount;
    private String status; // PAID, UNPAID, DISPUTED
    private LocalDate dueDate;
    @ManyToOne
    private Customer customer;

    public void setStatus(String paid) {
    }

    public LocalDate getDueDate() {
        return null;
    }
    // Getters and Setters
}
