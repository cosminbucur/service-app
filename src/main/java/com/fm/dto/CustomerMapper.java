package com.fm.dto;

import com.fm.model.Customer;

public class CustomerMapper {

    public Customer toEntity(CustomerDetails customerDetails) {
        Customer customer = new Customer();
        customer.setFirstName(customerDetails.getFirstName());
        customer.setLastName(customerDetails.getLastName());
        customer.setCompany(customerDetails.getCompany());
        customer.setEmail(customerDetails.getEmail());
        customer.setPhoneNumber(customerDetails.getPhoneNumber());
        customer.addLicensePlate(customerDetails.getPhoneNumber());
        return customer;
    }
}
