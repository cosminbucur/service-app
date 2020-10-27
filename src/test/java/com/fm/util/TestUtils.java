package com.fm.util;

import com.fm.dto.ObjectMapper;
import com.fm.model.Customer;
import com.fm.model.CustomerVisit;
import com.fm.model.RimType;
import com.fm.model.Season;
import com.fm.model.Tyre;
import com.fm.model.TyreSize;
import com.fm.model.TyreType;
import com.fm.repository.CustomerH2Repository;
import com.fm.repository.CustomerRepository;
import com.fm.repository.CustomerVisitH2Repository;
import com.fm.repository.CustomerVisitRepository;
import com.fm.repository.StoragePointH2Repository;
import com.fm.repository.StoragePointRepository;
import com.fm.service.HotelService;
import com.fm.service.IHotelService;

import java.util.Arrays;
import java.util.List;

public class TestUtils {

    public static final String LICENSE_PLATE = "B222ABC";

    public static IHotelService createHotelService() {
        StoragePointRepository storagePointRepository = new StoragePointH2Repository();
        CustomerRepository customerRepository = new CustomerH2Repository();
        CustomerVisitRepository customerVisitRepository = new CustomerVisitH2Repository();
        ObjectMapper objectMapper = new ObjectMapper();
        return new HotelService(customerVisitRepository, customerRepository, storagePointRepository, objectMapper);
    }

    public static CustomerVisit createCustomerVisit() {
        CustomerVisit customerVisit = new CustomerVisit();
        customerVisit.setId(1L);
        customerVisit.setMechanicId(1L);
        customerVisit.setObservations("Xela");
        customerVisit.setServicesPerformed("aerospace");
        customerVisit.setCustomer(createCustomer());
        return customerVisit;
    }

    public static List<Tyre> createFourSummerTyres() {
        return Arrays.asList(
            createTyre(Season.SUMMER, 8),
            createTyre(Season.SUMMER, 8),
            createTyre(Season.SUMMER, 8),
            createTyre(Season.SUMMER, 8)
        );
    }

    public static Tyre createTyre(Season season, int threadDepth) {
        String tyreBrand = "michelin";
        TyreSize tyreSize = new TyreSize(255, 55, 16);
        return new Tyre(1L, tyreBrand, tyreSize, TyreType.REGULAR, season, RimType.ALLOY, threadDepth);
    }

    public static Customer createCustomer() {
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setFirstName("Alex");
        customer.setLastName("Xela");
        customer.setCompany("aerospace");
        customer.setEmail("xela@aerospace.ro");
        customer.setPhoneNumber("0722333444");
        customer.addLicensePlate(LICENSE_PLATE);
        return customer;
    }
}
