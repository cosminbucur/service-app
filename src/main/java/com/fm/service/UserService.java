package com.fm.service;

import com.fm.model.AppUser;
import com.fm.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {

    private final AppUserRepository appUserRepository;

    @Autowired
    public UserService(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @Override
    public AppUser create(AppUser appUser) {
        return appUserRepository.save(appUser);
    }

    @Override
    public List<AppUser> findAll() {
        return appUserRepository.findAll();
    }

    @Override
    public Optional<AppUser> findById(Long id) {
        return appUserRepository.findById(id);
    }

    @Override
    public AppUser update(Long id, AppUser appUser) {
        return appUserRepository.update(id, appUser);
    }

    @Override
    public void deactivate(Long id) {
        findById(id)
            .map(appUser -> {
                appUser.setActive(false);
                return appUser;
            })
            .orElseThrow(() -> new RuntimeException("not found"));
    }
}
