package com.bucur.repository;

import com.bucur.model.StoragePoint;

public interface HotelRepository {

    StoragePoint getStoragePoint(String licensePlate);
}
