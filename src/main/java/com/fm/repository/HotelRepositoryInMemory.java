package com.fm.repository;

import com.fm.dto.StoragePointDetail;
import com.fm.dto.TyreDetail;
import com.fm.model.WearLevel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class HotelRepositoryInMemory implements HotelRepository {

    private List<StoragePointDetail> storagePointDetails = new ArrayList<>();

    @Override
    public StoragePointDetail findStoragePoint(String licensePlate) {
        return storagePointDetails.stream()
            .filter(storagePoint -> storagePoint.getLicensePlate().equals(licensePlate))
            .findFirst()
            .orElseThrow(() -> new RuntimeException("no tyres found for license plate " + licensePlate));
    }

    @Override
    public Map<String, List<TyreDetail>> findWornTyres() {
        Map<String, List<TyreDetail>> result = new HashMap<>();

        for (StoragePointDetail storagePoint : this.storagePointDetails) {
            if (!storagePoint.getTyreList().isEmpty()) {
                List<TyreDetail> wornTyreDetails = getWornTyresFromStoragePoint(storagePoint.getTyreList());
                result.put(storagePoint.getLicensePlate(), wornTyreDetails);
            }
        }

        return result;
    }

    @Override
    public void save(StoragePointDetail storagePointDetail) {
        this.storagePointDetails.add(storagePointDetail);
    }

    @Override
    public List<StoragePointDetail> getStoragePointByLicensePlate(String licensePlate) {
        return storagePointDetails.stream().filter(storagePoint -> storagePoint.getLicensePlate().equals(licensePlate))
            .collect(Collectors.toList());
    }

    private List<TyreDetail> getWornTyresFromStoragePoint(List<TyreDetail> tyreDetails) {
        return tyreDetails.stream()
            .filter(tyre -> tyre.getWearLevel().equals(WearLevel.WARNING) || tyre.getWearLevel().equals(WearLevel.DANGER))
            .collect(Collectors.toList());
    }

    @Override
    public void setStoragePoints(List<StoragePointDetail> storagePointDetails) {
        this.storagePointDetails = storagePointDetails;
    }
}
