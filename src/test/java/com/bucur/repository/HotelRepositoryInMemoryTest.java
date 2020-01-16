package com.bucur.repository;

import com.bucur.model.StoragePoint;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class HotelRepositoryInMemoryTest {
    @Test
    public void getStoragePoint() {
        // given 2 storage points
        StoragePoint storagePoint1 = new StoragePoint();
        StoragePoint storagePoint2 = new StoragePoint();

        storagePoint1.licensePlate = "A";
        storagePoint2.licensePlate = "B";

        HotelRepositoryInMemory hotelRepository = new HotelRepositoryInMemory();

        hotelRepository.setStoragePoints(Arrays.asList(storagePoint1, storagePoint2));

        // when
        StoragePoint actual = hotelRepository.getStoragePoint("A");

        // then
        assertThat(actual.licensePlate).isEqualTo("A");
    }
}