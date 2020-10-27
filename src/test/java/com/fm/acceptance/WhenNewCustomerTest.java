package com.fm.acceptance;

import com.fm.dto.CustomerVisitInfo;
import com.fm.dto.ObjectMapper;
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

import static org.assertj.core.api.Assertions.assertThat;

class WhenNewCustomerTest {

    StoragePointRepository storagePointRepository = new StoragePointH2Repository();
    CustomerRepository customerRepository = new CustomerH2Repository();
    CustomerVisitRepository customerVisitRepository = new CustomerVisitH2Repository();
    ObjectMapper objectMapper = new ObjectMapper();
    IHotelService hotelService = new HotelService(customerVisitRepository, customerRepository, storagePointRepository, objectMapper);

    // user story: first time mount tyres

    @Test
    void shouldStoreTyresForNewCustomer() {
        // given
        CustomerVisitInfo customerVisitInfo = TestDtoUtils.createCustomerVisitInfo();
        String licensePlate = customerVisitInfo.getCustomerInfo().getLicensePlate();

        Customer expectedCustomer = TestUtils.createCustomer();
        CustomerVisit expectedCustomerVisit = TestUtils.createCustomerVisit();

        // when
        hotelService.saveCustomerVisit(customerVisitInfo);

        // then

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
