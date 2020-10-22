package com.fm.acceptance;

import com.fm.dto.CustomerVisitDetails;
import com.fm.dto.TyreDetail;
import com.fm.dto.Vehicle;
import com.fm.model.TyreType;
import com.fm.model.WearLevel;
import com.fm.service.MountingService;
import com.fm.util.TestUtils;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class WhenNewCustomerTest {

    // user story 3: first time mount tyres
    @Test
    void shouldReplaceTyres() {
        String licensePlates = "B22ABC";
        LocalDate visitDate = LocalDate.now();
        long customerId = 2L;
        long mechanicId = 3L;
        String licensePlate = "B22ABC";

        CustomerVisitDetails customerVisitDetails = new CustomerVisitDetails(0, visitDate, licensePlates);

        MountingService mountingService = new MountingService();

        Vehicle vehicleWithOldTyres = new Vehicle.Builder()
            .withFrontLeft(TestUtils.createTyre(TyreType.SUMMER, WearLevel.GOOD))
            .withFrontRight(TestUtils.createTyre(TyreType.SUMMER, WearLevel.GOOD))
            .withRearLeft(TestUtils.createTyre(TyreType.SUMMER, WearLevel.GOOD))
            .withRearRight(TestUtils.createTyre(TyreType.SUMMER, WearLevel.GOOD))
            .build();

        TyreDetail newFrontLeft = TestUtils.createTyre(TyreType.WINTER, WearLevel.GOOD);

        Vehicle vehicleWithNewTyres = new Vehicle.Builder()
            .withFrontLeft(newFrontLeft)
            .withFrontRight(TestUtils.createTyre(TyreType.WINTER, WearLevel.GOOD))
            .withRearLeft(TestUtils.createTyre(TyreType.WINTER, WearLevel.GOOD))
            .withRearRight(TestUtils.createTyre(TyreType.WINTER, WearLevel.GOOD))
            .build();

        mountingService.replaceTyres(customerVisitDetails, mechanicId, licensePlate, vehicleWithOldTyres, vehicleWithNewTyres);

        assertThat(vehicleWithNewTyres.frontLeft).isEqualTo(newFrontLeft);
    }
}
