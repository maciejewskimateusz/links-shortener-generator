package com.example.linksshortenergenerator.dto.link;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LinkCreateDto {

    private String url;
    private String alias;
}
