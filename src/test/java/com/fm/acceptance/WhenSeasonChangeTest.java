package com.fm.acceptance;

import com.fm.dto.CustomerInfo;
import com.fm.dto.CustomerMapper;
import com.fm.dto.CustomerVisitInfo;
import com.fm.dto.StoragePointInfo;
import com.fm.dto.TyreInfo;
import com.fm.model.Customer;
import com.fm.repository.CustomerH2Repository;
import com.fm.repository.CustomerRepository;
import com.fm.repository.CustomerVisitH2Repository;
import com.fm.repository.CustomerVisitRepository;
import com.fm.repository.HotelH2Repository;
import com.fm.repository.HotelRepository;
import com.fm.service.HotelService;
import com.fm.util.TestUtils;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class WhenSeasonChangeTest {

    private final HotelRepository hotelRepository = new HotelH2Repository();
    private final CustomerRepository customerRepository = new CustomerH2Repository();
    private final CustomerVisitRepository customerVisitRepository = new CustomerVisitH2Repository();
    private final CustomerMapper customerMapper = new CustomerMapper();
    private final HotelService hotelService = new HotelService(hotelRepository, customerRepository, customerMapper, customerVisitRepository);

    // user story 6: store tyres on season change
    @Test
    void shouldStoreTyres() {
        // given
        CustomerVisitInfo customerVisitInfo = TestUtils.createCustomerVisitInfo();
        String licensePlate = customerVisitInfo.getCustomerInfo().getLicensePlate();
        StoragePointInfo storagePointInfo = TestUtils.createStoragePointInfo();

        Customer expectedCustomer = TestUtils.createCustomer();

        // when
        hotelService.storeTyres(storagePointInfo, customerVisitInfo, storagePointInfo.getMountedTyres());

        // check customer
        Customer actualCustomer = customerRepository.findByPhoneNumber(expectedCustomer.getPhoneNumber());
        assertThat(actualCustomer).isEqualTo(expectedCustomer);

        // check tyres in storage point
        assertThat(hotelRepository.findStoragePoint(licensePlate).getMountedTyres().size()).isEqualTo(4);
    }

    // user story 7: unstore tyres during season change
    @Test
    void shouldUnstoreTyres() {
        CustomerVisitInfo customerVisitInfo = TestUtils.createCustomerVisitInfo();
        StoragePointInfo storagePointsWithTyresDetails = new StoragePointInfo();
        storagePointsWithTyresDetails.setMountedTyres(TestUtils.createFourSummerTyres());

        List<TyreInfo> selectedTyreInfos = storagePointsWithTyresDetails.getMountedTyres();

        hotelService.unstoreTyres(storagePointsWithTyresDetails, customerVisitInfo, selectedTyreInfos);

        assertThat(storagePointsWithTyresDetails.getMountedTyres()).isEmpty();
    }

    // TODO user story 8: notify customers after 6 months
    @Test
    void shouldNotifyCustomersEverySixMonths() {
        // given
        StoragePointInfo storagePointInfo1 = new StoragePointInfo();
        storagePointInfo1.setMountedTyres(TestUtils.createFourSummerTyres());
        StoragePointInfo storagePointInfo2 = new StoragePointInfo();
        storagePointInfo2.setMountedTyres(TestUtils.createFourSummerTyres());

        LocalDate sixMonthsAgo = LocalDate.of(2019, 6, 20);
        LocalDate recentDate = LocalDate.of(2020, 1, 10);

        CustomerVisitInfo customerVisit1 = TestUtils.createCustomerVisitInfo();
        CustomerInfo customer1 = customerVisit1.getCustomerInfo();
        customer1.setId(1L);
        customer1.setLicensePlate("B22ABC");

        CustomerVisitInfo customerVisit2 = TestUtils.createCustomerVisitInfo();
        CustomerInfo customer2 = customerVisit2.getCustomerInfo();
        customer2.setId(2L);
        customer2.setLicensePlate("B22DEF");

        List<CustomerVisitInfo> customerVisits = Arrays.asList(customerVisit1, customerVisit2);

        // when
        hotelService.notifyCustomersOnSeasonChange(customerVisits);

        // then
        assertThat(customerVisit2.isSeasonPassed()).isFalse();
    }

}
