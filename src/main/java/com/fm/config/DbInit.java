package com.fm.config;

import com.fm.model.AppUser;
import com.fm.model.UserRole;
import com.fm.repository.AppUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DbInit {

    private static final Logger log = LoggerFactory.getLogger(DbInit.class);

    private AppUserRepository appUserRepository;

    public DbInit(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
        setupDb();
    }

    private void setupDb() {
        createAdmin();
        createMechanics();
    }

    private void createAdmin() {
        AppUser appUser = new AppUser("jon", "snow", "admin", "sudo", "admin@gmail.com");
        appUser.setRole(UserRole.ADMIN);
        appUserRepository.save(appUser);
    }

    private void createMechanics() {
        AppUser mechanic1 = new AppUser("gigi", "costache", "gigi", "secret", "gigi@gmail.com");
        mechanic1.setRole(UserRole.MECHANIC);

        AppUser mechanic2 = new AppUser("marin", "vasile", "sile", "parola", "vasi@gmail.com");
        mechanic1.setRole(UserRole.MECHANIC);

        appUserRepository.save(mechanic1);
        appUserRepository.save(mechanic2);

        appUserRepository.findAll().forEach(appUser -> log.info("Db initialized with {}", appUser));
    }
}
