package com.stackroute.domain;

import lombok.*;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class EpisodicMedia {

    @Id
    private String episodeTitle;
    private String episodeCategory;
    private String episodeLanguage;
    private String episodeType;
}