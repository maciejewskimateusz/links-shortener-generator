package com.example.linksshortenergenerator.repository.link;

import com.example.linksshortenergenerator.model.link.Link;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface LinkRepository extends JpaRepository<Link, String> {

    boolean existsById(String id);

    @Modifying
    @Query("delete from Link l where l.createdAt <= :time")
    void deleteOldLinks(@Param("time") LocalDateTime time);
}
