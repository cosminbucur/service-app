package com.bucur.service;

import com.bucur.model.StoragePoint;
import com.bucur.model.Tyre;
import com.bucur.model.TyreType;
import com.bucur.repository.HotelRepository;
import com.bucur.repository.HotelRepositoryInMemory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class HotelServiceTest {

    // TODO: mock the repository instead of using a real object
    private HotelRepository hotelRepository;
    private HotelService hotelService;

    @BeforeEach
    void setUp() {
        hotelRepository = new HotelRepositoryInMemory();
        hotelService = new HotelService(hotelRepository);
    }

    /*
         scenario:
         1 storage point with 1 worn tyre (from license plate 1)
         1 storage point with 1 worn tyre and 1 new tyre (from license plate 2)

         call method
         check I get the correct number of worn tyres (2)
         */
    @Test
    public void given3wornTyresAnd1New_whenCountWornTyres_thenReturn3() {
        // given
        // on given, we instantiate the objects required to create the test scenario
        Tyre wornTyre1 = new Tyre("michelin", TyreType.SUMMER, 2);
        Tyre wornTyre2 = new Tyre("michelin", TyreType.SUMMER, 1);
        Tyre newTyre1 = new Tyre("michelin", TyreType.SUMMER, 4);
        Tyre wornTyre3 = new Tyre("michelin", TyreType.SUMMER, 2);

        StoragePoint storagePoint1 = new StoragePoint();
        storagePoint1.setTyres(Arrays.asList(wornTyre1, wornTyre2));

        StoragePoint storagePoint2 = new StoragePoint();
        storagePoint2.setTyres(Arrays.asList(newTyre1, wornTyre3));

        // when
        // on when, we call the method under test from the responsible object
        int actualResult = hotelService.countWornTyres();

        // because we have 2 worn tyres in first storage point and 1 in the second point
        int expectedResult = 3;

        // then
        // on then, we check that the actual result is equal to the expected result

        // use the import static org.assertj.core.api.Assertions.assertThat; for fluent assertions
        // read more here https://assertj.github.io/doc/#overview-what-is-assertj
        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    public void given3wornTyresAnd1New_whenFindWornTyres_then3WornTyres() {
        Tyre wornTyre1 = new Tyre("michelin", TyreType.SUMMER, 3);
        Tyre wornTyre2 = new Tyre("michelin", TyreType.SUMMER, 2);
        Tyre newTyre1 = new Tyre("michelin", TyreType.SUMMER, 8);
        Tyre wornTyre3 = new Tyre("michelin", TyreType.SUMMER, 2);

        StoragePoint storagePoint1 = new StoragePoint();
        storagePoint1.licensePlate = "B22ABC";
        storagePoint1.setTyres(Arrays.asList(wornTyre1, wornTyre2));

        StoragePoint storagePoint2 = new StoragePoint();
        storagePoint2.licensePlate = "B33DEF";
        storagePoint2.setTyres(Arrays.asList(newTyre1, wornTyre3));

        hotelRepository.setStoragePoints(Arrays.asList(storagePoint1, storagePoint2));

        Map<String, List<Tyre>> actualResult = hotelService.getWornTyres();

        Map<String, List<Tyre>> expectedResult = Map.of(
            "B33DEF", Collections.singletonList(wornTyre3),
            "B22ABC", Arrays.asList(wornTyre1, wornTyre2)
        );

        assertThat(actualResult).isEqualTo(expectedResult);
    }
}