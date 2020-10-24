package com.fm.acceptance;

import com.fm.dto.StoragePointInfo;
import com.fm.dto.TyreInfo;
import com.fm.model.TyreType;
import com.fm.model.WearLevel;
import com.fm.repository.HotelH2Repository;
import com.fm.repository.HotelRepository;
import com.fm.service.HotelService;
import com.fm.util.TestUtils;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static com.fm.util.TestUtils.createHotelService;
import static org.assertj.core.api.Assertions.assertThat;

class WhenWornTyresInStorageTest {

    // TODO: find another way to avoid this
    private final HotelRepository hotelRepository = new HotelH2Repository();
    private final HotelService hotelService = createHotelService();

    @Test
    void shouldSeeWornTyres() {
        TyreInfo wornTyreInfo1 = TestUtils.createTyre(TyreType.SUMMER, WearLevel.DANGER);
        TyreInfo wornTyreInfo2 = TestUtils.createTyre(TyreType.SUMMER, WearLevel.WARNING);
        TyreInfo newTyreInfo1 = TestUtils.createTyre(TyreType.SUMMER, WearLevel.GOOD);
        TyreInfo wornTyreInfo3 = TestUtils.createTyre(TyreType.SUMMER, WearLevel.DANGER);

        StoragePointInfo storagePointInfoWithWornTyres = new StoragePointInfo();
        storagePointInfoWithWornTyres.setLicensePlate("B22ABC");
        storagePointInfoWithWornTyres.setMountedTyres(Arrays.asList(wornTyreInfo1, wornTyreInfo2, newTyreInfo1, wornTyreInfo3));

        hotelRepository.setStoragePoints(Collections.singletonList(storagePointInfoWithWornTyres));

        Map<String, List<TyreInfo>> wornTyres = hotelService.getWornTyres();

        int expectedResult = Map.of("B22ABC", Arrays.asList(wornTyreInfo1, wornTyreInfo2, wornTyreInfo3)).size();

        assertThat(wornTyres).hasSize(expectedResult);
    }
}
