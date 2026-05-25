package com.utility.utility.model;

import com.utility.utility.enums.UserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "call_center_agents")
public class CallCenterAgent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    @Email(message = "Invalid email format")
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank(message = "Password is required")
    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole role = UserRole.AGENT;

    @OneToMany(mappedBy = "assignedAgent",
            cascade = CascadeType.ALL)
    private List<Dispute> assignedDisputes = new ArrayList<>();

    public CallCenterAgent() {
    }

    public CallCenterAgent(String name,
                           String email,
                           String password) {

        this.name = name;
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public List<Dispute> getAssignedDisputes() {
        return assignedDisputes;
    }

    public void assignDispute(Dispute dispute) {

        assignedDisputes.add(dispute);

        dispute.setAssignedAgent(this);
    }

    public void removeDispute(Dispute dispute) {

        assignedDisputes.remove(dispute);

        dispute.setAssignedAgent(null);
    }
}