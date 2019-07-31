package com.stackroute.recommendationService.service;

import com.stackroute.recommendationService.domain.Genre;
import com.stackroute.recommendationService.domain.Language;
import com.stackroute.recommendationService.domain.StandaloneMedia;
import com.stackroute.recommendationService.exception.GenreNotFoundException;
import com.stackroute.recommendationService.exception.LanguageNotFoundException;
import com.stackroute.recommendationService.exception.MediaAlreadyExistException;
import com.stackroute.recommendationService.exception.MediaNotFoundException;
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
    private LanguageRepository languageRepository;

    @Autowired
    private GenreRepository genreRepository;

    public Collection<StandaloneMedia> getMedias()throws MediaNotFoundException {
        if (standaloneMediaRepository.getAllMedias()==null){
            throw new MediaNotFoundException();
        }
        else {
            return standaloneMediaRepository.getAllMedias();
        }
    }

    public List<StandaloneMedia> displayMedia() throws MediaNotFoundException {
        if (standaloneMediaRepository.findAll()==null)
        {
            throw new MediaNotFoundException();
        }
        else {
            return (List<StandaloneMedia>) standaloneMediaRepository.findAll();
        }
    }

    public Collection<Language> getLanguages()throws LanguageNotFoundException {
        if (languageRepository.getAllLanguages()==null){
            throw new LanguageNotFoundException();
        }
        else {
            return languageRepository.getAllLanguages();
        }
    }

    public Collection<Genre> getGenres()throws GenreNotFoundException {
        if (genreRepository.getAllGenres()==null){
            throw new GenreNotFoundException();
        }
        else {
            return genreRepository.getAllGenres();
        }
    }

    public StandaloneMedia getMediaByTitle(String title)throws MediaNotFoundException{
        if (standaloneMediaRepository.getByTitle(title)==null){
            throw new MediaNotFoundException();
        }
        else {
            return standaloneMediaRepository.getByTitle(title);
        }
    }

    public StandaloneMedia saveMedia(StandaloneMedia standaloneMedia)throws MediaAlreadyExistException {
        if (standaloneMediaRepository.getByTitle(standaloneMedia.getTitle())==null)
        {
            int length = standaloneMedia.getGenre().size();
            for (int i = 0; i < length; i++)
            {
                if (languageRepository.findLanguageByName(standaloneMedia.getLanguage()) == null)
                {
                    if (genreRepository.findGenreByName(standaloneMedia.getGenre().get(i)) == null)
                    {
                        standaloneMediaRepository.saveNewMediaLanguage(standaloneMedia.getTitle(), standaloneMedia.getGenre().get(i), standaloneMedia.getLanguage());
                        standaloneMediaRepository.createGenreNode(standaloneMedia.getGenre().get(i));
                        standaloneMediaRepository.createGenreRelation(standaloneMedia.getTitle(), standaloneMedia.getGenre().get(i));
                    }
                    else {
                        standaloneMediaRepository.saveNewMediaLanguage(standaloneMedia.getTitle(), standaloneMedia.getGenre().get(i), standaloneMedia.getLanguage());
                        standaloneMediaRepository.createGenreRelation(standaloneMedia.getTitle(), standaloneMedia.getGenre().get(i));
                    }
                }
                else if (genreRepository.findGenreByName(standaloneMedia.getGenre().get(i)) == null)
                {
                    if (standaloneMediaRepository.getByTitle(standaloneMedia.getTitle()) == null)
                    {
                        standaloneMediaRepository.createMediaNode(standaloneMedia.getTitle(), standaloneMedia.getGenre().get(i), standaloneMedia.getLanguage());
                        standaloneMediaRepository.createLanguageRelation(standaloneMedia.getTitle(), standaloneMedia.getLanguage());
                        standaloneMediaRepository.createGenreNode(standaloneMedia.getGenre().get(i));
                        standaloneMediaRepository.createGenreRelation(standaloneMedia.getTitle(), standaloneMedia.getGenre().get(i));
                    }
                    else {
                        standaloneMediaRepository.createGenreNode(standaloneMedia.getGenre().get(i));
                        standaloneMediaRepository.createGenreRelation(standaloneMedia.getTitle(), standaloneMedia.getGenre().get(i));
                    }
                }
                else {
                    if (standaloneMediaRepository.getByTitle(standaloneMedia.getTitle()) == null)
                    {
                        standaloneMediaRepository.createMediaNode(standaloneMedia.getTitle(), standaloneMedia.getGenre().get(i), standaloneMedia.getLanguage());
                        standaloneMediaRepository.createLanguageRelation(standaloneMedia.getTitle(), standaloneMedia.getLanguage());
                        standaloneMediaRepository.createGenreRelation(standaloneMedia.getTitle(), standaloneMedia.getGenre().get(i));
                    }
                    else {
                        standaloneMediaRepository.createGenreRelation(standaloneMedia.getTitle(), standaloneMedia.getGenre().get(i));
                    }
                }
            }
        }
        else{
            throw new MediaAlreadyExistException();
        }
        return standaloneMedia;
    }
}
