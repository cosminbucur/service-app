package com.fm.repository;

import com.fm.model.Customer;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
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
    public List<Customer> findAll() {
        return new ArrayList<>(db.values());
    }

    @Override
    public Customer findByPhoneNumber(String phoneNumber) {
        return db.values().stream()
            .filter(customer -> customer.getPhoneNumber().equals(phoneNumber))
            .findFirst()
            .orElseThrow(() -> new RuntimeException("no customer with phone number " + phoneNumber));
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return Optional.of(db.get(id));
    }
}
