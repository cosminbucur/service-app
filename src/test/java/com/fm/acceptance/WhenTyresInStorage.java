package com.fm.acceptance;

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
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class WhenTyresInStorage {

    @Test
    void shouldSwapStorage() {
        HotelRepository hotelRepository = new HotelRepositoryInMemory();
        CustomerRepository customerRepository = new CustomerRepositoryInMemory();
        HotelService hotelService = new HotelService(hotelRepository, customerRepository);

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
