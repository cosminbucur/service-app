package com.bucur.acceptance;

import com.bucur.dto.StoragePoint;
import com.bucur.dto.Tyre;
import com.bucur.model.TyreType;
import com.bucur.repository.HotelRepository;
import com.bucur.repository.HotelRepositoryInMemory;
import com.bucur.service.HotelService;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class WhenTyresInStorage {

    // TODO use case 4: move tyres from one storage point to another
    @Test
    public void shouldSwapStorage() {
        HotelRepository hotelRepository = new HotelRepositoryInMemory();
        HotelService hotelService = new HotelService(hotelRepository);

        StoragePoint oldStorage = new StoragePoint();
        StoragePoint newStorage = new StoragePoint();

        oldStorage.setTyres(createTyres());

        hotelService.swapStorage(oldStorage, newStorage);

        assertThat(oldStorage.getTyres()).isEmpty();
        assertThat(newStorage.getTyres().size()).isEqualTo(4);
    }

    private List<Tyre> createTyres() {
        String tyreBrand = "michelin";
        int treadDepth = 3;
        return Arrays.asList(
                new Tyre(tyreBrand, TyreType.SUMMER, treadDepth, 205, 55, "R16"),
                new Tyre(tyreBrand, TyreType.SUMMER, treadDepth, 205, 55, "R16"),
                new Tyre(tyreBrand, TyreType.SUMMER, treadDepth, 205, 55, "R16"),
                new Tyre(tyreBrand, TyreType.SUMMER, treadDepth, 205, 55, "R16")
        );
    }
}
