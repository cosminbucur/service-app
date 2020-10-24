package com.fm.dto;

import com.fm.model.Customer;

public class CustomerMapper {

    public Customer toEntity(CustomerInfo customerInfo) {
        Customer customer = new Customer();
        customer.setFirstName(customerInfo.getFirstName());
        customer.setLastName(customerInfo.getLastName());
        customer.setCompany(customerInfo.getCompany());
        customer.setEmail(customerInfo.getEmail());
        customer.setPhoneNumber(customerInfo.getPhoneNumber());
        customer.addLicensePlate(customerInfo.getPhoneNumber());
        return customer;
    }
}
