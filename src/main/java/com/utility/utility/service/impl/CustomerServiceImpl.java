package com.utility.utility.service.impl;

import com.utility.utility.dao.CustomerDAO;
import com.utility.utility.dao.UtilityAccountDAO;
import com.utility.utility.model.Customer;
import com.utility.utility.model.UtilityAccount;
import com.utility.utility.service.CustomerService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class CustomerServiceImpl
        implements CustomerService {

    private final CustomerDAO customerDAO;
    private final PasswordEncoder passwordEncoder;
    private final UtilityAccountDAO utilityAccountDAO;

    public CustomerServiceImpl(
            CustomerDAO customerDAO,
            PasswordEncoder passwordEncoder,
            UtilityAccountDAO utilityAccountDAO
    ) {

        this.customerDAO = customerDAO;
        this.passwordEncoder = passwordEncoder;
        this.utilityAccountDAO = utilityAccountDAO;
    }

    @Override
    public Customer save(Customer customer) {

        if (customerDAO.findByEmail(customer.getEmail()).isPresent()) {

            throw new RuntimeException(
                    "Email already exists"
            );
        }

        customer.setPassword(
                passwordEncoder.encode(
                        customer.getPassword()
                )
        );

        Customer savedCustomer =
                customerDAO.save(customer);

        UtilityAccount utilityAccount =
                new UtilityAccount();

        utilityAccount.setCustomer(savedCustomer);

        utilityAccount.setAccountNumber(
                "UTIL-" + System.currentTimeMillis()
        );

        utilityAccount.setUtilityType("ELECTRICITY");

        utilityAccountDAO.save(utilityAccount);

        return savedCustomer;
    }

    @Override
    public Optional<Customer> findByEmail(String email) {

        return customerDAO.findByEmail(email);
    }

    @Override
    public Optional<Customer> findById(Long id) {

        return customerDAO.findById(id);
    }
}