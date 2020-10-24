package com.fm.repository;

import com.fm.model.CustomerVisit;

import java.util.HashMap;
import java.util.Map;

public class CustomerVisitH2Repository implements CustomerVisitRepository {

    private Map<Long, CustomerVisit> db = new HashMap<>();

    @Override
    public void save(CustomerVisit customerVisit) {
        long nextId = db.size() + 1L;
        customerVisit.setId(nextId);
        db.put(nextId, customerVisit);
    }
}
