package com.fm.util;

import com.fm.dto.request.CustomerVisitWrite;
import com.fm.dto.request.CustomerWrite;
import com.fm.dto.request.ServiceWrite;
import com.fm.dto.request.StoragePointWrite;
import com.fm.dto.request.TyreWrite;
import com.fm.model.RimType;
import com.fm.model.Season;
import com.fm.model.TyreLocation;
import com.fm.model.TyreSize;
import com.fm.model.TyreType;

import java.util.Arrays;
import java.util.List;

public class TestDtoUtils {

    public static final String LICENSE_PLATE = "B222ABC";

    public static CustomerVisitWrite createCustomerVisitInfo() {
        CustomerVisitWrite dto = new CustomerVisitWrite();
        dto.setCustomerInfo(createCustomerInfo());
        dto.setServiceInfo(createServiceInfo());
        dto.setStoragePointInfo(createStoragePointInfo());
        return dto;
    }

    public static CustomerVisitWrite createCustomerVisitInfoWithWornTyres() {
        CustomerVisitWrite dto = new CustomerVisitWrite();
        dto.setCustomerInfo(createCustomerInfo());
        dto.setServiceInfo(createServiceInfo());
        dto.setStoragePointInfo(createStoragePointInfoWithWornTyres());
        return dto;
    }

    public static CustomerWrite createCustomerInfo() {
        CustomerWrite dto = new CustomerWrite();
        dto.setFirstName("Alex");
        dto.setLastName("Xela");
        dto.setCompany("aerospace");
        dto.setEmail("xela@aerospace.ro");
        dto.setPhoneNumber("0722333444");
        return dto;
    }

    private static ServiceWrite createServiceInfo() {
        ServiceWrite dto = new ServiceWrite();
        dto.setMechanicId(1L);
        dto.setServicesPerformed("complete setup");
        dto.setObservations("all went well");
        return dto;
    }

    public static StoragePointWrite createStoragePointInfo() {
        StoragePointWrite dto = new StoragePointWrite();
        dto.setCode("A1B2C3");
        dto.setLicensePlate(LICENSE_PLATE);
        dto.setNumberOfRimCaps(12);
        dto.setMountedTyres(createFourSummerTyresInfo());
        dto.setStoredTyres(createFourWinterTyresInfo());
        return dto;
    }

    public static StoragePointWrite createStoragePointInfoWithWornTyres() {
        StoragePointWrite dto = new StoragePointWrite();
        dto.setLicensePlate(LICENSE_PLATE);
        dto.setNumberOfRimCaps(12);
        dto.setStoredTyres(createFourSummerWornTyresInfo());
        return dto;
    }

    public static List<TyreWrite> createFourSummerTyresInfo() {
        return Arrays.asList(
            createTyreInfo(Season.SUMMER, 8),
            createTyreInfo(Season.SUMMER, 8),
            createTyreInfo(Season.SUMMER, 8),
            createTyreInfo(Season.SUMMER, 8)
        );
    }

    public static List<TyreWrite> createFourSummerWornTyresInfo() {
        return Arrays.asList(
            createTyreInfo(Season.SUMMER, 2),
            createTyreInfo(Season.SUMMER, 8),
            createTyreInfo(Season.SUMMER, 5),
            createTyreInfo(Season.SUMMER, 2)
        );
    }

    public static List<TyreWrite> createFourWinterTyresInfo() {
        return Arrays.asList(
            createTyreInfo(Season.WINTER, 8),
            createTyreInfo(Season.WINTER, 8),
            createTyreInfo(Season.WINTER, 8),
            createTyreInfo(Season.WINTER, 8)
        );
    }

    public static TyreWrite createTyreInfo(Season season, int treadDepth) {
        String tyreBrand = "michelin";
        TyreSize tyreSize = new TyreSize(255, 55, 16);
        return new TyreWrite(tyreBrand, tyreSize, TyreType.REGULAR, season, RimType.ALLOY, treadDepth, TyreLocation.STORED);
    }
}
