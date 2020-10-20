package com.fm.acceptance;

import com.fm.dto.CustomerMapper;
import com.fm.dto.StoragePoint;
import com.fm.dto.Tyre;
import com.fm.model.TyreType;
import com.fm.repository.CustomerRepository;
import com.fm.repository.CustomerRepositoryInMemory;
import com.fm.repository.HotelRepository;
import com.fm.repository.HotelRepositoryInMemory;
import com.fm.service.HotelService;
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
        Tyre wornTyre1 = new Tyre("michelin", TyreType.SUMMER, 3, 205, 55, "R16");
        Tyre wornTyre2 = new Tyre("michelin", TyreType.SUMMER, 2, 205, 55, "R16");
        Tyre newTyre1 = new Tyre("michelin", TyreType.SUMMER, 8, 205, 55, "R16");
        Tyre wornTyre3 = new Tyre("michelin", TyreType.SUMMER, 2, 205, 55, "R16");

        StoragePoint storagePointWithWornTyres = new StoragePoint();
        storagePointWithWornTyres.licensePlate = "B22ABC";
        storagePointWithWornTyres.setTyres(Arrays.asList(wornTyre1, wornTyre2, newTyre1, wornTyre3));

        hotelRepository.setStoragePoints(Collections.singletonList(storagePointWithWornTyres));

        Map<String, List<Tyre>> wornTyres = hotelService.getWornTyres();

        int expectedResult = Map.of("B22ABC", Arrays.asList(wornTyre1, wornTyre2, wornTyre3)).size();

        assertThat(wornTyres.size()).isEqualTo(expectedResult);
    }
}
