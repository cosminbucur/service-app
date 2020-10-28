package com.fm.dto.mapper;

import com.fm.dto.request.CustomerWrite;
import com.fm.dto.response.CustomerRead;
import com.fm.model.Customer;

public class CustomerMapper {

    private CustomerMapper() {
    }

    public static Customer toEntity(CustomerWrite customerWrite) {
        Customer customer = new Customer();
        customer.setFirstName(customerWrite.getFirstName());
        customer.setLastName(customerWrite.getLastName());
        customer.setCompany(customerWrite.getCompany());
        customer.setEmail(customerWrite.getEmail());
        customer.setPhoneNumber(customerWrite.getPhoneNumber());
        return customer;
    }

    public static CustomerRead toDto(Customer entity) {
        CustomerRead dto = new CustomerRead();
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setCompany(entity.getCompany());
        dto.setEmail(entity.getEmail());
        dto.setPhoneNumber(entity.getPhoneNumber());
        return dto;
    }
}
