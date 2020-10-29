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
    public void save(AppUser appUser) {
        long nextId = db.size() + 1L;
        appUser.setId(nextId);
        db.put(nextId, appUser);
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
    public void update(Long id, AppUser appUser) {
        db.put(id, appUser);
    }

    @Override
    public void delete(Long id) {
        db.remove(id);
    }
}
