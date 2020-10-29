package com.fm.acceptance;

import com.fm.dto.request.CustomerVisitWrite;
import com.fm.dto.request.CustomerWrite;
import com.fm.dto.request.StoragePointWrite;
import com.fm.model.Customer;
import com.fm.model.CustomerVisit;
import com.fm.model.StoragePoint;
import com.fm.repository.CustomerH2Repository;
import com.fm.repository.CustomerRepository;
import com.fm.repository.CustomerVisitH2Repository;
import com.fm.repository.CustomerVisitRepository;
import com.fm.repository.StoragePointH2Repository;
import com.fm.repository.StoragePointRepository;
import com.fm.repository.TyreH2Repository;
import com.fm.repository.TyreRepository;
import com.fm.service.CustomerVisitService;
import com.fm.service.ICustomerVisitService;
import com.fm.service.NotificationService;
import com.fm.util.TestDtoUtils;
import com.fm.util.TestEntityUtils;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class WhenSeasonChangeTest {

    StoragePointRepository storagePointRepository = new StoragePointH2Repository();
    CustomerRepository customerRepository = new CustomerH2Repository();
    CustomerVisitRepository customerVisitRepository = new CustomerVisitH2Repository();
    TyreRepository tyreRepository = new TyreH2Repository();
    ICustomerVisitService customerVisitService = new CustomerVisitService(customerVisitRepository, customerRepository, storagePointRepository, tyreRepository);
    NotificationService notificationService = new NotificationService();

    // user story: store tyres on season change

    @Test
    void shouldStoreTyresForExistingCustomer() {
        // given
        CustomerVisitWrite customerVisitWrite = TestDtoUtils.createCustomerVisitWrite();
        String licensePlate = customerVisitWrite.getStoragePointInfo().getLicensePlate();

        Customer expectedCustomer = TestEntityUtils.createCustomer();
        CustomerVisit expectedCustomerVisit = TestEntityUtils.createCustomerVisit();

        // when
        customerVisitService.saveCustomerVisit(customerVisitWrite);

        // check customer
        Customer actualCustomer = customerRepository.findByPhoneNumber(expectedCustomer.getPhoneNumber());
        assertThat(actualCustomer).isEqualTo(expectedCustomer);

        // check customer visit
        CustomerVisit actualCustomerVisit = customerVisitRepository.findByLicensePlate(licensePlate).get();
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
        StoragePointWrite storagePointWrite1 = new StoragePointWrite();
        storagePointWrite1.setMountedTyres(TestDtoUtils.createFourSummerTyresWrite());
        StoragePointWrite storagePointWrite2 = new StoragePointWrite();
        storagePointWrite2.setMountedTyres(TestDtoUtils.createFourSummerTyresWrite());

        LocalDate sixMonthsAgo = LocalDate.of(2019, 6, 20);
        LocalDate recentDate = LocalDate.of(2020, 1, 10);

        CustomerVisitWrite customerVisit1 = TestDtoUtils.createCustomerVisitWrite();
        CustomerWrite customer1 = customerVisit1.getCustomerInfo();

        CustomerVisitWrite customerVisit2 = TestDtoUtils.createCustomerVisitWrite();
        CustomerWrite customer2 = customerVisit2.getCustomerInfo();

        List<CustomerVisitWrite> customerVisits = Arrays.asList(customerVisit1, customerVisit2);

        // when
        notificationService.notifyCustomersOnSeasonChange(customerVisits);

        // then
        assertThat(customerVisit2.isSeasonPassed()).isFalse();
    }

}
