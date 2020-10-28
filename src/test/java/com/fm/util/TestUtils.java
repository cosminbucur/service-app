package com.fm.util;

import com.fm.model.Customer;
import com.fm.model.CustomerVisit;
import com.fm.model.RimType;
import com.fm.model.Season;
import com.fm.model.Tyre;
import com.fm.model.TyreLocation;
import com.fm.model.TyreSize;
import com.fm.model.TyreType;
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

import java.util.Arrays;
import java.util.List;

public class TestUtils {

    public static final String LICENSE_PLATE = "B222ABC";

    public static ICustomerVisitService createHotelService() {
        StoragePointRepository storagePointRepository = new StoragePointH2Repository();
        CustomerRepository customerRepository = new CustomerH2Repository();
        CustomerVisitRepository customerVisitRepository = new CustomerVisitH2Repository();
        TyreRepository tyreRepository = new TyreH2Repository();
        return new CustomerVisitService(customerVisitRepository, customerRepository, storagePointRepository, tyreRepository);
    }

    public static CustomerVisit createCustomerVisit() {
        CustomerVisit customerVisit = new CustomerVisit();
        customerVisit.setId(1L);
        customerVisit.setMechanicId(1L);
        customerVisit.setObservations(LICENSE_PLATE);
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
        Tyre tyre = new Tyre();
        tyre.setId(1L);
        tyre.setTyreBrand("michelin");
        tyre.setTyreSize(new TyreSize(255, 55, 16));
        tyre.setTyreType(TyreType.REGULAR);
        tyre.setSeason(season);
        tyre.setRimType(RimType.ALLOY);
        tyre.setTyreWearLevel(threadDepth);
        tyre.setTreadDepth(threadDepth);
        tyre.setTyreLocation(TyreLocation.STORED);
        return tyre;
    }

    public static Customer createCustomer() {
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setFirstName("Alex");
        customer.setLastName("Xela");
        customer.setCompany("aerospace");
        customer.setEmail("xela@aerospace.ro");
        customer.setPhoneNumber("0722333444");
        return customer;
    }
}
