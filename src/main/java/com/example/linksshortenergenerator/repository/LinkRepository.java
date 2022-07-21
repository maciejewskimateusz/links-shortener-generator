package com.example.linksshortenergenerator.repository;

import com.example.linksshortenergenerator.model.Link;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LinkRepository extends JpaRepository<Link, String> {

    boolean existsById(String id);

}
