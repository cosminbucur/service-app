package com.fm.service;

import com.fm.model.AppUser;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    AppUser create(AppUser appUser);

    List<AppUser> findAll();

    Optional<AppUser> findById(Long id);

    AppUser update(Long id, AppUser appUser);

    void deactivate(Long id);
}
