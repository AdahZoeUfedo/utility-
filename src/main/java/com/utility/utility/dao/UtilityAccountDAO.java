package com.utility.utility.dao;

import com.utility.utility.model.Customer;
import com.utility.utility.model.UtilityAccount;

import java.util.Optional;

public interface UtilityAccountDAO {

    UtilityAccount save(UtilityAccount utilityAccount);

    Optional<UtilityAccount> findById(Long id);

    Optional<UtilityAccount> findByCustomer(Customer customer);
}