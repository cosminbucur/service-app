package com.fm.controller;

import com.fm.model.AppUser;
import com.fm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @GetMapping
    public List<AppUser> findAll() {
        return userService.findAll();
    }

    public void findById() {

    }

    public void update() {

    }

    public void deactivate() {

    }

}

