package com.utility.utility.dao.impl;

import com.utility.utility.dao.UtilityAccountDAO;
import com.utility.utility.model.Customer;
import com.utility.utility.model.UtilityAccount;
import com.utility.utility.repository.UtilityAccountRepository;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UtilityAccountDAOImpl
        implements UtilityAccountDAO {

    private final UtilityAccountRepository
            utilityAccountRepository;

    public UtilityAccountDAOImpl(
            UtilityAccountRepository utilityAccountRepository
    ) {

        this.utilityAccountRepository =
                utilityAccountRepository;
    }

    @Override
    public UtilityAccount save(
            UtilityAccount utilityAccount
    ) {

        return utilityAccountRepository.save(
                utilityAccount
        );
    }

    @Override
    public Optional<UtilityAccount> findById(
            Long id
    ) {

        return utilityAccountRepository.findById(id);
    }

    @Override
    public Optional<UtilityAccount> findByCustomer(
            Customer customer
    ) {

        return utilityAccountRepository
                .findByCustomer(customer);
    }
}