package com.fm.controller;

import com.fm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/users")
@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    public void create() {

    }

    public void findAll() {

    }

    public void findById() {

    }

    public void update() {

    }

    public void deactivate() {

    }

}

