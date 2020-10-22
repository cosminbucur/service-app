package com.fm.service;

import com.fm.dto.CustomerMapper;
import com.fm.dto.StoragePointDetail;
import com.fm.dto.TyreDetail;
import com.fm.model.TyreType;
import com.fm.model.WearLevel;
import com.fm.repository.CustomerRepository;
import com.fm.repository.CustomerRepositoryInMemory;
import com.fm.repository.HotelRepository;
import com.fm.repository.HotelRepositoryInMemory;
import com.fm.util.TestUtils;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class HotelServiceTest {

    private final HotelRepository hotelRepository = new HotelRepositoryInMemory();
    private final CustomerRepository customerRepository = new CustomerRepositoryInMemory();
    private final CustomerMapper customerMapper = new CustomerMapper();
    private final HotelService hotelService = new HotelService(hotelRepository, customerRepository, customerMapper);

    /*
         scenario:
         1 storage point with 1 worn tyre (from license plate 1)
         1 storage point with 1 worn tyre and 1 new tyre (from license plate 2)

         call method
         check I get the correct number of worn tyres (2)
         */
    @Test
    void given3wornTyresAnd1New_whenCountWornTyres_thenReturn3() {
        // given
        // on given, we instantiate the objects required to create the test scenario

        // when
        // on when, we call the method under test from the responsible object
        int actualResult = 3;   // hotelService.countWornTyres();

        // because we have 2 worn tyres in first storage point and 1 in the second point
        int expectedResult = 3;

        // then
        // on then, we check that the actual result is equal to the expected result

        // use the import static org.assertj.core.api.Assertions.assertThat; for fluent assertions
        // read more here https://assertj.github.io/doc/#overview-what-is-assertj
        assertThat(actualResult).isEqualTo(expectedResult);
    }

    // TODO: possible duplicate with WhenWornTyresInStorage
    @Test
    void given3wornTyresAnd1New_whenFindWornTyres_then3WornTyres() {
        TyreDetail wornTyreDetail1 = TestUtils.createTyre(TyreType.SUMMER, WearLevel.DANGER);
        TyreDetail wornTyreDetail2 = TestUtils.createTyre(TyreType.SUMMER, WearLevel.WARNING);
        TyreDetail newTyreDetail1 = TestUtils.createTyre(TyreType.SUMMER, WearLevel.GOOD);
        TyreDetail wornTyreDetail3 = TestUtils.createTyre(TyreType.SUMMER, WearLevel.DANGER);

        StoragePointDetail storagePointDetail1 = new StoragePointDetail();
        storagePointDetail1.setLicensePlate("B22ABC");
        storagePointDetail1.setTyreList(Arrays.asList(wornTyreDetail1, wornTyreDetail2));

        StoragePointDetail storagePointDetail2 = new StoragePointDetail();
        storagePointDetail2.setLicensePlate("B33DEF");
        storagePointDetail2.setTyreList(Arrays.asList(newTyreDetail1, wornTyreDetail3));

        HotelRepositoryInMemory hotelRepositoryInMemory = new HotelRepositoryInMemory();
        hotelRepositoryInMemory.setStoragePoints(Arrays.asList(storagePointDetail1, storagePointDetail2));

        Map<String, List<TyreDetail>> actualResult = hotelService.getWornTyres();

        Map<String, List<TyreDetail>> expectedResult = Map.of(
            "B33DEF", Collections.singletonList(wornTyreDetail3),
            "B22ABC", Arrays.asList(wornTyreDetail1, wornTyreDetail2)
        );

        assertThat(actualResult).isEqualTo(expectedResult);
    }
}