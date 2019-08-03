package com.stackroute.episodicmediaservice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EpisodicMedia {

    @Id
    private String episodicTitle;
    private String episodicCategory;
    private String episodicSynopsis;
    private String episodicLanguage;
    private String episodicPosterUrl;
    private String episodicStudioName;
    private List<Crew> episodicCrew;
    private List<Cast> episodicCast;
    private List<Episode> episodeList;
    private String episodicType;
}
