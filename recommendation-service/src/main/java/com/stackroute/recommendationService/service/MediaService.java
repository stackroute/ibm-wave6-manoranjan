package com.stackroute.recommendationService.service;

import com.stackroute.recommendationService.domain.EpisodicMedia;
import com.stackroute.recommendationService.domain.Genre;
import com.stackroute.recommendationService.domain.Language;
import com.stackroute.recommendationService.domain.StandaloneMedia;
import com.stackroute.recommendationService.exception.GenreNotFoundException;
import com.stackroute.recommendationService.exception.LanguageNotFoundException;
import com.stackroute.recommendationService.exception.MediaAlreadyExistException;
import com.stackroute.recommendationService.exception.MediaNotFoundException;

import java.util.Collection;
import java.util.List;

public interface MediaService {
    public Collection<StandaloneMedia> getStandaloneMedias() throws MediaNotFoundException;

    public Collection<EpisodicMedia> getEpisodicMedias() throws MediaNotFoundException;

    public List<StandaloneMedia> displayMedia() throws MediaNotFoundException;

    public Collection<Language> getLanguages() throws LanguageNotFoundException;

    public Collection<Genre> getGenres() throws GenreNotFoundException;

    public StandaloneMedia getStandaloneMediaByTitle(String title) throws MediaNotFoundException;

    public EpisodicMedia getEpisodicMediaByTitle(String title) throws MediaNotFoundException;

    public StandaloneMedia saveStandaloneMedia(StandaloneMedia standaloneMedia) throws MediaAlreadyExistException;

    public EpisodicMedia saveEpisodicMedia(EpisodicMedia episodicMedia) throws MediaAlreadyExistException;
}