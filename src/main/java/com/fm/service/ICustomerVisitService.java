package com.fm.service;

import com.fm.dto.request.CustomerVisitWrite;
import com.fm.dto.response.CustomerVisitRead;
import com.fm.dto.response.StoragePointRead;
import com.fm.model.Tyre;

import java.util.List;
import java.util.Map;

public interface ICustomerVisitService {

    /**
     * Used to search
     *
     * @param licensePlate
     * @return storage point
     */
    StoragePointRead findStoragePoint(String licensePlate);

    /**
     * @param customerVisitWrite
     */
    CustomerVisitRead saveCustomerVisit(CustomerVisitWrite customerVisitWrite);

    List<CustomerVisitRead> findAll();

    /**
     * During a visit, a customer can choose to checkout his tyres. The storage point must be cleared.
     *
     * @param licensePlate license plate associated with storage point
     */
    void checkout(String licensePlate);

    /**
     * Inside the storage, a set of tyres can be moved from one storage point to another.
     *
     * @param sourceStorageCode
     * @param targetStorageCode
     */
    void swapStorage(String sourceStorageCode, String targetStorageCode);

    /**
     * Used to find the worn tyre.
     *
     * @return a map of tyres grouped by license plate
     * key = license plate and value = list of tyres
     */
    Map<String, List<Tyre>> findWornTyres();

}
