package com.fm.acceptance;

import com.fm.dto.CustomerInfo;
import com.fm.dto.CustomerVisitInfo;
import com.fm.dto.ObjectMapper;
import com.fm.dto.StoragePointInfo;
import com.fm.model.Customer;
import com.fm.model.CustomerVisit;
import com.fm.model.StoragePoint;
import com.fm.repository.CustomerH2Repository;
import com.fm.repository.CustomerRepository;
import com.fm.repository.CustomerVisitH2Repository;
import com.fm.repository.CustomerVisitRepository;
import com.fm.repository.StoragePointH2Repository;
import com.fm.repository.StoragePointRepository;
import com.fm.service.HotelService;
import com.fm.service.IHotelService;
import com.fm.util.TestDtoUtils;
import com.fm.util.TestUtils;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class WhenSeasonChangeTest {

    StoragePointRepository storagePointRepository = new StoragePointH2Repository();
    CustomerRepository customerRepository = new CustomerH2Repository();
    CustomerVisitRepository customerVisitRepository = new CustomerVisitH2Repository();
    ObjectMapper objectMapper = new ObjectMapper();
    IHotelService hotelService = new HotelService(customerVisitRepository, customerRepository, storagePointRepository, objectMapper);

    // user story: store tyres on season change

    @Test
    void shouldStoreTyresForExistingCustomer() {
        // given
        CustomerVisitInfo customerVisitInfo = TestDtoUtils.createCustomerVisitInfo();
        String licensePlate = customerVisitInfo.getCustomerInfo().getLicensePlate();

        Customer expectedCustomer = TestUtils.createCustomer();
        CustomerVisit expectedCustomerVisit = TestUtils.createCustomerVisit();

        // when
        hotelService.saveCustomerVisit(customerVisitInfo);

        // check customer
        Customer actualCustomer = customerRepository.findByPhoneNumber(expectedCustomer.getPhoneNumber());
        assertThat(actualCustomer).isEqualTo(expectedCustomer);

        // check customer visit
        CustomerVisit actualCustomerVisit = customerVisitRepository.findByLicensePlate(licensePlate)
            .orElseThrow(() -> new RuntimeException());
        assertThat(actualCustomerVisit).isEqualTo(expectedCustomerVisit);

        // check tyres in storage point
        StoragePoint storagePoint = storagePointRepository.findStoragePoint(licensePlate).get();
        assertThat(storagePoint.getMountedTyres().size()).isEqualTo(4);
        assertThat(storagePoint.getMountedTyres().size()).isEqualTo(4);
    }

    // TODO user story: notify customers after 6 months
    @Test
    void shouldNotifyCustomersEverySixMonths() {
        // given
        StoragePointInfo storagePointInfo1 = new StoragePointInfo();
        storagePointInfo1.setMountedTyres(TestDtoUtils.createFourSummerTyresInfo());
        StoragePointInfo storagePointInfo2 = new StoragePointInfo();
        storagePointInfo2.setMountedTyres(TestDtoUtils.createFourSummerTyresInfo());

        LocalDate sixMonthsAgo = LocalDate.of(2019, 6, 20);
        LocalDate recentDate = LocalDate.of(2020, 1, 10);

        CustomerVisitInfo customerVisit1 = TestDtoUtils.createCustomerVisitInfo();
        CustomerInfo customer1 = customerVisit1.getCustomerInfo();
        customer1.setId(1L);
        customer1.setLicensePlate("B22ABC");

        CustomerVisitInfo customerVisit2 = TestDtoUtils.createCustomerVisitInfo();
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
