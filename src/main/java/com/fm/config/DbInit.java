package com.fm.config;

import com.fm.model.Role;
import com.fm.model.User;
import com.fm.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DbInit implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(DbInit.class);

    private final UserRepository userRepository;

    @Autowired
    public DbInit(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) {
        setupDb();
    }

    private void setupDb() {
        createAdmin();
        createMechanics();
    }

    private void createAdmin() {
        User user = new User();
        user.setFirstName("jon");
        user.setLastName("snow");
        user.setUsername("admin");
        user.setPassword("pass");
        user.setEmail("admin@gmial.com");
        user.getRoles().add(Role.ROLE_ADMIN);
        userRepository.save(user);
    }

    private void createMechanics() {
        User mechanic1 = new User();
        mechanic1.setFirstName("marin");
        mechanic1.setLastName("vasile");
        mechanic1.setUsername("vasi");
        mechanic1.setPassword("parola");
        mechanic1.setEmail("vasi@gmail.com");
        mechanic1.getRoles().add(Role.ROLE_MECHANIC);

        User mechanic2 = new User();
        mechanic2.setFirstName("marin");
        mechanic2.setLastName("vasile");
        mechanic2.setUsername("vasi");
        mechanic2.setPassword("parola");
        mechanic2.setEmail("vasi@gmail.com");
        mechanic2.getRoles().add(Role.ROLE_MECHANIC);

        userRepository.save(mechanic1);
        userRepository.save(mechanic2);

        userRepository.findAll().forEach(appUser -> log.info("Db initialized with {}", appUser));
    }
}
