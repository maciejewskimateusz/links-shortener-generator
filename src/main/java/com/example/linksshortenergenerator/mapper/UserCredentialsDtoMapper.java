package com.example.linksshortenergenerator.mapper;

import com.example.linksshortenergenerator.dto.user.UserCredentialsDto;
import com.example.linksshortenergenerator.model.user.User;
import com.example.linksshortenergenerator.model.user.UserRole;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class UserCredentialsDtoMapper {


    public static UserCredentialsDto toDto(User user) {
        return new UserCredentialsDto(user.getUsername(), user.getPassword(), getUserRoles(user));
    }

    private static Set<String> getUserRoles(final User user) {
        return user.getRoles().stream().map(UserRole::getName).collect(Collectors.toSet());
    }
}
