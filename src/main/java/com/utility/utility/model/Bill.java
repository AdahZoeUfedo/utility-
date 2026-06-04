package com.utility.utility.model;
import com.utility.utility.enums.BillStatus;
import com.utility.utility.model.Payment;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
// The bills table
@Entity
@Table(name = "bills")
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull(message = "Amount is required")
    @Positive(message = "Amount must be positive")
    private Double amount;
    
    @Enumerated(EnumType.STRING)
    private BillStatus status;
    
    @NotNull(message = "Due date is required")
    private LocalDate dueDate;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "utility_account_id")
    private UtilityAccount utilityAccount;
    
    @OneToMany(
            mappedBy = "bill",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @OrderBy("paymentDate DESC")
    private List<Payment> payments = new ArrayList<>();
    
    public List<Payment> getPayments() {
        return payments;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public BillStatus getStatus() {
        return status;
    }

    public void setStatus(BillStatus status) {
        this.status = status;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public UtilityAccount getUtilityAccount() {
        return utilityAccount;
    }

    public void setUtilityAccount(UtilityAccount utilityAccount) {
        this.utilityAccount = utilityAccount;
    }
    
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    
    public void addPayment(Payment payment) {
        payments.add(payment);
        payment.setBill(this);
    }

    public void removePayment(Payment payment) {
        payments.remove(payment);
        payment.setBill(null);
    }
    public boolean isPaid() {
        return status == BillStatus.PAID;
    }
    public void markAsPaid() {
        this.status = BillStatus.PAID;
    }
}