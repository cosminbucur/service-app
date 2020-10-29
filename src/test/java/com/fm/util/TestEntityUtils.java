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

import static com.fm.util.TestConstants.COMPANY;
import static com.fm.util.TestConstants.EMAIL;
import static com.fm.util.TestConstants.FIRST_NAME;
import static com.fm.util.TestConstants.LAST_NAME;
import static com.fm.util.TestConstants.OBSERVATIONS;
import static com.fm.util.TestConstants.PHONE_NUMBER;
import static com.fm.util.TestConstants.SERVICES_PERFORMED;
import static com.fm.util.TestConstants.TYRE_BRAND;

public class TestEntityUtils {

    public static ICustomerVisitService createCustomerVisitService() {
        StoragePointRepository storagePointRepository = new StoragePointH2Repository();
        CustomerRepository customerRepository = new CustomerH2Repository();
        CustomerVisitRepository customerVisitRepository = new CustomerVisitH2Repository();
        TyreRepository tyreRepository = new TyreH2Repository();
        return new CustomerVisitService(customerVisitRepository, customerRepository, storagePointRepository, tyreRepository);
    }

    public static CustomerVisit createCustomerVisit() {
        CustomerVisit entity = new CustomerVisit();
        entity.setId(1L);
        entity.setMechanicId(1L);
        entity.setObservations(OBSERVATIONS);
        entity.setServicesPerformed(SERVICES_PERFORMED);
        entity.setCustomer(createCustomer());
        return entity;
    }

    public static Customer createCustomer() {
        Customer entity = new Customer();
        entity.setId(1L);
        entity.setFirstName(FIRST_NAME);
        entity.setLastName(LAST_NAME);
        entity.setCompany(COMPANY);
        entity.setEmail(EMAIL);
        entity.setPhoneNumber(PHONE_NUMBER);
        return entity;
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
        tyre.setTyreBrand(TYRE_BRAND);
        tyre.setTyreSize(new TyreSize(255, 55, 16));
        tyre.setTyreType(TyreType.REGULAR);
        tyre.setSeason(season);
        tyre.setRimType(RimType.ALLOY);
        tyre.setTyreWearLevel(threadDepth);
        tyre.setTreadDepth(threadDepth);
        tyre.setTyreLocation(TyreLocation.STORED);
        return tyre;
    }
}
