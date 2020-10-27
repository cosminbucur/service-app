package com.fm.service;

import com.fm.dto.CustomerVisitInfo;
import com.fm.dto.StoragePointInfo;
import com.fm.model.Tyre;

import java.util.List;
import java.util.Map;

public interface IHotelService {

    /**
     * Used to search
     *
     * @param licensePlate
     * @return storage point
     */
    StoragePointInfo findStoragePoint(String licensePlate);

    /**
     * @param customerVisitInfo
     */
    CustomerVisitInfo saveCustomerVisit(CustomerVisitInfo customerVisitInfo);

    /**
     * During a visit, a customer can choose to checkout his tyres. The storage point must be cleared.
     *
     * @param licensePlate license plate associated with storage point
     */
    void checkout(String licensePlate);

    /**
     * Inside the storage, a set of tyres can be moved from one storage point to another.
     *
     * @param oldStorageInfo
     * @param newStorageInfo
     */
    void swapStorage(StoragePointInfo oldStorageInfo, StoragePointInfo newStorageInfo);

    /**
     * Used to find the worn tyre.
     *
     * @return a map of tyres grouped by license plate
     * key = license plate and value = list of tyres
     */
    Map<String, List<Tyre>> findWornTyres();

    void notifyCustomersOnSeasonChange(List<CustomerVisitInfo> customerVisits);
}
