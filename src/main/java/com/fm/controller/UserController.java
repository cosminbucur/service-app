package com.fm.controller;

import com.fm.dto.response.JwtTokenResponse;
import com.fm.dto.response.UserRead;
import com.fm.dto.response.UserWrite;
import com.fm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequestMapping("/api/users")
@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public JwtTokenResponse signup(@RequestBody UserWrite request) {
        return userService.signup(request);
    }

    @PostMapping("/login")
    public JwtTokenResponse login(@RequestParam String username,
                                  @RequestParam String password) {
        return userService.login(username, password);
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<UserRead> findAll() {
        return userService.findAll();
    }

    @GetMapping(value = "/{username}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public UserRead search(@PathVariable String username) {
        return userService.search(username);
    }

    @GetMapping(value = "/whoami")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
    public UserRead whoami(HttpServletRequest request) {
        return userService.whoami(request);
    }

    @GetMapping("/refresh")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
    public JwtTokenResponse refresh(HttpServletRequest request) {
        return userService.refresh(request.getRemoteUser());
    }

    @GetMapping("/deactivate")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deactivate(@PathVariable("id") Long id) {
        userService.deactivate(id);
    }

}

