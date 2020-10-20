package com.fm.acceptance;

import com.fm.dto.CustomerDetails;
import com.fm.dto.CustomerMapper;
import com.fm.dto.CustomerVisit;
import com.fm.dto.StoragePoint;
import com.fm.dto.Tyre;
import com.fm.model.Customer;
import com.fm.model.TyreType;
import com.fm.repository.CustomerRepository;
import com.fm.repository.CustomerRepositoryInMemory;
import com.fm.repository.HotelRepository;
import com.fm.repository.HotelRepositoryInMemory;
import com.fm.service.HotelService;
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
        CustomerVisit customerVisit = createCustomerVisit();

        StoragePoint storagePoint = new StoragePoint();
        List<Tyre> tyres = createTyres();

        hotelService.storeTyres(storagePoint, customerVisit, tyres);

        Customer expectedCustomer = customerRepository.findByPhoneNumber(customerVisit.getCustomerDetails().getPhoneNumber());
        String expectedPhoneNumber = expectedCustomer.getPhoneNumber();
        assertThat(expectedPhoneNumber).isEqualTo(customerVisit.getCustomerDetails().getPhoneNumber());
//        assertThat(hotelRepository.findStoragePoint("B22ABC").getTyres().size()).isEqualTo(4);
//        assertThat(customerRepository.findByPhoneNumber("0722333444").getFirstName()).isEqualTo("alex");
    }

    // user story 7: unstore tyres during season change
    @Test
    void shouldUnstoreTyres() {
        CustomerVisit customerVisit = new CustomerVisit(1, LocalDate.now(), "B22ABC");
        StoragePoint storagePointsWithTyres = new StoragePoint();
        storagePointsWithTyres.setTyres(createTyres());

        List<Tyre> selectedTyres = storagePointsWithTyres.getTyres();

        hotelService.unstoreTyres(storagePointsWithTyres, customerVisit, selectedTyres);

        assertThat(storagePointsWithTyres.getTyres()).isEmpty();
    }

    // TODO user story 8: notify customers after 6 months
    @Test
    void shouldNotifyCustomersEverySixMonths() {
        StoragePoint storagePoint1 = new StoragePoint();
        storagePoint1.setTyres(createTyres());
        StoragePoint storagePoint2 = new StoragePoint();
        storagePoint2.setTyres(createTyres());

        LocalDate sixMonthsAgo = LocalDate.of(2019, 6, 20);
        LocalDate recentDate = LocalDate.of(2020, 1, 10);

        CustomerVisit customerVisit1 = new CustomerVisit(1, sixMonthsAgo, "B22ABC");
        CustomerVisit customerVisit2 = new CustomerVisit(1, recentDate, "B22DEF");

        List<CustomerVisit> customerVisits = Arrays.asList(customerVisit1, customerVisit2);

        hotelService.notifyCustomersOnSeasonChange(customerVisits);

        assertThat(customerVisit2.isSeasonPassed()).isFalse();
    }

    private List<Tyre> createTyres() {
        String tyreBrand = "michelin";
        int treadDepth = 3;
        return Arrays.asList(
                new Tyre(tyreBrand, TyreType.SUMMER, treadDepth, 205, 55, "R16"),
                new Tyre(tyreBrand, TyreType.SUMMER, treadDepth, 205, 55, "R16"),
                new Tyre(tyreBrand, TyreType.SUMMER, treadDepth, 205, 55, "R16"),
                new Tyre(tyreBrand, TyreType.SUMMER, treadDepth, 205, 55, "R16")
        );
    }

    private Customer createCustomer() {
        Customer customer = new Customer();
        customer.setId(2L);
        customer.setFirstName("Alex");
        customer.setLastName("Xela");
        customer.setCompany("aerospace");
        customer.setEmail("xela@aerospace.ro");
        customer.setPhoneNumber("0722333444");
        return customer;
    }

    private CustomerVisit createCustomerVisit() {
        CustomerVisit dto = new CustomerVisit();
        dto.setCustomerDetails(createCustomerDetails());
        return dto;
    }

    private CustomerDetails createCustomerDetails() {
        CustomerDetails dto = new CustomerDetails();
        dto.setFirstName("Alex");
        dto.setLastName("Xela");
        dto.setCompany("aerospace");
        dto.setEmail("xela@aerospace.ro");
        dto.setPhoneNumber("0722333444");
        dto.setLicensePlate("B222ABC");
        return dto;
    }
}
