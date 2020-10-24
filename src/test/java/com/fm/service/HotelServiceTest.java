package com.fm.service;

import com.fm.dto.StoragePointInfo;
import com.fm.dto.TyreInfo;
import com.fm.model.TyreType;
import com.fm.model.WearLevel;
import com.fm.repository.HotelH2Repository;
import com.fm.util.TestUtils;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static com.fm.util.TestUtils.createHotelService;
import static org.assertj.core.api.Assertions.assertThat;

class HotelServiceTest {

    private final HotelService hotelService = createHotelService();

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
        TyreInfo wornTyreInfo1 = TestUtils.createTyre(TyreType.SUMMER, WearLevel.DANGER);
        TyreInfo wornTyreInfo2 = TestUtils.createTyre(TyreType.SUMMER, WearLevel.WARNING);
        TyreInfo newTyreInfo1 = TestUtils.createTyre(TyreType.SUMMER, WearLevel.GOOD);
        TyreInfo wornTyreInfo3 = TestUtils.createTyre(TyreType.SUMMER, WearLevel.DANGER);

        StoragePointInfo storagePointInfo1 = new StoragePointInfo();
        storagePointInfo1.setLicensePlate("B22ABC");
        storagePointInfo1.setMountedTyres(Arrays.asList(wornTyreInfo1, wornTyreInfo2));

        StoragePointInfo storagePointInfo2 = new StoragePointInfo();
        storagePointInfo2.setLicensePlate("B33DEF");
        storagePointInfo2.setMountedTyres(Arrays.asList(newTyreInfo1, wornTyreInfo3));

        HotelH2Repository hotelH2Repository = new HotelH2Repository();
        hotelH2Repository.setStoragePoints(Arrays.asList(storagePointInfo1, storagePointInfo2));

        Map<String, List<TyreInfo>> actualResult = hotelService.getWornTyres();

        Map<String, List<TyreInfo>> expectedResult = Map.of(
            "B33DEF", Collections.singletonList(wornTyreInfo3),
            "B22ABC", Arrays.asList(wornTyreInfo1, wornTyreInfo2)
        );

        assertThat(actualResult).isEqualTo(expectedResult);
    }
}