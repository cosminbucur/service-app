package com.fm.repository;

import com.fm.model.Customer;

import java.util.Optional;

public interface CustomerRepository {

    Customer save(Customer customer);

    Customer findByPhoneNumber(String phoneNumber);

    Optional<Customer> findById(Long id);

}
