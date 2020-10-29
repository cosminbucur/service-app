package com.fm.repository;

import com.fm.model.Tyre;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class TyreH2Repository implements TyreRepository {

    private static Map<Long, Tyre> db = new HashMap<>();

    @Override
    public Tyre save(Tyre tyre) {
        if (db.get(tyre.getId()) == null) {
            long nextId = db.size() + 1L;
            tyre.setId(nextId);
            db.put(nextId, tyre);
            return db.get(nextId);
        } else {
            db.put(tyre.getId(), tyre);
            return db.get(tyre.getId());
        }
    }

    @Override
    public List<Tyre> findAll() {
        return new ArrayList<>(db.values());
    }
}
