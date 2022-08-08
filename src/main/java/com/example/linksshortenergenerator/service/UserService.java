package com.example.linksshortenergenerator.service;

import com.example.linksshortenergenerator.dto.user.UserCredentialsDto;
import com.example.linksshortenergenerator.dto.user.UserRegistrationDto;
import com.example.linksshortenergenerator.exception.user.UsernameAlreadyExistException;
import com.example.linksshortenergenerator.mapper.UserCredentialsDtoMapper;
import com.example.linksshortenergenerator.model.user.User;
import com.example.linksshortenergenerator.repository.user.UserRepository;
import com.example.linksshortenergenerator.repository.user.UserRoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private UserRoleRepository userRoleRepository;


    public Optional<UserCredentialsDto> findByUsername(String username) {
        return userRepository.findByUsername(username)
                .map(UserCredentialsDtoMapper::map);
    }

    @Transactional
    public void register(UserRegistrationDto registrationDto) {
        User user = new User();
        userRepository.findByUsername(registrationDto.getUsername())
                        .ifPresent(u -> {
                            throw new UsernameAlreadyExistException();
                        });
        user.setUsername(registrationDto.getUsername());
        user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
        userRoleRepository.findByName("USER")
                .ifPresentOrElse(role -> user.getRoles().add(role),
                        () -> {
                            throw new NoSuchElementException();
                        }
                );
        userRepository.save(user);
    }
}
