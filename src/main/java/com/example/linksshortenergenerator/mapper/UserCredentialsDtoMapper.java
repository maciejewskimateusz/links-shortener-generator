package com.example.linksshortenergenerator.mapper;

import com.example.linksshortenergenerator.dto.user.UserCredentialsDto;
import com.example.linksshortenergenerator.model.user.User;
import com.example.linksshortenergenerator.model.user.UserRole;
import lombok.AllArgsConstructor;

import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
public class UserCredentialsDtoMapper {


    public static UserCredentialsDto map(User user) {
        String username = user.getUsername();
        String password = user.getPassword();
        Set<String> roles = user.getRoles().stream()
                .map(UserRole::getName)
                .collect(Collectors.toSet());
        return new UserCredentialsDto(username, password, roles);
    }
}