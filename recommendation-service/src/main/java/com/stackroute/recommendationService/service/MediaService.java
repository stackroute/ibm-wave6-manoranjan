package com.stackroute.recommendationService.service;

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
    public Collection<StandaloneMedia> getMedias() throws MediaNotFoundException;

    public List<StandaloneMedia> displayMedia() throws MediaNotFoundException;

    public Collection<Language> getLanguages() throws LanguageNotFoundException;

    public Collection<Genre> getGenres() throws GenreNotFoundException;

    public StandaloneMedia getMediaByTitle(String title) throws MediaNotFoundException;

    public StandaloneMedia saveMedia(StandaloneMedia standaloneMedia) throws MediaAlreadyExistException;
}