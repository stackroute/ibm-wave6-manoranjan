package com.stackroute.recommendationService.domain;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import java.util.List;

@NodeEntity
public class Media {

    @GraphId
    private Long id;
    private String title;
    private List<String> genre;
    private String language;

    public Media() {
    }

    public Media(Long id, String title, List<String> genre, String language) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.language = language;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getGenre() {
        return genre;
    }

    public void setGenre(List<String> genre) {
        this.genre = genre;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
