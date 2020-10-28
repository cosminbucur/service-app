package com.fm.acceptance;

import com.fm.dto.request.CustomerVisitWrite;
import com.fm.dto.response.CustomerVisitRead;
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
import com.fm.util.TestDtoUtils;
import com.fm.util.TestUtils;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class WhenNewCustomerTest {

    StoragePointRepository storagePointRepository = new StoragePointH2Repository();
    CustomerRepository customerRepository = new CustomerH2Repository();
    CustomerVisitRepository customerVisitRepository = new CustomerVisitH2Repository();
    TyreRepository tyreRepository = new TyreH2Repository();
    ICustomerVisitService hotelService = new CustomerVisitService(customerVisitRepository, customerRepository, storagePointRepository, tyreRepository);

    // user story: first time mount tyres

    @Test
    void shouldSaveCustomerVisit() {
        // given
        CustomerVisitWrite customerVisitWrite = TestDtoUtils.createCustomerVisitInfo();
        String licensePlate = customerVisitWrite.getStoragePointInfo().getLicensePlate();

        Customer expectedCustomer = TestUtils.createCustomer();
        CustomerVisit expectedCustomerVisit = TestUtils.createCustomerVisit();

        // when
        CustomerVisitRead customerVisitRead = hotelService.saveCustomerVisit(customerVisitWrite);

        // then

        // TODO: check that ids are set, check all fields except id
        assertThat(customerVisitRead.getCustomerRead().getEmail()).isEqualTo(customerVisitWrite.getCustomerInfo().getEmail());

        // check customer
        Customer actualCustomer = customerRepository.findByPhoneNumber(expectedCustomer.getPhoneNumber());
        assertThat(actualCustomer).isEqualTo(expectedCustomer);

        // check customer visit
        CustomerVisit actualCustomerVisit = customerVisitRepository.findByLicensePlate(licensePlate).get();
        assertThat(actualCustomerVisit).isEqualTo(expectedCustomerVisit);

        // check tyres in storage point
        StoragePoint storagePoint = storagePointRepository.findStoragePoint(licensePlate).get();
        assertThat(storagePoint.getMountedTyres().size()).isEqualTo(4);
        assertThat(storagePoint.getStoredTyres().size()).isEqualTo(4);
    }
}
