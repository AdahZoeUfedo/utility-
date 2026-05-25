package com.utility.utility.model;
import java.util.ArrayList;
import com.utility.utility.enums.UserRole;


import jakarta.validation.constraints.Size;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.List;
import java.util.Collection;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Entity
@Table(
	    name = "customers",
	    indexes = {
	        @Index(name = "idx_customer_email", columnList = "email")
	    }
	)
public class Customer implements UserDetails {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Name is required")
    private String name;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;
    
    @Enumerated(EnumType.STRING)
    private UserRole role;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
    
    @OneToMany(mappedBy = "customer",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<UtilityAccount> utilityAccounts = new ArrayList<>();
    
    public List<UtilityAccount> getUtilityAccounts() {
        return utilityAccounts;
    }

    public void addUtilityAccount(UtilityAccount utilityAccount) {
        utilityAccounts.add(utilityAccount);
        utilityAccount.setCustomer(this);
    }

    public void removeUtilityAccount(UtilityAccount utilityAccount) {
        utilityAccounts.remove(utilityAccount);
        utilityAccount.setCustomer(null);
    }   
    
    @OneToMany(mappedBy = "customer",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Notification> notifications = new ArrayList<>();
    
    public List<Notification> getNotifications() {
        return notifications;
    }

    public void addNotification(Notification notification) {
        notifications.add(notification);
        notification.setCustomer(this);
    }

    public void removeNotification(Notification notification) {
        notifications.remove(notification);
        notification.setCustomer(null);
    }
    
    public long getUnreadNotificationCount() {
        return notifications.stream()
                .filter(notification -> !notification.isViewed())
                .count();
    }
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return List.of(
                new SimpleGrantedAuthority(
                        "ROLE_" + role.name()
                )
        );
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}