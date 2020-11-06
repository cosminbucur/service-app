package com.fm.dto.mapper;

import com.fm.dto.response.UserRead;
import com.fm.dto.response.UserWrite;
import com.fm.model.User;

import java.util.List;
import java.util.stream.Collectors;

// TODO: future - replace with jmapper
public class UserMapper {

    private UserMapper() {
    }

    public static List<UserRead> toDto(List<User> users) {
        return users.stream().map(UserMapper::toDto)
            .collect(Collectors.toList());
    }

    public static User toEntity(UserWrite dto) {
        User entity = new User();
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setUsername(dto.getUsername());
        entity.setPassword(dto.getPassword());
        entity.setEmail(dto.getEmail());
        entity.setRoles(dto.getRoles());

        return entity;
    }

    public static UserRead toDto(User entity) {
        UserRead dto = new UserRead();
        dto.setId(entity.getId());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setUsername(entity.getUsername());
        dto.setEmail(entity.getEmail());
        dto.setRoles(entity.getRoles());

        return dto;
    }
}
