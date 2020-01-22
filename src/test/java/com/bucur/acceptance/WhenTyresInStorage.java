package com.bucur.acceptance;

import com.bucur.model.StoragePoint;
import com.bucur.repository.HotelRepository;
import com.bucur.repository.HotelRepositoryInMemory;
import com.bucur.service.HotelService;
import org.junit.jupiter.api.Test;

public class WhenTyresInStorage {

    // use case 4: move tyres from one storage point to another
    @Test
    public void shouldSwapStorage() {
        HotelRepository hotelRepository = new HotelRepositoryInMemory();
        HotelService hotelService = new HotelService(hotelRepository);

        StoragePoint oldStorage = new StoragePoint();
        StoragePoint newStorage = new StoragePoint();

        hotelService.swapStorage(oldStorage, selectedTyres, newStorage);
    }
}
