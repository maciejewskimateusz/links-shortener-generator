package com.example.linksshortenergenerator.controller.link;

import com.example.linksshortenergenerator.dto.link.LinkCreateDto;
import com.example.linksshortenergenerator.dto.link.LinkResponseDto;
import com.example.linksshortenergenerator.service.LinkService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@AllArgsConstructor
@RestController
@RequestMapping("/api/links")
public class LinkController {

    private final LinkService linkService;

//    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @PostMapping
    ResponseEntity<LinkResponseDto> shortenLink(@RequestBody LinkCreateDto link) {
        LinkResponseDto shortenLink = linkService.shortenLink(link);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(shortenLink.getId())
                .toUri();

        return ResponseEntity.created(uri).body(shortenLink);
    }

//    @Secured({"ROLE_ADMIN", "ROLE_USER"})
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

//    @Secured("ROLE_ADMIN")
    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteLink(@PathVariable String id) {
        linkService.deleteLink(id);
        return ResponseEntity.noContent().build();
    }
}
