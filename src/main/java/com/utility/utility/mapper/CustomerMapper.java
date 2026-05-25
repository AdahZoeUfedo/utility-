package com.utility.utility.mapper;
import com.utility.utility.enums.UserRole;
import com.utility.utility.dto.request.RegisterCustomerRequestDTO;
import com.utility.utility.dto.response.CustomerResponseDTO;
import com.utility.utility.model.Customer;

public class CustomerMapper {

    private CustomerMapper() {
    }

    public static Customer toEntity(RegisterCustomerRequestDTO dto) {

        Customer customer = new Customer();

        customer.setName(dto.name());
        customer.setEmail(dto.email());
        customer.setPassword(dto.password());
        customer.setRole(UserRole.CUSTOMER);

        return customer;
    }

    public static CustomerResponseDTO toDTO(Customer customer) {

        return new CustomerResponseDTO(
                customer.getId(),
                customer.getName(),
                customer.getEmail(),
                customer.getRole()
        );
    }
}