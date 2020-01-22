package com.bucur.acceptance;

import com.bucur.model.CustomerVisit;
import com.bucur.model.StoragePoint;
import com.bucur.model.Tyre;
import com.bucur.model.TyreType;
import com.bucur.model.UnstoreNewTyres;
import com.bucur.model.Vehicle;
import com.bucur.service.MountingService;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class WhenNewCustomer {

    // test application service


    // Use case 1: first time mount tyres

    // un client

    // 1 masina
    // 4 (FIX) cauciucuri vara montate
    // 5 cauciucuri iarna in masina
    // 1 cauciuc rezerva (vara, iarna, all season)

    // 1 mecanic

    // mecanic demonteaza 4 roti vara
    // mecanic monteaza 4 roti iarna

    // client lasa 6 cauciucuri la mecanic

    // mecanic stocheaza 6 cauciucuri in depozit

    // in hotel: shelf >  row  > floor

    @Test
    public void shouldReplaceTyres() {
        // entity: customer
        // value type: tyre
        // domain service:
        // application service:

        // customer, mechanic
        // vehicle
        // tyre
        // hotel
        // storage point

        List<String> licensePlates = Arrays.asList("B22ABC", "B33BCD");
        LocalDate visitDate = LocalDate.now();
        long customerId = 2L;
        long mechanicId = 3L;
        String tyreBrand = "michelin";
        String licensePlate = "B22ABC";

        CustomerVisit customerVisit = new CustomerVisit(customerId, visitDate, licensePlates);

        MountingService mountingService = new MountingService();

        int threadLevel = 3;
        Vehicle vehicleWithOldTyres = new Vehicle.Builder()
            .withFrontLeft(new Tyre(tyreBrand, TyreType.SUMMER, threadLevel))
            .withFrontRight(new Tyre(tyreBrand, TyreType.SUMMER, threadLevel))
            .withRearLeft(new Tyre(tyreBrand, TyreType.SUMMER, threadLevel))
            .withRearRight(new Tyre(tyreBrand, TyreType.SUMMER, threadLevel))
            .build();

        Tyre newFrontLeft = new Tyre(tyreBrand, TyreType.WINTER, threadLevel);

        Vehicle vehicleWithNewTyres = new Vehicle.Builder()
            .withFrontLeft(newFrontLeft)
            .withFrontRight(new Tyre(tyreBrand, TyreType.WINTER, threadLevel))
            .withRearLeft(new Tyre(tyreBrand, TyreType.WINTER, threadLevel))
            .withRearRight(new Tyre(tyreBrand, TyreType.WINTER, threadLevel))
            .build();


        mountingService.replaceTyres(customerVisit.id, mechanicId, licensePlate, UnstoreNewTyres.YES,
                vehicleWithOldTyres, vehicleWithNewTyres);

        assertThat(vehicleWithNewTyres.frontLeft).isEqualTo(newFrontLeft);

        // Use case 2: season change



        // notificare 6 luni: schimbare sezon

        // use case 3: replace worn tyres in store
        StoragePoint storagePointsWithTyres = hotelService.findStoragePoint(licensePlate);

        List<Tyre> selectedTyres = storagePointsWithTyres.getTyres();

        hotelService.unstoreTyres(storagePoint, customerVisit, selectedTyres);
    }
}
