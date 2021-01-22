package com.fm.acceptance;

import com.fm.dto.request.StoragePointWrite;
import com.fm.service.ICustomerVisitService;
import com.fm.util.TestDtoUtils;
import org.junit.jupiter.api.Test;

import static com.fm.util.TestEntityUtils.createCustomerVisitService;
import static org.assertj.core.api.Assertions.assertThat;

class WhenTyresInStorageTest {

    private final ICustomerVisitService customerVisitService = createCustomerVisitService();

    // user story: swap storage points

    @Test
    void shouldSwapStorage() {
        // given
        StoragePointWrite oldStorage = TestDtoUtils.createStoragePointWrite();
        StoragePointWrite newStorage = new StoragePointWrite();

        oldStorage.setMountedTyres(TestDtoUtils.createFourSummerTyresWrite());

        // when
        customerVisitService.swapStorage(oldStorage.getCode(), newStorage.getCode());

        // then

        // check old storage is empty
        assertThat(oldStorage.getLicensePlate()).isNull();
        assertThat(oldStorage.getMountedTyres()).isEmpty();
        assertThat(oldStorage.getStoredTyres()).isEmpty();
        assertThat(oldStorage.getRimCapsCount()).isZero();

        // check new storage is full
        assertThat(newStorage.getLicensePlate()).isNotEmpty();
        assertThat(newStorage.getMountedTyres().size()).isEqualTo(4);
        assertThat(newStorage.getStoredTyres().size()).isEqualTo(4);
        assertThat(newStorage.getRimCapsCount()).isEqualTo(12);
    }

}
