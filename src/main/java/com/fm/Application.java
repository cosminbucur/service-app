package com.fm;

import com.fm.config.DbInit;
import com.fm.repository.AppUserH2Repository;
import com.fm.repository.AppUserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

        AppUserRepository appUserRepository = new AppUserH2Repository();
        new DbInit(appUserRepository);
    }
}
