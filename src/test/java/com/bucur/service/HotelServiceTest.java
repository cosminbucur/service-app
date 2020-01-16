package com.bucur.service;

import com.bucur.model.StoragePoint;
import com.bucur.model.Tyre;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

class HotelServiceTest {

    @Test
    public void getWornTyres() {
        // 1 storage point with 1 worn tyre (from license plate 1)
        // 1 storage point with 1 worn tyre and 1 new tyre (from license plate 2)

        // call method
        // check I get the correct number of worn tyres (2)

        StoragePoint storagePoint1 = new StoragePoint();
        storagePoint1.setTyres(Arrays.asList(new Tyre()));

        StoragePoint storagePoint2 = new StoragePoint();
        storagePoint2.setTyres(Arrays.asList(new Tyre(), new Tyre()));

        int numberOfWornTyres = countNumberOfWornTyres();

        Assertions.assertEquals(2, numberOfWornTyres);

    }

    private int countNumberOfWornTyres() {
        Tyre tyre1 = new Tyre(1);
        Tyre tyre2 = new Tyre(2);
        Tyre tyre3 = new Tyre(6);

        int wornTyresCount = 0;

        if (tyre1.wear < 3) {
            wornTyresCount++;
        }
        if (tyre2.wear < 3) {
            wornTyresCount++;
        }
        if (tyre3.wear < 3) {
            wornTyresCount++;
        }

        return wornTyresCount;
    }
}