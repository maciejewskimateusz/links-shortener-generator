package com.example.linksshortenergenerator.mapper;

import com.example.linksshortenergenerator.dto.LinkResponseDto;
import com.example.linksshortenergenerator.model.Link;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Component
public class LinkResponseMapper {

    public LinkResponseDto map(Link link) {

        String shortenLink = shortenLink(link.getId());
        return new LinkResponseDto(link.getId(), link.getUrl(), shortenLink, link.getCreatedAt());
    }

    private String shortenLink(String id) {
        return ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/api/links/{id}")
                .buildAndExpand(id)
                .toUriString();
    }
}
