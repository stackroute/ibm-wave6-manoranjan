package com.stackroute.recommendationService.domain;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity
public class Language {

    @GraphId
    private Long id;
    private String language;

    public Language() {
    }

    public Language(Long id, String language, Media media) {
        this.id = id;
        this.language = language;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
