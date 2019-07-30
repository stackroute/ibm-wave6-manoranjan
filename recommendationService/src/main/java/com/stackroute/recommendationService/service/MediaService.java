package com.stackroute.recommendationService.service;

import com.stackroute.recommendationService.domain.Genre;
import com.stackroute.recommendationService.domain.Language;
import com.stackroute.recommendationService.domain.Media;
import com.stackroute.recommendationService.exception.GenreNotFoundException;
import com.stackroute.recommendationService.exception.LanguageNotFoundException;
import com.stackroute.recommendationService.exception.MediaAlreadyExistException;
import com.stackroute.recommendationService.exception.MediaNotFoundException;

import java.util.Collection;
import java.util.List;

public interface MediaService {
    public Collection<Media> getMedias()throws MediaNotFoundException;
    public List<Media> displayMedia() throws MediaNotFoundException;
    public Collection<Language> getLanguages()throws LanguageNotFoundException;
    public Collection<Genre> getGenres()throws GenreNotFoundException;
    public Media getMediaByTitle(String title)throws MediaNotFoundException;
    public Media saveMedia(Media media)throws MediaAlreadyExistException;
}