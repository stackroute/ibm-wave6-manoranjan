package com.stackroute.recommendationService.service;

import com.stackroute.recommendationService.domain.EpisodicMedia;
import com.stackroute.recommendationService.domain.Genre;
import com.stackroute.recommendationService.domain.Language;
import com.stackroute.recommendationService.domain.StandaloneMedia;
import com.stackroute.recommendationService.exception.GenreNotFoundException;
import com.stackroute.recommendationService.exception.LanguageNotFoundException;
import com.stackroute.recommendationService.exception.MediaAlreadyExistException;
import com.stackroute.recommendationService.exception.MediaNotFoundException;
import com.stackroute.recommendationService.repository.EpisodicMediaRepository;
import com.stackroute.recommendationService.repository.GenreRepository;
import com.stackroute.recommendationService.repository.LanguageRepository;
import com.stackroute.recommendationService.repository.StandaloneMediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class MediaServiceImpl implements MediaService {

    @Autowired
    private StandaloneMediaRepository standaloneMediaRepository;

    @Autowired
    private EpisodicMediaRepository episodicMediaRepository;

    @Autowired
    private LanguageRepository languageRepository;

    @Autowired
    private GenreRepository genreRepository;

    //getting all standalone media's
    public Collection<StandaloneMedia> getStandaloneMedias() throws MediaNotFoundException {
        if (standaloneMediaRepository.getAllStandaloneMedias() == null) {
            throw new MediaNotFoundException();
        } else {
            return standaloneMediaRepository.getAllStandaloneMedias();
        }
    }

    //getting all episodic media's
    public Collection<EpisodicMedia> getEpisodicMedias() throws MediaNotFoundException {
        if (episodicMediaRepository.getAllEpisodicMedias() == null) {
            throw new MediaNotFoundException();
        } else {
            return episodicMediaRepository.getAllEpisodicMedias();
        }
    }


    public List<StandaloneMedia> displayMedia() throws MediaNotFoundException {
        if (standaloneMediaRepository.findAll() == null) {
            throw new MediaNotFoundException();
        } else {
            return (List<StandaloneMedia>) standaloneMediaRepository.findAll();
        }
    }

    public Collection<Language> getLanguages() throws LanguageNotFoundException {
        if (languageRepository.getAllLanguages() == null) {
            throw new LanguageNotFoundException();
        } else {
            return languageRepository.getAllLanguages();
        }
    }

    public Collection<Genre> getGenres() throws GenreNotFoundException {
        if (genreRepository.getAllGenres() == null) {
            throw new GenreNotFoundException();
        } else {
            return genreRepository.getAllGenres();
        }
    }

    public StandaloneMedia getStandaloneMediaByTitle(String title) throws MediaNotFoundException {
        if (standaloneMediaRepository.getStandaloneMediaByTitle(title) == null) {
            throw new MediaNotFoundException();
        } else {
            return standaloneMediaRepository.getStandaloneMediaByTitle(title);
        }
    }

    public EpisodicMedia getEpisodicMediaByTitle(String title) throws MediaNotFoundException {
        if (episodicMediaRepository.findByTitle(title) == null) {
            throw new MediaNotFoundException();
        } else {
            return episodicMediaRepository.findByTitle(title);
        }
    }

    public StandaloneMedia saveStandaloneMedia(StandaloneMedia standaloneMedia) throws MediaAlreadyExistException {
        if (standaloneMediaRepository.getStandaloneMediaByTitle(standaloneMedia.getTitle()) == null) {
            int length = standaloneMedia.getGenre().size();
            for (int i = 0; i < length; i++) {
                if (languageRepository.findLanguageByName(standaloneMedia.getLanguage()) == null) {
                    if (genreRepository.findGenreByName(standaloneMedia.getGenre().get(i)) == null) {
                        standaloneMediaRepository.saveNewStandaloneMediaLanguage(standaloneMedia.getTitle(), standaloneMedia.updateGenre(i, standaloneMedia.getGenre().get(i)), standaloneMedia.getLanguage());
                        standaloneMediaRepository.createGenreNode(standaloneMedia.getGenre().get(i));
                        standaloneMediaRepository.createGenreRelation(standaloneMedia.getTitle(), standaloneMedia.getGenre().get(i));
                    } else {
                        standaloneMediaRepository.saveNewStandaloneMediaLanguage(standaloneMedia.getTitle(), standaloneMedia.updateGenre(i, standaloneMedia.getGenre().get(i)), standaloneMedia.getLanguage());
                        standaloneMediaRepository.createGenreRelation(standaloneMedia.getTitle(), standaloneMedia.getGenre().get(i));
                    }
                } else if (genreRepository.findGenreByName(standaloneMedia.getGenre().get(i)) == null) {
                    if (standaloneMediaRepository.getStandaloneMediaByTitle(standaloneMedia.getTitle()) == null) {
                        standaloneMediaRepository.createStandaloneMediaNode(standaloneMedia.getTitle(), standaloneMedia.updateGenre(i, standaloneMedia.getGenre().get(i)), standaloneMedia.getLanguage());
                        standaloneMediaRepository.createLanguageRelation(standaloneMedia.getTitle(), standaloneMedia.getLanguage());
                        standaloneMediaRepository.createGenreNode(standaloneMedia.getGenre().get(i));
                        standaloneMediaRepository.createGenreRelation(standaloneMedia.getTitle(), standaloneMedia.getGenre().get(i));
                    } else {
                        standaloneMediaRepository.createGenreNode(standaloneMedia.getGenre().get(i));
                        standaloneMediaRepository.createGenreRelation(standaloneMedia.getTitle(), standaloneMedia.getGenre().get(i));
                    }
                } else {
                    if (standaloneMediaRepository.getStandaloneMediaByTitle(standaloneMedia.getTitle()) == null) {
                        standaloneMediaRepository.createStandaloneMediaNode(standaloneMedia.getTitle(), standaloneMedia.updateGenre(i, standaloneMedia.getGenre().get(i)), standaloneMedia.getLanguage());
                        standaloneMediaRepository.createLanguageRelation(standaloneMedia.getTitle(), standaloneMedia.getLanguage());
                        standaloneMediaRepository.createGenreRelation(standaloneMedia.getTitle(), standaloneMedia.getGenre().get(i));
                    } else {
                        standaloneMediaRepository.createGenreRelation(standaloneMedia.getTitle(), standaloneMedia.getGenre().get(i));
                    }
                }
            }
        } else {
            throw new MediaAlreadyExistException();
        }
        return standaloneMedia;
    }

    public EpisodicMedia saveEpisodicMedia(EpisodicMedia episodicMedia) throws MediaAlreadyExistException {
        if (episodicMediaRepository.findByTitle(episodicMedia.getTitle()) == null) {
            if (languageRepository.findLanguageByName(episodicMedia.getLanguage()) == null) {
                episodicMediaRepository.saveNewEpisodicMediaLanguage(episodicMedia.getTitle(), episodicMedia.getLanguage());
            } else {
                episodicMediaRepository.createEpisodicMediaNode(episodicMedia.getTitle(), episodicMedia.getLanguage());
                episodicMediaRepository.createLanguageRelation(episodicMedia.getTitle(), episodicMedia.getLanguage());
            }
        } else {
            throw new MediaAlreadyExistException();
        }
        return episodicMedia;
    }
}
