package com.fm.repository;

import com.fm.dto.StoragePointInfo;
import com.fm.dto.TyreInfo;
import com.fm.model.WearLevel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class HotelH2Repository implements HotelRepository {

    private List<StoragePointInfo> db = new ArrayList<>();

    @Override
    public StoragePointInfo findStoragePoint(String licensePlate) {
        return db.stream()
            .filter(storagePoint -> storagePoint.getLicensePlate().equals(licensePlate))
            .findFirst()
            .orElseThrow(() -> new RuntimeException("no tyres found for license plate " + licensePlate));
    }

    @Override
    public Map<String, List<TyreInfo>> findWornTyres() {
        Map<String, List<TyreInfo>> result = new HashMap<>();

        for (StoragePointInfo storagePoint : this.db) {
            if (!storagePoint.getMountedTyres().isEmpty()) {
                List<TyreInfo> wornTyreInfos = getWornTyresFromStoragePoint(storagePoint.getMountedTyres());
                result.put(storagePoint.getLicensePlate(), wornTyreInfos);
            }
        }

        return result;
    }

    @Override
    public void save(StoragePointInfo storagePointInfo) {
        this.db.add(storagePointInfo);
    }

    @Override
    public List<StoragePointInfo> findStoragePointByLicensePlate(String licensePlate) {
        return db.stream().filter(storagePoint -> storagePoint.getLicensePlate().equals(licensePlate))
            .collect(Collectors.toList());
    }

    private List<TyreInfo> getWornTyresFromStoragePoint(List<TyreInfo> tyreInfos) {
        return tyreInfos.stream()
            .filter(tyre -> tyre.getWearLevel().equals(WearLevel.WARNING) || tyre.getWearLevel().equals(WearLevel.DANGER))
            .collect(Collectors.toList());
    }


    public void setStoragePoints(List<StoragePointInfo> storagePointInfos) {
        this.db = storagePointInfos;
    }
}
