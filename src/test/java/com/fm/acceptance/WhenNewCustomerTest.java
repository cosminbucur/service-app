package com.fm.acceptance;

import com.fm.dto.CustomerVisitInfo;
import com.fm.dto.TyreInfo;
import com.fm.model.TyreType;
import com.fm.model.WearLevel;
import com.fm.service.MountingService;
import com.fm.util.TestUtils;
import org.junit.jupiter.api.Test;

class WhenNewCustomerTest {

    // user story 3: first time mount tyres
    @Test
    void shouldReplaceTyres() {
        CustomerVisitInfo customerVisitInfo = TestUtils.createCustomerVisitInfo();

        MountingService mountingService = new MountingService();

        TyreInfo newFrontLeft = TestUtils.createTyre(TyreType.WINTER, WearLevel.GOOD);

        mountingService.replaceTyres(customerVisitInfo, 3L, "B22ABC");

        // check mounted tyres
    }
}
