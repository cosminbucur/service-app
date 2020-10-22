package com.fm.acceptance;

import com.fm.dto.CustomerMapper;
import com.fm.dto.StoragePointDetail;
import com.fm.dto.TyreDetail;
import com.fm.model.TyreType;
import com.fm.model.WearLevel;
import com.fm.repository.CustomerRepository;
import com.fm.repository.CustomerRepositoryInMemory;
import com.fm.repository.HotelRepository;
import com.fm.repository.HotelRepositoryInMemory;
import com.fm.service.HotelService;
import com.fm.util.TestUtils;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class WhenWornTyresInStorageTest {

    private final HotelRepository hotelRepository = new HotelRepositoryInMemory();
    private final CustomerRepository customerRepository = new CustomerRepositoryInMemory();
    private final CustomerMapper customerMapper = new CustomerMapper();
    private final HotelService hotelService = new HotelService(hotelRepository, customerRepository, customerMapper);

    @Test
    void shouldSeeWornTyres() {
        TyreDetail wornTyreDetail1 = TestUtils.createTyre(TyreType.SUMMER, WearLevel.DANGER);
        TyreDetail wornTyreDetail2 = TestUtils.createTyre(TyreType.SUMMER, WearLevel.WARNING);
        TyreDetail newTyreDetail1 = TestUtils.createTyre(TyreType.SUMMER, WearLevel.GOOD);
        TyreDetail wornTyreDetail3 = TestUtils.createTyre(TyreType.SUMMER, WearLevel.DANGER);

        StoragePointDetail storagePointDetailWithWornTyres = new StoragePointDetail();
        storagePointDetailWithWornTyres.setLicensePlate("B22ABC");
        storagePointDetailWithWornTyres.setTyreList(Arrays.asList(wornTyreDetail1, wornTyreDetail2, newTyreDetail1, wornTyreDetail3));

        hotelRepository.setStoragePoints(Collections.singletonList(storagePointDetailWithWornTyres));

        Map<String, List<TyreDetail>> wornTyres = hotelService.getWornTyres();

        int expectedResult = Map.of("B22ABC", Arrays.asList(wornTyreDetail1, wornTyreDetail2, wornTyreDetail3)).size();

        assertThat(wornTyres).hasSize(expectedResult);
    }
}
