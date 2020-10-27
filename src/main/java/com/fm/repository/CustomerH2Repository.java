package com.fm.repository;

import com.fm.model.Customer;

import java.util.HashMap;
import java.util.Map;

public class CustomerH2Repository implements CustomerRepository {

    private static Map<Long, Customer> db = new HashMap<>();

    @Override
    public Customer save(Customer customer) {
        long nextId = db.size() + 1L;
        customer.setId(nextId);
        db.put(nextId, customer);
        return db.get(nextId);
    }

    @Override
    public Customer findByPhoneNumber(String phoneNumber) {
        return db.values().stream()
            .filter(customer -> customer.getPhoneNumber().equals(phoneNumber))
            .findFirst()
            .orElseThrow(() -> new RuntimeException("no customer with phone number " + phoneNumber));
    }

    @Override
    public boolean existsById(Long id) {
        Customer customer = db.get(id);
        return customer != null;
    }
}
