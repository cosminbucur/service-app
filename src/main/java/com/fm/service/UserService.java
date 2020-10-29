package com.fm.service;

import com.fm.model.AppUser;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {

    @Override
    public AppUser create(AppUser appUser) {
        return null;
    }

    @Override
    public List<AppUser> findAll(AppUser appUser) {
        return null;
    }

    @Override
    public Optional<AppUser> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public AppUser update(Long id, AppUser appUser) {
        return null;
    }

    @Override
    public void deactivate(Long id) {

    }
}
