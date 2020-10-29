package com.fm.repository;

import com.fm.model.AppUser;

import java.util.List;
import java.util.Optional;

public interface AppUserRepository {

    AppUser save(AppUser appUser);

    List<AppUser> findAll();

    Optional<AppUser> findById(Long id);

    AppUser update(Long id, AppUser appUser);

    void delete(Long id);
}
