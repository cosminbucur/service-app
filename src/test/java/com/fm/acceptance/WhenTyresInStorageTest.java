package com.fm.acceptance;

import com.fm.dto.StoragePointInfo;
import com.fm.service.HotelService;
import com.fm.util.TestUtils;
import org.junit.jupiter.api.Test;

import static com.fm.util.TestUtils.createHotelService;
import static org.assertj.core.api.Assertions.assertThat;

class WhenTyresInStorageTest {

    private final HotelService hotelService = createHotelService();

    // user story: swap storage points

    @Test
    void shouldSwapStorage() {
        StoragePointInfo oldStorage = new StoragePointInfo();
        StoragePointInfo newStorage = new StoragePointInfo();

        oldStorage.setMountedTyres(TestUtils.createFourSummerTyres());

        hotelService.swapStorage(oldStorage, newStorage);

        assertThat(oldStorage.getMountedTyres()).isEmpty();
        assertThat(newStorage.getMountedTyres().size()).isEqualTo(4);
    }

}
