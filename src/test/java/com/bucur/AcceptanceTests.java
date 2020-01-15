package com.bucur;

import com.bucur.model.CustomerVisit;
import com.bucur.model.StoragePoint;
import com.bucur.model.StoreDismounted;
import com.bucur.model.Tyre;
import com.bucur.model.TyreType;
import com.bucur.model.UnstoreNewTyres;
import com.bucur.model.Vehicle;
import com.bucur.service.HotelService;
import com.bucur.service.MountingService;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class AcceptanceTests {

    // test application service


    // Use case 1: first time mount tyres

    // un client

    // 1 masina
    // 4 (FIX) cauciucuri vara montate
    // 5 cauciucuri iarna in masina
    // 1 cauciuc rezerva (vara, iarna, all season)

    // 1 mecanic

    // mecanic demonteaza 4 roti vara
    // mecanic monteaza 4 roti iarna

    // client lasa 6 cauciucuri la mecanic

    // mecanic stocheaza 6 cauciucuri in depozit

    // in hotel: shelf >  row  > floor

    @Test
    public void test1() {
        // entity: customer
        // value type: tyre
        // domain service:
        // application service:

        // customer, mechanic
        // vehicle
        // tyre
        // hotel
        // storage point

        List<String> licensePlates = Arrays.asList("B22ABC", "B33BCD");
        LocalDate visitDate = LocalDate.now();
        long id = 1L;
        long customerId = 2L;
        long mechanicId = 3L;
        String tyreBrand = "michelin";

        CustomerVisit customerVisit = new CustomerVisit(id, customerId, visitDate, licensePlates);

        MountingService mountingService = new MountingService();

        Vehicle vehicleWithOldTyres = new Vehicle.Builder()
            .withFrontLeft(new Tyre(tyreBrand, TyreType.SUMMER))
            .withFrontRight(new Tyre(tyreBrand, TyreType.SUMMER))
            .withRearLeft(new Tyre(tyreBrand, TyreType.SUMMER))
            .withRearRight(new Tyre(tyreBrand, TyreType.SUMMER))
            .build();

        Tyre newFrontLeft = new Tyre(tyreBrand, TyreType.WINTER);
        Vehicle vehicleWithNewTyres = new Vehicle.Builder()
            .withFrontLeft(newFrontLeft)
            .withFrontRight(new Tyre(tyreBrand, TyreType.WINTER))
            .withRearLeft(new Tyre(tyreBrand, TyreType.WINTER))
            .withRearRight(new Tyre(tyreBrand, TyreType.WINTER))
            .build();

        String licensePlate = "B22ABC";
        mountingService.replaceTyres(customerVisit.id, mechanicId, licensePlate, UnstoreNewTyres.YES,
            vehicleWithOldTyres, vehicleWithNewTyres);

        assertThat(vehicleWithNewTyres.frontLeft).isEqualTo(newFrontLeft);

        // Use case 2: season change

        HotelService hotelService = new HotelService();
        StoragePoint storagePoint = new StoragePoint();
        List<Tyre> tyres = createTyres();
        hotelService.storeTyres(storagePoint, customerVisit, licensePlate, StoreDismounted.YES, tyres);

        // notificare 6 luni: schimbare sezon

        // use case 3: replace worn tyres in store
        StoragePoint storagePointsWithTyres = hotelService.getStoredTyres(licensePlate);

        List<Tyre> selectedTyres = storagePointsWithTyres.getTyres();

        hotelService.unstoreTyres(storagePoint, customerVisit, selectedTyres);

        // use case 4: move tyres from one storage point to another
        StoragePoint oldStorage = new StoragePoint();
        StoragePoint newStorage = new StoragePoint();
        hotelService.swapStorage(oldStorage, selectedTyres, newStorage);

        // use case 5: get the worn tyres list
        StoragePoint storagePointWithWornTyres = new StoragePoint();
        Map<String, List<Tyre>> wornTyres = hotelService.getWornTyres();
    }

    private List<Tyre> createTyres() {
        return Arrays.asList(new Tyre(), new Tyre(), new Tyre(), new Tyre());
    }
}
