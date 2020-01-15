package com.bucur.service;

import com.bucur.model.StoragePoint;
import com.bucur.model.Tyre;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

class HotelServiceTest {

    @Test
    void getWornTyres() {
        // 1 storage point with 1 worn tyre (from license plate 1)
        // 1 storage point with 1 worn tyre and 1 new tyre (from license plate 2)

        // call method
        // check I get the correct number of worn tyres (2)

        StoragePoint storagePoint1 = new StoragePoint();
        storagePoint1.setTyres(Arrays.asList(new Tyre()));

        StoragePoint storagePoint2 = new StoragePoint();
        storagePoint1.setTyres(Arrays.asList(new Tyre()));
    }
}