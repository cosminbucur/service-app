package com.fm;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Application {

    // TODO: add in memory database support
    // add H2 database dependency
    // configure db in application.yml
    @RequestMapping("/")
    public static void main(String[] args) {

    }
}
