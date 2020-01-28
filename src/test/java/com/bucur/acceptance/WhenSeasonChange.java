package com.bucur.acceptance;

import com.bucur.dto.CustomerVisit;
import com.bucur.dto.StoragePoint;
import com.bucur.dto.Tyre;
import com.bucur.model.TyreType;
import com.bucur.repository.HotelRepository;
import com.bucur.repository.HotelRepositoryInMemory;
import com.bucur.service.HotelService;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class WhenSeasonChange {

    // TODO use case 2: store tyres during season change
    @Test
    public void shouldStoreTyres() {
        String licensePlates = "B22ABC";
        LocalDate visitDate = LocalDate.now();
        long customerId = 2L;
        CustomerVisit customerVisit = new CustomerVisit(customerId, visitDate, licensePlates);

        HotelRepository hotelRepository = new HotelRepositoryInMemory();
        HotelService hotelService = new HotelService(hotelRepository);
        StoragePoint storagePoint = new StoragePoint();
        List<Tyre> tyres = createTyres();

        hotelService.storeTyres(storagePoint, customerVisit, tyres);

        assertThat(hotelRepository.findStoragePoint("B22ABC").getTyres().size()).isEqualTo(4);
    }

    // TODO use case 3: unstore tyres during season change
    @Test
    public void shouldUnstoreTyres() {
        HotelRepository hotelRepository = new HotelRepositoryInMemory();
        HotelService hotelService = new HotelService(hotelRepository);

        CustomerVisit customerVisit = new CustomerVisit(1, LocalDate.now(), "B22ABC");
        StoragePoint storagePointsWithTyres = new StoragePoint();
        storagePointsWithTyres.setTyres(createTyres());

        List<Tyre> selectedTyres = storagePointsWithTyres.getTyres();

        hotelService.unstoreTyres(storagePointsWithTyres, customerVisit, selectedTyres);

        assertThat(storagePointsWithTyres.getTyres()).isEmpty();
    }

    // TODO use case 6: notify customers after 6 months
    @Test
    public void shouldNotifyCustomersEverySixMonths() {
        HotelRepository hotelRepository = new HotelRepositoryInMemory();
        HotelService hotelService = new HotelService(hotelRepository);

        StoragePoint storagePoint1 = new StoragePoint();
        storagePoint1.setTyres(createTyres());
        StoragePoint storagePoint2 = new StoragePoint();
        storagePoint2.setTyres(createTyres());

        LocalDate sixMonthsAgo = LocalDate.of(2019, 6, 20);
        LocalDate recentDate = LocalDate.of(2020, 1, 10);

        CustomerVisit customerVisit1 = new CustomerVisit(1, sixMonthsAgo, "B22ABC");
        CustomerVisit customerVisit2 = new CustomerVisit(1, recentDate, "B22DEF");

        List<CustomerVisit> customerVisits = Arrays.asList(customerVisit1, customerVisit2);

        hotelService.notifyCustomersOnSeasonChange(customerVisits);

        assertThat(customerVisit1.seasonPassed).isTrue();
        assertThat(customerVisit2.seasonPassed).isFalse();
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
