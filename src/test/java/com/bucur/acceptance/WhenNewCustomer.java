package com.bucur.acceptance;

import com.bucur.dto.CustomerVisit;
import com.bucur.dto.Tyre;
import com.bucur.dto.Vehicle;
import com.bucur.model.TyreType;
import com.bucur.service.MountingService;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class WhenNewCustomer {

    // TODO use case 1: first time mount tyres
    @Test
    public void shouldReplaceTyres() {
        List<String> licensePlates = Arrays.asList("B22ABC", "B33BCD");
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
