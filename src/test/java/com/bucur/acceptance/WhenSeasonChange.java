package com.bucur.acceptance;

import com.bucur.model.CustomerVisit;
import com.bucur.model.StoragePoint;
import com.bucur.model.StoreDismounted;
import com.bucur.model.Tyre;
import com.bucur.repository.HotelRepository;
import com.bucur.repository.HotelRepositoryInMemory;
import com.bucur.service.HotelService;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class WhenSeasonChange {

    @Test
    public void shouldStoreTyres() {
        List<String> licensePlates = Arrays.asList("B22ABC", "B33DEF");
        LocalDate visitDate = LocalDate.now();
        long customerId = 2L;
        CustomerVisit customerVisit = new CustomerVisit(customerId, visitDate, licensePlates);

        HotelRepository hotelRepository = new HotelRepositoryInMemory();
        HotelService hotelService = new HotelService(hotelRepository);
        StoragePoint storagePoint = new StoragePoint();
        List<Tyre> tyres = createTyres();

        hotelService.storeTyres(storagePoint, customerVisit, licensePlates.get(0), StoreDismounted.YES, tyres);

        assertThat(hotelRepository.findStoragePoint("B22ABC").getTyres().size()).isEqualTo(4);
    }

    private List<Tyre> createTyres() {
        return Arrays.asList(new Tyre(), new Tyre(), new Tyre(), new Tyre());
    }
}
