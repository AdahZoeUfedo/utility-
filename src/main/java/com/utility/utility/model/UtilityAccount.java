package com.utility.utility.model;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "utility_accounts")
public class UtilityAccount {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String accountNumber;

    @Column(nullable = false)
    private String utilityType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "utilityAccount",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Bill> bills = new ArrayList<>();

    public UtilityAccount() {
    }

    public UtilityAccount(String accountNumber,
                          String utilityType,
                          Customer customer) {
        this.accountNumber = accountNumber;
        this.utilityType = utilityType;
        this.customer = customer;
    }

    public Long getId() {
        return id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getUtilityType() {
        return utilityType;
    }

    public void setUtilityType(String utilityType) {
        this.utilityType = utilityType;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Bill> getBills() {
        return bills;
    }

    public void addBill(Bill bill) {
        bills.add(bill);
        bill.setUtilityAccount(this);
    }

    public void removeBill(Bill bill) {
        bills.remove(bill);
        bill.setUtilityAccount(null);
    }
}
