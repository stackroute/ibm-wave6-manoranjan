package com.stackroute.recommendationService.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@NodeEntity
public class TvEpisodes {

    @GraphId
    private Long id;
    private String episodeTitle;
    private String episodeCategory;
    private String episodeLanguage;
}
