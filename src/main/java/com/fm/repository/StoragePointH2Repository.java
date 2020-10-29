package com.fm.repository;

import com.fm.model.StoragePoint;
import com.fm.model.Tyre;
import com.fm.model.WearLevel;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class StoragePointH2Repository implements StoragePointRepository {

    private static Map<Long, StoragePoint> db = new HashMap<>();

    @Override
    public void save(StoragePoint storagePoint) {
        if (db.get(storagePoint.getId()) == null) {
            long nextId = db.size() + 1L;
            storagePoint.setId(nextId);
            db.put(nextId, storagePoint);
        } else {
            db.put(storagePoint.getId(), storagePoint);
        }
    }

    @Override
    public List<StoragePoint> findAll() {
        return new ArrayList<>(db.values());
    }

    @Override
    public Optional<StoragePoint> findStoragePoint(String licensePlate) {
        return db.values().stream()
            .filter(storagePoint -> storagePoint.getLicensePlate().equals(licensePlate))
            .findFirst();
    }

    @Override
    public Optional<StoragePoint> findStoragePoint(Long id) {
        StoragePoint foundStoragePoint = db.get(id);
        if (foundStoragePoint != null) {
            return Optional.of(foundStoragePoint);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Map<String, List<Tyre>> findWornTyres() {
        // TODO: fix findWornTyres
        Map<String, List<Tyre>> result = new HashMap<>();

        for (StoragePoint storagePoint : db.values()) {
            if (!storagePoint.getStoredTyres().isEmpty()) {
                List<Tyre> wornTyres = getWornTyresFromStoragePoint(storagePoint.getStoredTyres());
                result.put(storagePoint.getLicensePlate(), wornTyres);
            }
        }

        return result;
    }

    private List<Tyre> getWornTyresFromStoragePoint(List<Tyre> tyres) {
        return tyres.stream()
            .filter(tyre -> tyre.getWearLevel().equals(WearLevel.WARNING) || tyre.getWearLevel().equals(WearLevel.DANGER))
            .collect(Collectors.toList());
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
