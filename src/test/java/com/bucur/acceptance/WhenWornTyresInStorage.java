package com.bucur.acceptance;

import com.bucur.dto.StoragePoint;
import com.bucur.dto.Tyre;
import com.bucur.model.TyreType;
import com.bucur.repository.HotelRepository;
import com.bucur.repository.HotelRepositoryInMemory;
import com.bucur.service.HotelService;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class WhenWornTyresInStorage {

    // TODO user story 5: get the worn tyres list
    @Test
    public void shouldSeeWornTyres() {
        HotelRepository hotelRepository = new HotelRepositoryInMemory();
        HotelService hotelService = new HotelService(hotelRepository);

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
