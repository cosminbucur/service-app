package com.fm.repository;

import com.fm.model.CustomerVisit;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CustomerVisitH2Repository implements CustomerVisitRepository {

    private static Map<Long, CustomerVisit> db = new HashMap<>();

    @Override
    public CustomerVisit save(CustomerVisit customerVisit) {
        long nextId = db.size() + 1L;
        customerVisit.setId(nextId);
        db.put(nextId, customerVisit);
        return customerVisit;
    }

    @Override
    public Optional<CustomerVisit> findByLicensePlate(String licensePlate) {
        return db.values().stream()
            .filter(customerVisit -> customerVisit.getCustomer().getLicensePlates().contains(licensePlate))
            .findFirst();
    }
}
