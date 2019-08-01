package com.stackroute.recommendationService.service;

import com.stackroute.recommendationService.domain.*;
import com.stackroute.recommendationService.exception.GenreNotFoundException;
import com.stackroute.recommendationService.exception.LanguageNotFoundException;
import com.stackroute.recommendationService.exception.MediaAlreadyExistException;
import com.stackroute.recommendationService.exception.MediaNotFoundException;
import com.stackroute.recommendationService.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;

@Service
public class MediaServiceImpl implements MediaService {

    @Autowired
    private DocumentaryRepository documentaryRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private TvEpisodesRepository tvEpisodesRepository;

    @Autowired
    private WebSeriesRepository webSeriesRepository;

    @Autowired
    private LanguageRepository languageRepository;

    @Autowired
    private GenreRepository genreRepository;

    //method to get all documentaries
    public Collection<Documentary> getDocumentary() throws MediaNotFoundException {
        if (documentaryRepository.getAllDocumentary() == null) {
            throw new MediaNotFoundException();
        }
        else {
            return documentaryRepository.getAllDocumentary();
        }
    }

    //method to get all movies
    public Collection<Movie> getMovie() throws MediaNotFoundException {
        if (movieRepository.getAllMovie() == null) {
            throw new MediaNotFoundException();
        }
        else {
            return movieRepository.getAllMovie();
        }
    }

    //method to get all Tv Episodes
    public Collection<TvEpisodes> getTvEpisodes() throws MediaNotFoundException {
        if (tvEpisodesRepository.getAllTvEpisodes() == null) {
            throw new MediaNotFoundException();
        }
        else {
            return tvEpisodesRepository.getAllTvEpisodes();
        }
    }

    //method to get all Web Series
    public Collection<WebSeries> getWebSeries() throws MediaNotFoundException {
        if (webSeriesRepository.getAllWebSeries() == null) {
            throw new MediaNotFoundException();
        }
        else {
            return webSeriesRepository.getAllWebSeries();
        }
    }

    //method to get all languages
    public Collection<Language> getLanguages() throws LanguageNotFoundException {
        if (languageRepository.getAllLanguages() == null) {
            throw new LanguageNotFoundException();
        } else {
            return languageRepository.getAllLanguages();
        }
    }

    //method to get all genres
    public Collection<Genre> getGenres() throws GenreNotFoundException {
        if (genreRepository.getAllGenres() == null) {
            throw new GenreNotFoundException();
        } else {
            return genreRepository.getAllGenres();
        }
    }

    //method to get documentary by title
    public Documentary getDocumentaryByTitle(String title) throws MediaNotFoundException {
        if (documentaryRepository.findDocumentaryByTitle(title) == null) {
            throw new MediaNotFoundException();
        } else {
            return documentaryRepository.findDocumentaryByTitle(title);
        }
    }

    //method to get movie by title
    public Movie getMovieByTitle(String title) throws MediaNotFoundException {
        if (movieRepository.findMovieByTitle(title) == null) {
            throw new MediaNotFoundException();
        } else {
            return movieRepository.findMovieByTitle(title);
        }
    }

    //method to get Tv Episodes by title
    public TvEpisodes getTvEpisodesByTitle(String title) throws MediaNotFoundException {
        if (tvEpisodesRepository.findTvEpisodeByTitle(title) == null) {
            throw new MediaNotFoundException();
        } else {
            return tvEpisodesRepository.findTvEpisodeByTitle(title);
        }
    }

    //method to get Web Series by title
    public WebSeries getWebSeriesByTitle(String title) throws MediaNotFoundException {
        if (webSeriesRepository.findWebSeriesByTitle(title) == null) {
            throw new MediaNotFoundException();
        } else {
            return webSeriesRepository.findWebSeriesByTitle(title);
        }
    }

    //method to save Documentary
    public Documentary saveDocumentary(Documentary documentary) throws MediaAlreadyExistException{
        if (documentaryRepository.findDocumentaryByTitle(documentary.getTitle()) == null)
        {
            documentaryRepository.createDocumentaryNode(documentary.getTitle());
            documentaryRepository.createLanguageRelation(documentary.getTitle(), documentary.getMediaLanguage());
            documentaryRepository.createCategoryRelation(documentary.getTitle(), documentary.getMediaCategory());

            for (int i = 0; i < documentary.getMediaGenre().size(); i++) {
                System.out.println(documentary.getMediaGenre().get(i));
                documentaryRepository.createGenreRelation(documentary.getTitle(), documentary.getMediaGenre().get(i));
            }
        }
        else
        {
            throw new MediaAlreadyExistException();
        }
      return documentary;
    }

    //method to save movie
    public Movie saveMovie(Movie movie) throws MediaAlreadyExistException{
        if (movieRepository.findMovieByTitle(movie.getTitle()) == null) {
            movieRepository.createMovieNode(movie.getTitle());
            movieRepository.createLanguageRelation(movie.getTitle(), movie.getMediaLanguage());
            movieRepository.createCategoryRelation(movie.getTitle(), movie.getMediaCategory());

            for (int i = 0; i < movie.getMediaGenre().size(); i++) {
                movieRepository.createGenreRelation(movie.getTitle(), movie.getMediaGenre().get(i));
            }
        }
        else
        {
            throw new MediaAlreadyExistException();
        }
       return movie;
    }

    //method to save tv episodes
    public TvEpisodes saveTvEpisodes(TvEpisodes tvEpisodes) throws MediaAlreadyExistException{
        if (tvEpisodesRepository.findTvEpisodeByTitle(tvEpisodes.getTitle()) == null) {

            tvEpisodesRepository.createTvEpisodesNode(tvEpisodes.getTitle());
            tvEpisodesRepository.createLanguageRelation (tvEpisodes.getTitle(), tvEpisodes.getEpisodeLanguage());
            tvEpisodesRepository.createCategoryRelation (tvEpisodes.getTitle(), tvEpisodes.getEpisodeCategory());
        }
        else
        {
            throw new MediaAlreadyExistException();
        }
        return tvEpisodes;
    }

    //method to save web series
    public WebSeries saveWebSeries(WebSeries webSeries) throws MediaAlreadyExistException{
        if (webSeriesRepository.findWebSeriesByTitle(webSeries.getTitle()) == null) {

            webSeriesRepository.createWebSeriesNode(webSeries.getTitle());
            webSeriesRepository.createLanguageRelation(webSeries.getTitle(), webSeries.getEpisodeLanguage());
            webSeriesRepository.createCategoryRelation(webSeries.getTitle(), webSeries.getEpisodeCategory());
        }
        else
        {
            throw new MediaAlreadyExistException();
        }
        return webSeries;
    }
}
