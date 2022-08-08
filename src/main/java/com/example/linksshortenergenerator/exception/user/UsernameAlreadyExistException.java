package com.example.linksshortenergenerator.exception.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Username already exists")
public class UsernameAlreadyExistException extends RuntimeException {
}
