package com.utility.utility.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tariffs")
public class Tariff {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double ratePerUnit;
    // Getters and Setters
}
