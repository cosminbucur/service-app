package com.fm.util;

import com.fm.dto.CustomerInfo;
import com.fm.dto.CustomerMapper;
import com.fm.dto.CustomerVisitInfo;
import com.fm.dto.ServiceInfo;
import com.fm.dto.StoragePointInfo;
import com.fm.dto.TyreInfo;
import com.fm.dto.TyreSize;
import com.fm.model.Customer;
import com.fm.model.RimType;
import com.fm.model.TyreType;
import com.fm.model.WearLevel;
import com.fm.repository.CustomerH2Repository;
import com.fm.repository.CustomerRepository;
import com.fm.repository.CustomerVisitH2Repository;
import com.fm.repository.CustomerVisitRepository;
import com.fm.repository.HotelH2Repository;
import com.fm.repository.HotelRepository;
import com.fm.service.HotelService;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class TestUtils {

    public static HotelService createHotelService() {
        HotelRepository hotelRepository = new HotelH2Repository();
        CustomerRepository customerRepository = new CustomerH2Repository();
        CustomerVisitRepository customerVisitRepository = new CustomerVisitH2Repository();
        CustomerMapper customerMapper = new CustomerMapper();
        return new HotelService(hotelRepository, customerRepository, customerMapper, customerVisitRepository);
    }

    public static List<TyreInfo> createFourSummerTyres() {
        return Arrays.asList(
            TestUtils.createTyre(TyreType.SUMMER, WearLevel.GOOD),
            TestUtils.createTyre(TyreType.SUMMER, WearLevel.GOOD),
            TestUtils.createTyre(TyreType.SUMMER, WearLevel.GOOD),
            TestUtils.createTyre(TyreType.SUMMER, WearLevel.GOOD)
        );
    }

    public static List<TyreInfo> createFourWinterTyres() {
        return Arrays.asList(
            TestUtils.createTyre(TyreType.WINTER, WearLevel.GOOD),
            TestUtils.createTyre(TyreType.WINTER, WearLevel.GOOD),
            TestUtils.createTyre(TyreType.WINTER, WearLevel.GOOD),
            TestUtils.createTyre(TyreType.WINTER, WearLevel.GOOD)
        );
    }

    public static TyreInfo createTyre(TyreType tyreType, WearLevel wearLevel) {
        String tyreBrand = "michelin";
        int treadDepth = 3;
        TyreSize tyreSize = new TyreSize(255, 55, 16);

        if (tyreType.equals(TyreType.WINTER)) {
            return new TyreInfo(1L, tyreBrand, tyreSize, TyreType.WINTER, RimType.ALLOY, treadDepth);
        } else {
            return new TyreInfo(1L, tyreBrand, tyreSize, TyreType.SUMMER, RimType.ALLOY, treadDepth);
        }
    }

    public static CustomerVisitInfo createCustomerVisitInfo() {
        CustomerVisitInfo dto = new CustomerVisitInfo();
        dto.setCustomerInfo(createCustomerInfo());
        dto.setServiceInfo(createServiceInfo());
        return dto;
    }

    public static CustomerInfo createCustomerInfo() {
        CustomerInfo dto = new CustomerInfo();
        dto.setFirstName("Alex");
        dto.setLastName("Xela");
        dto.setCompany("aerospace");
        dto.setEmail("xela@aerospace.ro");
        dto.setPhoneNumber("0722333444");
        dto.setLicensePlate("B222ABC");
        return dto;
    }

    private static ServiceInfo createServiceInfo() {
        ServiceInfo dto = new ServiceInfo();
        dto.setVisitDate(LocalDate.now());
        dto.setMechanicId(1L);
        dto.setServicesPerformed("complete setup");
        dto.setObservations("all went well");
        return dto;
    }

    public static StoragePointInfo createStoragePointInfo() {
        StoragePointInfo dto = new StoragePointInfo();
        dto.setLicensePlate("B222ABC");
        dto.setNumberOfRimCaps(12);
        dto.setMountedTyres(createFourSummerTyres());
        dto.setStorageTyres(createFourWinterTyres());
        return dto;
    }

    public static Customer createCustomer() {
        Customer customer = new Customer();
        customer.setId(2L);
        customer.setFirstName("Alex");
        customer.setLastName("Xela");
        customer.setCompany("aerospace");
        customer.setEmail("xela@aerospace.ro");
        customer.setPhoneNumber("0722333444");
        return customer;
    }
}
