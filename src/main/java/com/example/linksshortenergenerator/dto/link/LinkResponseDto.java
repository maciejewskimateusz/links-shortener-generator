package com.example.linksshortenergenerator.dto.link;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class LinkResponseDto {

    private String id;
    private String url;
    private String shortLink;
    private LocalDateTime createdAt;
}
