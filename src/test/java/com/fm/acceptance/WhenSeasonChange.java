package com.fm.acceptance;

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

public class WhenSeasonChange {

    // user story 6: store tyres during season change
    @Test
    public void shouldStoreTyres() {
        //create new customer
        Customer customer = createCustomer();

        String licensePlate = "B22ABC";
        LocalDate visitDate = LocalDate.now();
        CustomerVisit customerVisit = new CustomerVisit(customer.getId(), visitDate, licensePlate);

        HotelRepository hotelRepository = new HotelRepositoryInMemory();
        HotelService hotelService = new HotelService(hotelRepository);

        CustomerRepository customerRepository = new CustomerRepositoryInMemory();

        StoragePoint storagePoint = new StoragePoint();
        List<Tyre> tyres = createTyres();

        hotelService.storeTyres(customer, storagePoint, customerVisit, tyres);

        assertThat(hotelRepository.findStoragePoint("B22ABC").getTyres().size()).isEqualTo(4);
        assertThat(customerRepository.findCustomerByPhoneNumber(55L).getFirstName().equals("Alex"));
    }

    // user story 7: unstore tyres during season change
    @Test
    public void shouldUnstoreTyres() {
        HotelRepository hotelRepository = new HotelRepositoryInMemory();

        HotelService hotelService = new HotelService(hotelRepository);

        CustomerVisit customerVisit = new CustomerVisit(1, LocalDate.now(), "B22ABC");
        StoragePoint storagePointsWithTyres = new StoragePoint();
        storagePointsWithTyres.setTyres(createTyres());

        List<Tyre> selectedTyres = storagePointsWithTyres.getTyres();

        hotelService.unstoreTyres(storagePointsWithTyres, customerVisit, selectedTyres);

        assertThat(storagePointsWithTyres.getTyres()).isEmpty();
    }

    // TODO user story 8: notify customers after 6 months
    @Test
    public void shouldNotifyCustomersEverySixMonths() {
        HotelRepository hotelRepository = new HotelRepositoryInMemory();
        HotelService hotelService = new HotelService(hotelRepository);

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
        customer.setEmailAddress("xela@aerospace.ro");
        customer.setPhoneNumber(55L);
        return customer;
    }
}
