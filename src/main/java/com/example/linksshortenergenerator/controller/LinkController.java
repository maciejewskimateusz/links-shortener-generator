package com.example.linksshortenergenerator.controller;

import com.example.linksshortenergenerator.service.LinkService;
import com.example.linksshortenergenerator.dto.LinkCreateDto;
import com.example.linksshortenergenerator.dto.LinkResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@AllArgsConstructor
@RestController
@RequestMapping("/api/links")
public class LinkController {

    private final LinkService linkService;

    @PostMapping
    ResponseEntity<LinkResponseDto> shortenLink(@RequestBody LinkCreateDto link) {
        LinkResponseDto shortenLink = linkService.shortenLink(link);
        return ResponseEntity.ok(shortenLink);
    }

    @GetMapping("/{id}")
    ResponseEntity<?> redirect(@PathVariable String id) {
        return linkService.findLink(id)
                .map(LinkResponseDto::getUrl)
                .map(link -> ResponseEntity
                        .status(HttpStatus.FOUND)
                        .location(URI.create(link))
                        .build())
                .orElse(ResponseEntity.notFound().build());

    }
}
