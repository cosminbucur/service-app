package com.fm.repository;

import com.fm.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class UserH2Repository implements UserRepository {

    private static Map<Long, User> db = new HashMap<>();

    @Override
    public boolean existsByUsername(String username) {
        Optional<User> optionalUser = db.values().stream()
            .filter(user -> user.getUsername().equals(username)).findFirst();
        return optionalUser.isPresent();
    }

    @Override
    public User findByUsername(String username) {
        return db.values().stream()
            .filter(user -> user.getUsername().equals(username))
            .findFirst()
            .orElse(null);
    }

    @Override
    public void deleteByUsername(String username) {
        // https://www.baeldung.com/java-map-key-from-value

        Long idToDelete = db.entrySet()
            .stream()
            .filter(entry -> username.equals(entry.getValue().getUsername()))
            .map(Map.Entry::getKey)
            .findFirst()
            .orElse(null);

        db.remove(idToDelete);
    }

    @Override
    public User save(User user) {
        long nextId = db.size() + 1L;
        user.setId(nextId);
        db.put(nextId, user);
        return db.get(nextId);
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(db.values());
    }

    @Override
    public Optional<User> findById(Long id) {
        User user = db.get(id);
        return Optional.ofNullable(user);
    }

    @Override
    public User update(Long id, User user) {
        db.put(id, user);
        return db.get(id);
    }

    @Override
    public void delete(Long id) {
        db.remove(id);
    }
}
