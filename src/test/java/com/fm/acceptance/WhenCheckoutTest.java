package com.fm.acceptance;

import com.fm.dto.CustomerVisitInfo;
import com.fm.dto.ObjectMapper;
import com.fm.dto.StoragePointInfo;
import com.fm.repository.CustomerH2Repository;
import com.fm.repository.CustomerRepository;
import com.fm.repository.CustomerVisitH2Repository;
import com.fm.repository.CustomerVisitRepository;
import com.fm.repository.StoragePointH2Repository;
import com.fm.repository.StoragePointRepository;
import com.fm.service.HotelService;
import com.fm.service.IHotelService;
import com.fm.util.TestDtoUtils;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class WhenCheckoutTest {

    StoragePointRepository storagePointRepository = new StoragePointH2Repository();
    CustomerRepository customerRepository = new CustomerH2Repository();
    CustomerVisitRepository customerVisitRepository = new CustomerVisitH2Repository();
    ObjectMapper objectMapper = new ObjectMapper();
    IHotelService hotelService = new HotelService(customerVisitRepository, customerRepository, storagePointRepository, objectMapper);

    // user story: checkout storage

    @Test
    void shouldCheckoutTyres() {
        // given
        CustomerVisitInfo customerVisitInfo = TestDtoUtils.createCustomerVisitInfo();
        hotelService.storeTyres(customerVisitInfo);

        // when
        hotelService.checkout(customerVisitInfo.getStoragePointInfo().getLicensePlate());

        // then
        StoragePointInfo storagePoint = hotelService.findStoragePoint(customerVisitInfo.getStoragePointInfo().getLicensePlate());

        // check storage point cleared
        assertThat(storagePoint.getNumberOfRimCaps()).isZero();
        assertThat(storagePoint.getMountedTyres()).isEmpty();
        assertThat(storagePoint.getStoredTyres()).isEmpty();
    }
}
