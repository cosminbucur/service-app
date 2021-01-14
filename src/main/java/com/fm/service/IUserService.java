package com.fm.service;

import com.fm.dto.request.UserWrite;
import com.fm.dto.response.JwtTokenResponse;
import com.fm.dto.response.UserRead;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface IUserService {

    JwtTokenResponse signup(UserWrite request);

    UserRead search(String username);

    UserRead whoami(HttpServletRequest request);

    List<UserRead> findAll();

    /**
     * Finds a user by id. Can not fail.
     *
     * @param id
     * @return
     */
    UserRead findById(Long id);

    UserRead update(Long id, UserWrite request);

    void deactivate(Long id);

    // security

    JwtTokenResponse login(String username, String password);

    JwtTokenResponse refresh(String username);
}
