package com.fm.repository;

import com.fm.model.Customer;

public interface CustomerRepository {

    void save(Customer customer);

    Customer findCustomerByPhoneNumber(Long phoneNumber);
}
