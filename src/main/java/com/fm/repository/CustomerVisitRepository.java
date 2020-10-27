package com.fm.repository;

import com.fm.model.CustomerVisit;

import java.util.Optional;

public interface CustomerVisitRepository {

    void save(CustomerVisit customerVisit);

    Optional<CustomerVisit> findByLicensePlate(String licensePlate);
}