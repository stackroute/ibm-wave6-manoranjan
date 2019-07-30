package com.stackroute.recommendationService.domain;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import java.util.List;

@NodeEntity
public class Viewer {

    @GraphId
    private Long id;
    private String name;
    private String emailId;
    private List<String> genre;

    public Viewer() {
    }

    public Viewer(Long id, String name, String emailId, List<String> genre, List<StandaloneMedia> standaloneMedia) {
        this.id = id;
        this.name = name;
        this.emailId = emailId;
        this.genre = genre;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public List<String> getGenre() {
        return genre;
    }

    public void setGenre(List<String> genre) {
        this.genre = genre;
    }
}
