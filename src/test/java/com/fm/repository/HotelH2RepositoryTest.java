package com.fm.repository;

import com.fm.model.StoragePoint;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class HotelH2RepositoryTest {

    @Test
    void shouldFindStoragePoint() {
        // given
        String licensePlate = "B22ABC";
        StoragePoint storagePoint1 = new StoragePoint();
        StoragePoint storagePoint2 = new StoragePoint();
        storagePoint1.setLicensePlate(licensePlate);
        storagePoint2.setLicensePlate("B33DEF");
        StoragePointRepository storagePointRepository = new StoragePointH2Repository();
        storagePointRepository.save(storagePoint1);
        storagePointRepository.save(storagePoint2);

        // when
        StoragePoint actual = storagePointRepository.findStoragePoint(licensePlate).get();

        // then
        assertThat(actual.getLicensePlate()).isEqualTo(licensePlate);
    }
}