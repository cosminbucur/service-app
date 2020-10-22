package com.fm.dto;

import com.fm.model.Customer;

public class CustomerMapper {

    public Customer toEntity(CustomerDetail customerDetail) {
        Customer customer = new Customer();
        customer.setFirstName(customerDetail.getFirstName());
        customer.setLastName(customerDetail.getLastName());
        customer.setCompany(customerDetail.getCompany());
        customer.setEmail(customerDetail.getEmail());
        customer.setPhoneNumber(customerDetail.getPhoneNumber());
        customer.addLicensePlate(customerDetail.getPhoneNumber());
        return customer;
    }
}
