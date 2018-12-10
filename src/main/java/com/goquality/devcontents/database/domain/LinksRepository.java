package com.goquality.devcontents.database.domain;

import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface LinksRepository extends JpaRepository<Links, Long> {

    @Query("SELECT l " +
            "FROM Links l " +
            "ORDER BY l.id DESC ")
    Stream<Links> findAllDesc();

}
