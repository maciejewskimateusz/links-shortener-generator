package com.example.linksshortenergenerator.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Link {

    @Id
    private String id;
    private String url;
    private LocalDateTime createdAt;
}
