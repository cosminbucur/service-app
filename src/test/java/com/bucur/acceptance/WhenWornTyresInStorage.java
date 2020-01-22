package com.bucur.acceptance;

import com.bucur.model.StoragePoint;
import com.bucur.model.Tyre;
import com.bucur.model.TyreType;
import com.bucur.repository.HotelRepository;
import com.bucur.repository.HotelRepositoryInMemory;
import com.bucur.service.HotelService;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class WhenWornTyresInStorage {

    // use case 5: get the worn tyres list
    @Test
    public void shouldSeeWornTyres() {
        HotelRepository hotelRepository = new HotelRepositoryInMemory();
        HotelService hotelService = new HotelService(hotelRepository);

        Tyre wornTyre1 = new Tyre("michelin", TyreType.SUMMER, 3);
        Tyre wornTyre2 = new Tyre("michelin", TyreType.SUMMER, 2);
        Tyre newTyre1 = new Tyre("michelin", TyreType.SUMMER, 8);
        Tyre wornTyre3 = new Tyre("michelin", TyreType.SUMMER, 2);

        StoragePoint storagePointWithWornTyres = new StoragePoint();
        storagePointWithWornTyres.licensePlate = "B22ABC";
        storagePointWithWornTyres.setTyres(Arrays.asList(wornTyre1, wornTyre2, newTyre1, wornTyre3));

        Map<String, List<Tyre>> wornTyres = hotelService.getWornTyres();

        assertThat(wornTyres.size()).isEqualTo(3);
    }
}
