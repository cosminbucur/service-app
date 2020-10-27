package com.fm.util;

import com.fm.dto.CustomerInfo;
import com.fm.dto.CustomerVisitInfo;
import com.fm.dto.ServiceInfo;
import com.fm.dto.StoragePointInfo;
import com.fm.dto.TyreInfo;
import com.fm.dto.TyreSizeInfo;
import com.fm.model.RimType;
import com.fm.model.TyreType;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class TestDtoUtils {

    public static final String LICENSE_PLATE = "B222ABC";

    public static CustomerVisitInfo createCustomerVisitInfo() {
        CustomerVisitInfo dto = new CustomerVisitInfo();
        dto.setCustomerInfo(createCustomerInfo());
        dto.setServiceInfo(createServiceInfo());
        dto.setStoragePointInfo(createStoragePointInfo());
        return dto;
    }

    public static CustomerVisitInfo createCustomerVisitInfoWithWornTyres() {
        CustomerVisitInfo dto = new CustomerVisitInfo();
        dto.setCustomerInfo(createCustomerInfo());
        dto.setServiceInfo(createServiceInfo());
        dto.setStoragePointInfo(createStoragePointInfoWithWornTyres());
        return dto;
    }

    public static CustomerInfo createCustomerInfo() {
        CustomerInfo dto = new CustomerInfo();
        dto.setFirstName("Alex");
        dto.setLastName("Xela");
        dto.setCompany("aerospace");
        dto.setEmail("xela@aerospace.ro");
        dto.setPhoneNumber("0722333444");
        dto.setLicensePlate(LICENSE_PLATE);
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
        dto.setLicensePlate(LICENSE_PLATE);
        dto.setNumberOfRimCaps(12);
        dto.setMountedTyres(createFourSummerTyresInfo());
        dto.setStoredTyres(createFourWinterTyresInfo());
        return dto;
    }

    public static StoragePointInfo createStoragePointInfoWithWornTyres() {
        StoragePointInfo dto = new StoragePointInfo();
        dto.setLicensePlate(LICENSE_PLATE);
        dto.setNumberOfRimCaps(12);
        dto.setStoredTyres(createFourSummerWornTyresInfo());
        return dto;
    }

    public static List<TyreInfo> createFourSummerTyresInfo() {
        return Arrays.asList(
            createTyreInfo(TyreType.SUMMER, 8),
            createTyreInfo(TyreType.SUMMER, 8),
            createTyreInfo(TyreType.SUMMER, 8),
            createTyreInfo(TyreType.SUMMER, 8)
        );
    }

    public static List<TyreInfo> createFourSummerWornTyresInfo() {
        return Arrays.asList(
            createTyreInfo(TyreType.SUMMER, 2),
            createTyreInfo(TyreType.SUMMER, 8),
            createTyreInfo(TyreType.SUMMER, 5),
            createTyreInfo(TyreType.SUMMER, 2)
        );
    }

    public static List<TyreInfo> createFourWinterTyresInfo() {
        return Arrays.asList(
            createTyreInfo(TyreType.WINTER, 8),
            createTyreInfo(TyreType.WINTER, 8),
            createTyreInfo(TyreType.WINTER, 8),
            createTyreInfo(TyreType.WINTER, 8)
        );
    }

    public static TyreInfo createTyreInfo(TyreType tyreType, int treadDepth) {
        String tyreBrand = "michelin";
        TyreSizeInfo tyreSizeInfo = new TyreSizeInfo(255, 55, 16);

        if (tyreType.equals(TyreType.WINTER)) {
            return new TyreInfo(1L, tyreBrand, tyreSizeInfo, TyreType.WINTER, RimType.ALLOY, treadDepth);
        } else {
            return new TyreInfo(1L, tyreBrand, tyreSizeInfo, TyreType.SUMMER, RimType.ALLOY, treadDepth);
        }
    }
}
