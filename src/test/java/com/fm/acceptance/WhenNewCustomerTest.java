package com.fm.acceptance;

import com.fm.dto.CustomerVisitDetails;
import com.fm.dto.TyreDetail;
import com.fm.model.TyreType;
import com.fm.model.WearLevel;
import com.fm.service.MountingService;
import com.fm.util.TestUtils;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

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

        TyreDetail newFrontLeft = TestUtils.createTyre(TyreType.WINTER, WearLevel.GOOD);

    }
}
