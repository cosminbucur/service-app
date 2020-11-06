package com.fm.repository;

import com.fm.model.User;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface UserRepository {

    boolean existsByUsername(String username);

    User findByUsername(String username);

    @Transactional
    void deleteByUsername(String username);

    // TODO: might not need these

    User save(User user);

    List<User> findAll();

    Optional<User> findById(Long id);

    User update(Long id, User user);

    void delete(Long id);
}
