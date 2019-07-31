package com.stackroute.recommendationService.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@NodeEntity
public class StandaloneMedia {

    @GraphId
    private Long id;
    private String title;
    private List<String> genre;
    private String language;

    public String updateGenre(int i, String genreName)
    {
        genre.set(i, genreName);
        return String.valueOf(genre);
    }
}
