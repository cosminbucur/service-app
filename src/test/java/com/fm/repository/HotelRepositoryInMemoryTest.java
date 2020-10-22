package com.fm.repository;

import com.fm.dto.StoragePointDetail;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class HotelRepositoryInMemoryTest {

    @Test
    void getStoragePoint() {
        StoragePointDetail storagePointDetail1 = new StoragePointDetail();
        StoragePointDetail storagePointDetail2 = new StoragePointDetail();
        storagePointDetail1.setLicensePlate("B22ABC");
        storagePointDetail2.setLicensePlate("B33DEF");
        HotelRepositoryInMemory hotelRepository = new HotelRepositoryInMemory();
        hotelRepository.setStoragePoints(Arrays.asList(storagePointDetail1, storagePointDetail2));

        StoragePointDetail actual = hotelRepository.findStoragePoint("B22ABC");

        assertThat(actual.getLicensePlate()).isEqualTo("B22ABC");
    }
}