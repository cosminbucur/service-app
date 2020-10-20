package com.fm.repository;

import com.fm.model.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerRepositoryInMemory implements CustomerRepository{

    private List<Customer> customers = new ArrayList<>();

    @Override
    public void save(Customer customer) {
        int nextId = customers.size() + 1;
        customer.setId((long) nextId);
        customers.add(customer);
    }

    @Override
    public Customer findByPhoneNumber(String phoneNumber) {
        return customers.stream()
            .filter(customer -> customer.getPhoneNumber().equals(phoneNumber))
            .findFirst()
            .orElseThrow(() -> new RuntimeException("no customer with phone number " + phoneNumber));
    }

    @Override
    public boolean existsById(Long id) {
        Customer foundCustomer = customers.stream()
            .filter(customer -> customer.getId().equals(id))
            .findFirst()
            .orElse(null);
        return foundCustomer != null;
    }
}
