package com.fm.acceptance;

import com.fm.dto.CustomerMapper;
import com.fm.dto.CustomerVisitDetails;
import com.fm.dto.StoragePointDetail;
import com.fm.dto.TyreDetail;
import com.fm.model.Customer;
import com.fm.repository.CustomerRepository;
import com.fm.repository.CustomerRepositoryInMemory;
import com.fm.repository.HotelRepository;
import com.fm.repository.HotelRepositoryInMemory;
import com.fm.service.HotelService;
import com.fm.util.TestUtils;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class WhenSeasonChangeTest {

    private final HotelRepository hotelRepository = new HotelRepositoryInMemory();
    private final CustomerRepository customerRepository = new CustomerRepositoryInMemory();
    private final CustomerMapper customerMapper = new CustomerMapper();
    private final HotelService hotelService = new HotelService(hotelRepository, customerRepository, customerMapper);

    // user story 6: store tyres during season change
    @Test
    void shouldStoreTyres() {
        String licensePlate = "B22ABC";
        LocalDate visitDate = LocalDate.now();
        CustomerVisitDetails customerVisitDetails = TestUtils.createCustomerVisitDetails();

        StoragePointDetail storagePointDetail = new StoragePointDetail();
        List<TyreDetail> tyreDetails = TestUtils.createFourTyres();

        hotelService.storeTyres(storagePointDetail, customerVisitDetails, tyreDetails);

        Customer expectedCustomer = customerRepository.findByPhoneNumber(customerVisitDetails.getCustomerDetails().getPhoneNumber());
        String expectedPhoneNumber = expectedCustomer.getPhoneNumber();
        assertThat(expectedPhoneNumber).isEqualTo(customerVisitDetails.getCustomerDetails().getPhoneNumber());
//        assertThat(hotelRepository.findStoragePoint("B22ABC").getTyres().size()).isEqualTo(4);
//        assertThat(customerRepository.findByPhoneNumber("0722333444").getFirstName()).isEqualTo("alex");
    }

    // user story 7: unstore tyres during season change
    @Test
    void shouldUnstoreTyres() {
        CustomerVisitDetails customerVisitDetails = new CustomerVisitDetails(1, LocalDate.now(), "B22ABC");
        StoragePointDetail storagePointsWithTyresDetails = new StoragePointDetail();
        storagePointsWithTyresDetails.setTyreList(TestUtils.createFourTyres());

        List<TyreDetail> selectedTyreDetails = storagePointsWithTyresDetails.getTyreList();

        hotelService.unstoreTyres(storagePointsWithTyresDetails, customerVisitDetails, selectedTyreDetails);

        assertThat(storagePointsWithTyresDetails.getTyreList()).isEmpty();
    }

    // TODO user story 8: notify customers after 6 months
    @Test
    void shouldNotifyCustomersEverySixMonths() {
        StoragePointDetail storagePointDetail1 = new StoragePointDetail();
        storagePointDetail1.setTyreList(TestUtils.createFourTyres());
        StoragePointDetail storagePointDetail2 = new StoragePointDetail();
        storagePointDetail2.setTyreList(TestUtils.createFourTyres());

        LocalDate sixMonthsAgo = LocalDate.of(2019, 6, 20);
        LocalDate recentDate = LocalDate.of(2020, 1, 10);

        CustomerVisitDetails customerVisitDetails1 = new CustomerVisitDetails(1, sixMonthsAgo, "B22ABC");
        CustomerVisitDetails customerVisitDetails2 = new CustomerVisitDetails(1, recentDate, "B22DEF");

        List<CustomerVisitDetails> customerVisitDetails = Arrays.asList(customerVisitDetails1, customerVisitDetails2);

        hotelService.notifyCustomersOnSeasonChange(customerVisitDetails);

        assertThat(customerVisitDetails2.isSeasonPassed()).isFalse();
    }

}
