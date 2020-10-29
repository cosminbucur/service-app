package com.fm.repository;

import com.fm.model.AppUser;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class AppUserH2Repository implements AppUserRepository {

    private static Map<Long, AppUser> db = new HashMap<>();

    @Override
    public AppUser save(AppUser appUser) {
        long nextId = db.size() + 1L;
        appUser.setId(nextId);
        db.put(nextId, appUser);
        return db.get(nextId);
    }

    @Override
    public List<AppUser> findAll() {
        return new ArrayList<>(db.values());
    }

    @Override
    public Optional<AppUser> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public AppUser update(Long id, AppUser appUser) {
        db.put(id, appUser);
        return db.get(id);
    }

    @Override
    public void delete(Long id) {
        db.remove(id);
    }
}
