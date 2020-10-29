package com.fm.service;

import com.fm.dto.mapper.CustomerVisitMapper;
import com.fm.model.Season;
import com.fm.model.StoragePoint;
import com.fm.model.Tyre;
import com.fm.repository.CustomerRepository;
import com.fm.repository.CustomerVisitRepository;
import com.fm.repository.StoragePointH2Repository;
import com.fm.repository.StoragePointRepository;
import com.fm.util.TestEntityUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class CustomerVisitServiceTest {

    @Mock
    StoragePointRepository storagePointRepository;
    @Mock
    CustomerRepository customerRepository;
    @Mock
    CustomerVisitRepository customerVisitRepository;
    @Mock
    CustomerVisitMapper customerVisitMapper;

    @InjectMocks
    ICustomerVisitService hotelService;

    @Test
    void given3wornTyresAnd1New_whenFindWornTyres_then3WornTyres() {
        // given
        Tyre wornTyre1 = TestEntityUtils.createTyre(Season.SUMMER, 2);
        Tyre wornTyre2 = TestEntityUtils.createTyre(Season.SUMMER, 3);
        Tyre newTyre1 = TestEntityUtils.createTyre(Season.SUMMER, 8);
        Tyre wornTyre3 = TestEntityUtils.createTyre(Season.SUMMER, 2);

        StoragePoint storagePoint1 = new StoragePoint();
        storagePoint1.setLicensePlate("B22ABC");
        storagePoint1.setMountedTyres(Arrays.asList(wornTyre1, wornTyre2));

        StoragePoint storagePoint2 = new StoragePoint();
        storagePoint2.setLicensePlate("B33DEF");
        storagePoint2.setMountedTyres(Arrays.asList(newTyre1, wornTyre3));

        StoragePointRepository storagePointRepository = new StoragePointH2Repository();
        storagePointRepository.save(storagePoint1);
        storagePointRepository.save(storagePoint2);

        Map<String, List<Tyre>> expectedResult = Map.of(
            "B33DEF", Collections.singletonList(wornTyre3),
            "B22ABC", Arrays.asList(wornTyre1, wornTyre2)
        );

        // when
        Map<String, List<Tyre>> actualResult = hotelService.findWornTyres();

        // then
        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    void given2StoragePoints_whenFindStoragePoint_then1StoragePoint() {

    }
}