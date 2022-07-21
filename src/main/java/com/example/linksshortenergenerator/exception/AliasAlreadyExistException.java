package com.example.linksshortenergenerator.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Alias already exists")
public class AliasAlreadyExistException extends RuntimeException {
}
