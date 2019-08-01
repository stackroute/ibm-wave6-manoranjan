package com.stackroute.recommendationService.service;

import com.stackroute.recommendationService.domain.*;
import com.stackroute.recommendationService.exception.GenreNotFoundException;
import com.stackroute.recommendationService.exception.LanguageNotFoundException;
import com.stackroute.recommendationService.exception.MediaAlreadyExistException;
import com.stackroute.recommendationService.exception.MediaNotFoundException;
import java.util.Collection;

public interface MediaService {
    public Collection<Documentary> getDocumentary() throws MediaNotFoundException;
    public Collection<Movie> getMovie() throws MediaNotFoundException;
    public Collection<TvEpisodes> getTvEpisodes() throws MediaNotFoundException;
    public Collection<WebSeries> getWebSeries() throws MediaNotFoundException;

    public Collection<Language> getLanguages() throws LanguageNotFoundException;
    public Collection<Genre> getGenres() throws GenreNotFoundException;

    public Documentary getDocumentaryByTitle(String title) throws MediaNotFoundException;
    public Movie getMovieByTitle(String title) throws MediaNotFoundException;
    public TvEpisodes getTvEpisodesByTitle(String title) throws MediaNotFoundException;
    public WebSeries getWebSeriesByTitle(String title) throws MediaNotFoundException;

    public Documentary saveDocumentary(Documentary documentary) throws MediaAlreadyExistException;
    public Movie saveMovie(Movie movie) throws MediaAlreadyExistException;
    public TvEpisodes saveTvEpisodes(TvEpisodes tvEpisodes) throws MediaAlreadyExistException;
    public WebSeries saveWebSeries(WebSeries webSeries) throws MediaAlreadyExistException;
}