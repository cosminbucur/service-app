package com.fm.acceptance;

import com.fm.dto.CustomerVisit;
import com.fm.dto.Tyre;
import com.fm.dto.Vehicle;
import com.fm.model.TyreType;
import com.fm.service.MountingService;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

// TODO: update tests to junit 5 syntax
public class WhenNewCustomer {

    // user story 3: first time mount tyres
    @Test
    public void shouldReplaceTyres() {
        String licensePlates = "B22ABC";
        LocalDate visitDate = LocalDate.now();
        long customerId = 2L;
        long mechanicId = 3L;
        String tyreBrand = "michelin";
        String licensePlate = "B22ABC";

        CustomerVisit customerVisit = new CustomerVisit(customerId, visitDate, licensePlates);

        MountingService mountingService = new MountingService();

        int treadDepth = 3;
        Vehicle vehicleWithOldTyres = new Vehicle.Builder()
            .withFrontLeft(new Tyre(tyreBrand, TyreType.SUMMER, treadDepth, 185, 65, "R15"))
            .withFrontRight(new Tyre(tyreBrand, TyreType.SUMMER, treadDepth, 185, 65, "R15"))
            .withRearLeft(new Tyre(tyreBrand, TyreType.SUMMER, treadDepth, 185, 65, "R15"))
            .withRearRight(new Tyre(tyreBrand, TyreType.SUMMER, treadDepth, 185, 65, "R15"))
            .build();

        Tyre newFrontLeft = new Tyre(tyreBrand, TyreType.WINTER, treadDepth, 195, 65, "R15");

        Vehicle vehicleWithNewTyres = new Vehicle.Builder()
            .withFrontLeft(newFrontLeft)
            .withFrontRight(new Tyre(tyreBrand, TyreType.WINTER, treadDepth, 195, 65, "R15"))
            .withRearLeft(new Tyre(tyreBrand, TyreType.WINTER, treadDepth, 195, 65, "R15"))
            .withRearRight(new Tyre(tyreBrand, TyreType.WINTER, treadDepth, 195, 65, "R15"))
            .build();

        mountingService.replaceTyres(customerVisit, mechanicId, licensePlate, vehicleWithOldTyres, vehicleWithNewTyres);

        assertThat(vehicleWithNewTyres.frontLeft).isEqualTo(newFrontLeft);
    }
}
