package com.fm.repository;

import com.fm.model.CustomerVisit;

import java.util.List;
import java.util.Optional;

public interface CustomerVisitRepository {

    CustomerVisit save(CustomerVisit customerVisit);

    List<CustomerVisit> findAll();

    Optional<CustomerVisit> findByLicensePlate(String licensePlate);

    Optional<CustomerVisit> findById(Long id);
}
