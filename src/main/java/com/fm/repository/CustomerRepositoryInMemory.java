package com.fm.repository;

import com.fm.model.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerRepositoryInMemory implements CustomerRepository{

    private List<Customer> customers = new ArrayList<>();

    @Override
    public void save(Customer customer) {
        customers.add(customer);
    }

    @Override
    public Customer findByPhoneNumber(String phoneNumber) {
        return customers.stream()
                .filter(customer -> customer.getPhoneNumber().equals(phoneNumber))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("no customer with phone number " + phoneNumber));
    }
}
