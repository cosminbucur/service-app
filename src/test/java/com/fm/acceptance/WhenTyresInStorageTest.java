package com.fm.acceptance;

import com.fm.dto.request.StoragePointWrite;
import com.fm.service.ICustomerVisitService;
import com.fm.util.TestDtoUtils;
import org.junit.jupiter.api.Test;

import static com.fm.util.TestUtils.createHotelService;
import static org.assertj.core.api.Assertions.assertThat;

class WhenTyresInStorageTest {

    private final ICustomerVisitService hotelService = createHotelService();

    // user story: swap storage points

    @Test
    void shouldSwapStorage() {
        // given
        StoragePointWrite oldStorage = TestDtoUtils.createStoragePointInfo();
        StoragePointWrite newStorage = new StoragePointWrite();

        oldStorage.setMountedTyres(TestDtoUtils.createFourSummerTyresInfo());

        // when
        hotelService.swapStorage(oldStorage.getCode(), newStorage.getCode());

        // then

        // check old storage is empty
        assertThat(oldStorage.getLicensePlate()).isNull();
        assertThat(oldStorage.getMountedTyres()).isEmpty();
        assertThat(oldStorage.getStoredTyres()).isEmpty();
        assertThat(oldStorage.getNumberOfRimCaps()).isZero();

        // check new storage is full
        assertThat(newStorage.getLicensePlate()).isNotEmpty();
        assertThat(newStorage.getMountedTyres().size()).isEqualTo(4);
        assertThat(newStorage.getStoredTyres().size()).isEqualTo(4);
        assertThat(newStorage.getNumberOfRimCaps()).isEqualTo(12);
    }

}
