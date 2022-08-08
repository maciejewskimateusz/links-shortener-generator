package com.example.linksshortenergenerator.dto.user;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class UserCredentialsDto {
    private String username;
    private String password;
    private Set<String> roles;

    public UserCredentialsDto(String username, String password, Set<String> roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }
}
