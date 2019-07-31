package com.stackroute.recommendationService.domain;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import java.util.List;

@NodeEntity
public class Genre {

    @GraphId
    private Long id;
    private String genre;

    public Genre() {
    }

    public Genre(Long id, String genre, List<Viewer> viewer, List<StandaloneMedia> standaloneMedia) {
        this.id = id;
        this.genre = genre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
