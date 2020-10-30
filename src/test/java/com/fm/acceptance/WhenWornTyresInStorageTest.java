package com.fm.acceptance;

import com.fm.dto.request.CustomerVisitWrite;
import com.fm.model.Tyre;
import com.fm.service.ICustomerVisitService;
import com.fm.util.TestDtoUtils;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static com.fm.util.TestEntityUtils.createCustomerVisitService;
import static org.assertj.core.api.Assertions.assertThat;

class WhenWornTyresInStorageTest {

    private final ICustomerVisitService customerVisitService = createCustomerVisitService();

    @Test
    void shouldSeeWornTyres() {
        // given
        CustomerVisitWrite customerVisit1 = TestDtoUtils.createCustomerVisitInfoWithWornTyres();
        customerVisit1.getStoragePointWrite().setCode("A1B1C1");
        CustomerVisitWrite customerVisit2 = TestDtoUtils.createCustomerVisitInfoWithWornTyres();
        customerVisit2.getStoragePointWrite().setCode("A2B2C2");

        customerVisitService.saveCustomerVisit(customerVisit1);
        customerVisitService.saveCustomerVisit(customerVisit2);

        // when
        Map<String, List<Tyre>> wornTyres = customerVisitService.findWornTyres();

        assertThat(wornTyres).hasSize(6);
    }
}
