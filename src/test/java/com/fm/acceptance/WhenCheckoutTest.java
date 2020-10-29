package com.fm.acceptance;

import com.fm.dto.request.CustomerVisitWrite;
import com.fm.dto.response.StoragePointRead;
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
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class WhenCheckoutTest {

    StoragePointRepository storagePointRepository = new StoragePointH2Repository();
    CustomerRepository customerRepository = new CustomerH2Repository();
    CustomerVisitRepository customerVisitRepository = new CustomerVisitH2Repository();
    TyreRepository tyreRepository = new TyreH2Repository();
    ICustomerVisitService customerVisitService = new CustomerVisitService(customerVisitRepository, customerRepository, storagePointRepository, tyreRepository);

    // user story: checkout storage

    @Test
    void shouldCheckoutTyres() {
        // given
        CustomerVisitWrite customerVisitWrite = TestDtoUtils.createCustomerVisitWrite();
        customerVisitService.saveCustomerVisit(customerVisitWrite);

        // when
        customerVisitService.checkout(customerVisitWrite.getStoragePointInfo().getLicensePlate());

        // then
        StoragePointRead storagePoint = customerVisitService.findStoragePoint(customerVisitWrite.getStoragePointInfo().getLicensePlate());

        // check storage point cleared
        assertThat(storagePoint.getNumberOfRimCaps()).isZero();
        assertThat(storagePoint.getMountedTyres()).isEmpty();
        assertThat(storagePoint.getStoredTyres()).isEmpty();
        assertThat(storagePoint.isCleared()).isTrue();
    }
}
