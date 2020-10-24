package com.fm.repository;

import com.fm.dto.StoragePointInfo;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class HotelH2RepositoryTest {

    @Test
    void getStoragePoint() {
        StoragePointInfo storagePointInfo1 = new StoragePointInfo();
        StoragePointInfo storagePointInfo2 = new StoragePointInfo();
        storagePointInfo1.setLicensePlate("B22ABC");
        storagePointInfo2.setLicensePlate("B33DEF");
        HotelH2Repository hotelRepository = new HotelH2Repository();
        hotelRepository.setStoragePoints(Arrays.asList(storagePointInfo1, storagePointInfo2));

        StoragePointInfo actual = hotelRepository.findStoragePoint("B22ABC");

        assertThat(actual.getLicensePlate()).isEqualTo("B22ABC");
    }
}