package com.bucur.service;

import com.bucur.model.Tyre;
import com.bucur.repository.HotelRepositoryInMemory;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

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

        HotelRepositoryInMemory hotelRepositoryInMemory = new HotelRepositoryInMemory();
        hotelRepositoryInMemory.setTyres(Arrays.asList(wornTyre1, wornTyre2, newTyre1, wornTyre3));
        // when
        // on when, we call the method under test from the responsible object
        int actualResult = hotelRepositoryInMemory.countWornTyres(" ");

        // because we have 2 worn tyres in first storage point and 1 in the second point
        int expectedResult = 3;

        // then
        // on then, we check that the actual result is equal to the expected result
        // use the import static org.assertj.core.api.Assertions.assertThat; for fluent assertions
        // read more here https://assertj.github.io/doc/#overview-what-is-assertj
        assertThat(actualResult).isEqualTo(expectedResult);
    }

}