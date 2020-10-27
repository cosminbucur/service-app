package com.fm.repository;

import com.fm.model.Customer;

public interface CustomerRepository {

    Customer save(Customer customer);

    Customer findByPhoneNumber(String phoneNumber);

    boolean existsById(Long id);
}
