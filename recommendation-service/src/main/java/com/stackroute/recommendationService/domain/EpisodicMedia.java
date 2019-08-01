package com.stackroute.recommendationService.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EpisodicMedia {

    @Id
    private String episodeTitle;
    private String episodeCategory;
    private String episodeLanguage;
    private String episodeType;
}