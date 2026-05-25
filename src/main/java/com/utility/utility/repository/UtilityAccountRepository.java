package com.utility.utility.repository;

import com.utility.utility.model.Customer;
import com.utility.utility.model.UtilityAccount;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UtilityAccountRepository
        extends JpaRepository<UtilityAccount, Long> {

    Optional<UtilityAccount> findByCustomer(
            Customer customer
    );
}