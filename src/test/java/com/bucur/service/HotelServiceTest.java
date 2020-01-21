package com.bucur.service;

import com.bucur.model.StoragePoint;
import com.bucur.model.Tyre;
import com.bucur.repository.HotelRepositoryInMemory;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class HotelServiceTest {

    /*
     scenario:
     1 storage point with 3 worn tyre and 1 new tyre

     call method
     check I get the correct number of worn tyres (3)
     */
    @Test
    public void countWornTyres() {
        // given
        // on given, we instantiate the objects required to create the test scenario
        Tyre wornTyre1 = new Tyre();
        Tyre wornTyre2 = new Tyre();
        Tyre newTyre1 = new Tyre();
        Tyre wornTyre3 = new Tyre();

        wornTyre1.wear = 2;
        wornTyre2.wear = 1;
        newTyre1.wear = 6;
        wornTyre3.wear = 1;

        HotelRepositoryInMemory tyresInStoragePoint = new HotelRepositoryInMemory();
        tyresInStoragePoint.setTyres(Arrays.asList(wornTyre1, wornTyre2, newTyre1, wornTyre3));
        // when
        // on when, we call the method under test from the responsible object
        long actualResult = tyresInStoragePoint.countWornTyres(tyresInStoragePoint);

        // because we have 2 worn tyres in first storage point and 1 in the second point
        long expectedResult = 3;

        // then
        // on then, we check that the actual result is equal to the expected result
        // use the import static org.assertj.core.api.Assertions.assertThat; for fluent assertions
        // read more here https://assertj.github.io/doc/#overview-what-is-assertj
        assertThat(actualResult).isEqualTo(expectedResult);
    }
    /*
     scenario:
     2 storage points with 3 tyre sets; one with worn tyres, one with new tyres and one mixed tyres

     call method
     check I get the correct vehicle license plate with how many worn tyres;
     ex: B222BOS - 4; B158HHH -2.
     */

    @Test
    public void checkForWornTyres_andThen_returnLicensePlate_andNumberOfWornTyres() {
        // create 12 tyres for 3 vehicle license plates
        // one license plate 4 worn tyres, one license plate 4 new tyres, one license plate mix old/new
        // create 2 storage point, set 8 tyres on 1 storage point, 4 tyres on 1 storage point

        //call method

        // verify correct answer

        Tyre vehicle1frontLeftTyre = new Tyre();
        Tyre vehicle1frontRightTyre = new Tyre();
        Tyre vehicle1rearLeftTyre = new Tyre();
        Tyre vehicle1rearRightTyre = new Tyre();

        vehicle1frontLeftTyre.wear = 3;
        vehicle1frontLeftTyre.licensePlate = "B222BOS";

        vehicle1frontRightTyre.wear = 2;
        vehicle1frontRightTyre.licensePlate = "B222BOS";

        vehicle1rearLeftTyre.wear = 2;
        vehicle1frontLeftTyre.licensePlate = "B222BOS";

        vehicle1rearRightTyre.wear = 1;
        vehicle1rearRightTyre.licensePlate = "B222BOS";

        Tyre vehicle2frontLeftTyre = new Tyre();
        Tyre vehicle2frontRightTyre = new Tyre();
        Tyre vehicle2rearLeftTyre = new Tyre();
        Tyre vehicle2rearRightTyre = new Tyre();

        vehicle2frontLeftTyre.wear = 5;
        vehicle2frontLeftTyre.licensePlate = "B248MSD";

        vehicle2frontRightTyre.wear = 5;
        vehicle2frontRightTyre.licensePlate = "B248MSD";

        vehicle2rearLeftTyre.wear = 5;
        vehicle2rearLeftTyre.licensePlate = "B248MSD";

        vehicle2rearRightTyre.wear = 5;
        vehicle2rearRightTyre.licensePlate = "B248MSD";

        StoragePoint storagePoint1 = new StoragePoint();
        storagePoint1.setTyres(Arrays.asList(vehicle1frontLeftTyre, vehicle1frontRightTyre, vehicle1rearLeftTyre, vehicle1rearRightTyre
                , vehicle2frontLeftTyre, vehicle2frontRightTyre, vehicle2rearLeftTyre, vehicle2rearRightTyre));

        Tyre vehicle3frontLeftTyre = new Tyre();
        Tyre vehicle3frontRightTyre = new Tyre();
        Tyre vehicle3rearLeftTyre = new Tyre();
        Tyre vehicle3rearRightTyre = new Tyre();

        vehicle3frontLeftTyre.wear = 5;
        vehicle3frontLeftTyre.licensePlate = "B158HHH";

        vehicle3frontRightTyre.wear = 5;
        vehicle3frontRightTyre.licensePlate = "B158HHH";

        vehicle3rearLeftTyre.wear = 2;
        vehicle3rearLeftTyre.licensePlate = "B158HHH";

        vehicle3rearRightTyre.wear = 2;
        vehicle3rearRightTyre.licensePlate = "B158HHH";

        StoragePoint storagePoint2 = new StoragePoint();
        storagePoint2.setTyres(Arrays.asList(vehicle3frontLeftTyre, vehicle3frontRightTyre, vehicle3rearLeftTyre, vehicle3rearRightTyre));

        HotelRepositoryInMemory hotelRepositoryInMemory = new HotelRepositoryInMemory();
        hotelRepositoryInMemory.setStoragePoints(Arrays.asList(storagePoint1, storagePoint2));

        Map<String, Integer> actualResult = hotelRepositoryInMemory.getLicensePlateAndNumberOfWornTyres(hotelRepositoryInMemory);

        Map<String, Integer> expectedResult = new HashMap<>();
        expectedResult.put("B222BOS", 4);
        expectedResult.put("B158HHH", 2);

        assertThat(actualResult).isEqualTo(expectedResult);
    }
}