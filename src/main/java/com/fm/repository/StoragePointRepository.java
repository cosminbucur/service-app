package com.fm.repository;

import com.fm.model.StoragePoint;
import com.fm.model.Tyre;

import java.util.List;
import java.util.Map;
import java.util.Optional;

// deposit tyres in hotel by: shelf > row > floor
public interface StoragePointRepository {

    // as a convention we use find() when a method might not have a result
    // and we use get() when a method returns something for sure

    /**
     * Saves or updates a storage point.
     *
     * @param storagePoint
     */
    void save(StoragePoint storagePoint);

    List<StoragePoint> findAll();

    /**
     * Finds a storage point bt a license plate. Each tyre is labeled with the license plate number.
     * All tyres belonging to a license plate are always kept in a single storage point.
     *
     * @param licensePlate the license plate of the vehicle
     * @return the storage point associated to the license plate
     */
    Optional<StoragePoint> findStoragePoint(String licensePlate);

    Optional<StoragePoint> findStoragePoint(Long id);

    /**
     * Returns a map of worn tyres where
     * key - the license plate
     * value - the list of tyres associated to that license plate
     * A tyre is considered to be worn if the depth level is less or equal to 3 (WARNING and DANGER wear level)
     *
     * @return the map with the entries if any
     */
    Map<String, List<Tyre>> findWornTyres();

    boolean isEmpty();
}
