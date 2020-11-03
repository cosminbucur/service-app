package com.fm.service;

import com.fm.dto.request.CustomerVisitWrite;
import com.fm.dto.response.CustomerVisitRead;
import com.fm.dto.response.StoragePointRead;
import com.fm.model.Tyre;

import java.util.List;
import java.util.Map;

public interface ICustomerVisitService {

    /**
     * Saves customer visit with customer data and storage data.
     *
     * @param customerVisitWrite request object from front end
     */
    CustomerVisitRead saveCustomerVisit(CustomerVisitWrite customerVisitWrite);

    /**
     * Finds all customer visits.
     *
     * @return list of visits
     */
    List<CustomerVisitRead> findAll();

    /**
     * Finds a customer visit by id.
     *
     * @param id of the customer visit
     * @return response object with customer and tyre data
     */
    CustomerVisitRead findCustomerVisit(Long id);

    /**
     * Used to search a storage point by car license plate.
     *
     * @param licensePlate car license plate
     * @return storage point with the stored and mounted tyres
     */
    StoragePointRead findStoragePoint(String licensePlate);

    /**
     * Used to find the worn tyre.
     *
     * @return a map of tyres grouped by license plate where key = license plate and value = list of tyres
     */
    Map<String, List<Tyre>> findWornTyres();

    /**
     * Inside the storage, a set of tyres can be moved from one storage point to another.
     *
     * @param sourceStorageCode from storage
     * @param targetStorageCode to storage
     */
    void swapStorage(String sourceStorageCode, String targetStorageCode);

    /**
     * During a visit, a customer can choose to checkout his tyres. The storage point must be cleared.
     *
     * @param licensePlate license plate associated with storage point
     */
    void checkout(String licensePlate);

}
