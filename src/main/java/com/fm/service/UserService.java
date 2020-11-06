package com.fm.service;

import com.fm.dto.mapper.UserMapper;
import com.fm.dto.response.JwtTokenResponse;
import com.fm.dto.response.UserRead;
import com.fm.dto.response.UserWrite;
import com.fm.exception.CustomException;
import com.fm.model.User;
import com.fm.repository.UserRepository;
import com.fm.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public JwtTokenResponse signup(UserWrite request) {
        if (!userRepository.existsByUsername(request.getUsername())) {
            User user = UserMapper.toEntity(request);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
            String token = jwtTokenProvider.createToken(user.getUsername(), user.getAuthorities());
            return new JwtTokenResponse(token);
        } else {
            throw new CustomException("Username is already in use", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @Override
    public UserRead search(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new CustomException("The user doesn't exist", HttpStatus.NOT_FOUND);
        }
        return UserMapper.toDto(user);
    }

    @Override
    public UserRead whoami(HttpServletRequest request) {
        String username = jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(request));
        User user = userRepository.findByUsername(username);
        return UserMapper.toDto(user);
    }

    @Override
    public List<UserRead> findAll() {
        return UserMapper.toDto(userRepository.findAll());
    }

    @Override
    public UserRead findById(Long id) {
        return userRepository.findById(id)
            .map(UserMapper::toDto)
            .orElseThrow(() -> new CustomException("The user doesn't exist", HttpStatus.NOT_FOUND));
    }

    @Override
    public UserRead update(Long id, UserWrite request) {
        // TODO: implement this
        return null;
    }

    @Override
    public void deactivate(Long id) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new CustomException("The user doesn't exist", HttpStatus.NOT_FOUND));
        user.setActive(false);
        userRepository.update(id, user);
    }

    @Override
    public JwtTokenResponse login(String username, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            User user = userRepository.findByUsername(username);
            String token = jwtTokenProvider.createToken(user.getUsername(), user.getAuthorities());
            return new JwtTokenResponse(token);
        } catch (AuthenticationException e) {
            throw new CustomException("Invalid username/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @Override
    public JwtTokenResponse refresh(String username) {
        User user = userRepository.findByUsername(username);
        String token = jwtTokenProvider.createToken(user.getUsername(), user.getAuthorities());
        return new JwtTokenResponse(token);
    }
}
