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

import static com.fm.util.TestConstants.CODE;
import static com.fm.util.TestConstants.COMPANY;
import static com.fm.util.TestConstants.EMAIL;
import static com.fm.util.TestConstants.FIRST_NAME;
import static com.fm.util.TestConstants.LAST_NAME;
import static com.fm.util.TestConstants.LICENSE_PLATE;
import static com.fm.util.TestConstants.NUMBER_OF_RIM_CAPS;
import static com.fm.util.TestConstants.OBSERVATIONS;
import static com.fm.util.TestConstants.PHONE_NUMBER;
import static com.fm.util.TestConstants.SERVICES_PERFORMED;
import static com.fm.util.TestConstants.TYRE_BRAND;

public class TestDtoUtils {

    public static CustomerVisitWrite createCustomerVisitWrite() {
        CustomerVisitWrite dto = new CustomerVisitWrite();
        dto.setCustomerInfo(createCustomerWrite());
        dto.setServiceInfo(createServiceWrite());
        dto.setStoragePointInfo(createStoragePointWrite());
        return dto;
    }

    public static CustomerVisitWrite createCustomerVisitInfoWithWornTyres() {
        CustomerVisitWrite dto = new CustomerVisitWrite();
        dto.setCustomerInfo(createCustomerWrite());
        dto.setServiceInfo(createServiceWrite());
        dto.setStoragePointInfo(createStoragePointInfoWithWornTyres());
        return dto;
    }

    public static CustomerWrite createCustomerWrite() {
        CustomerWrite dto = new CustomerWrite();
        dto.setFirstName(FIRST_NAME);
        dto.setLastName(LAST_NAME);
        dto.setCompany(COMPANY);
        dto.setEmail(EMAIL);
        dto.setPhoneNumber(PHONE_NUMBER);
        return dto;
    }

    private static ServiceWrite createServiceWrite() {
        ServiceWrite dto = new ServiceWrite();
        dto.setMechanicId(1L);
        dto.setServicesPerformed(SERVICES_PERFORMED);
        dto.setObservations(OBSERVATIONS);
        return dto;
    }

    public static StoragePointWrite createStoragePointWrite() {
        StoragePointWrite dto = new StoragePointWrite();
        dto.setCode(CODE);
        dto.setLicensePlate(LICENSE_PLATE);
        dto.setNumberOfRimCaps(NUMBER_OF_RIM_CAPS);
        dto.setMountedTyres(createFourSummerTyresWrite());
        dto.setStoredTyres(createFourWinterTyresWrite());
        return dto;
    }

    public static StoragePointWrite createStoragePointInfoWithWornTyres() {
        StoragePointWrite dto = new StoragePointWrite();
        dto.setLicensePlate(LICENSE_PLATE);
        dto.setNumberOfRimCaps(NUMBER_OF_RIM_CAPS);
        dto.setStoredTyres(createFourSummerWornTyresWrite());
        return dto;
    }

    public static List<TyreWrite> createFourSummerTyresWrite() {
        return Arrays.asList(
            createTyreWrite(Season.SUMMER, 8),
            createTyreWrite(Season.SUMMER, 8),
            createTyreWrite(Season.SUMMER, 8),
            createTyreWrite(Season.SUMMER, 8)
        );
    }

    public static List<TyreWrite> createFourSummerWornTyresWrite() {
        return Arrays.asList(
            createTyreWrite(Season.SUMMER, 2),
            createTyreWrite(Season.SUMMER, 8),
            createTyreWrite(Season.SUMMER, 5),
            createTyreWrite(Season.SUMMER, 2)
        );
    }

    public static List<TyreWrite> createFourWinterTyresWrite() {
        return Arrays.asList(
            createTyreWrite(Season.WINTER, 8),
            createTyreWrite(Season.WINTER, 8),
            createTyreWrite(Season.WINTER, 8),
            createTyreWrite(Season.WINTER, 8)
        );
    }

    public static TyreWrite createTyreWrite(Season season, int treadDepth) {
        TyreSize tyreSize = new TyreSize(255, 55, 16);
        return new TyreWrite(TYRE_BRAND, tyreSize, TyreType.REGULAR, season, RimType.ALLOY, treadDepth, TyreLocation.STORED);
    }
}
