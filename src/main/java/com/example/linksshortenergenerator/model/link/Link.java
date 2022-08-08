package com.example.linksshortenergenerator.model.link;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
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
    private String createdBy;
}
