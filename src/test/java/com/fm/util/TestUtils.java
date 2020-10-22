package com.fm.util;

import com.fm.dto.CustomerDetail;
import com.fm.dto.CustomerVisitDetails;
import com.fm.dto.TyreDetail;
import com.fm.dto.TyreSize;
import com.fm.model.Customer;
import com.fm.model.RimType;
import com.fm.model.TyreType;
import com.fm.model.WearLevel;

import java.util.Arrays;
import java.util.List;

public class TestUtils {

    public static List<TyreDetail> createFourTyres() {
        return Arrays.asList(
            TestUtils.createTyre(TyreType.SUMMER, WearLevel.GOOD),
            TestUtils.createTyre(TyreType.SUMMER, WearLevel.GOOD),
            TestUtils.createTyre(TyreType.SUMMER, WearLevel.GOOD),
            TestUtils.createTyre(TyreType.SUMMER, WearLevel.GOOD)
        );
    }

    public static TyreDetail createTyre(TyreType tyreType, WearLevel wearLevel) {
        String tyreBrand = "michelin";
        int treadDepth = 3;
        TyreSize tyreSize = new TyreSize(255, 55, 16);

        if (tyreType.equals(TyreType.WINTER)) {
            return new TyreDetail(1L, tyreBrand, tyreSize, TyreType.WINTER, RimType.ALLOY, treadDepth);
        } else {
            return new TyreDetail(1L, tyreBrand, tyreSize, TyreType.SUMMER, RimType.ALLOY, treadDepth);
        }
    }

    public static CustomerVisitDetails createCustomerVisitDetails() {
        CustomerVisitDetails dto = new CustomerVisitDetails();
        dto.setCustomerDetails(createCustomerDetails());
        return dto;
    }

    public static CustomerDetail createCustomerDetails() {
        CustomerDetail dto = new CustomerDetail();
        dto.setFirstName("Alex");
        dto.setLastName("Xela");
        dto.setCompany("aerospace");
        dto.setEmail("xela@aerospace.ro");
        dto.setPhoneNumber("0722333444");
        dto.setLicensePlate("B222ABC");
        return dto;
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
}
