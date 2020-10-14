package com.fm.repository;

import com.fm.dto.StoragePoint;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class HotelRepositoryInMemoryTest {

    @Test
    public void getStoragePoint() {
        StoragePoint storagePoint1 = new StoragePoint();
        StoragePoint storagePoint2 = new StoragePoint();
        storagePoint1.licensePlate = "B22ABC";
        storagePoint2.licensePlate = "B33DEF";
        HotelRepositoryInMemory hotelRepository = new HotelRepositoryInMemory();
        hotelRepository.setStoragePoints(Arrays.asList(storagePoint1, storagePoint2));

        StoragePoint actual = hotelRepository.findStoragePoint("B22ABC");

        assertThat(actual.licensePlate).isEqualTo("B22ABC");
    }
}