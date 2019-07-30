package com.stackroute.recommendationService.service;

import com.stackroute.recommendationService.domain.Genre;
import com.stackroute.recommendationService.domain.Language;
import com.stackroute.recommendationService.domain.Media;
import com.stackroute.recommendationService.exception.GenreNotFoundException;
import com.stackroute.recommendationService.exception.LanguageNotFoundException;
import com.stackroute.recommendationService.exception.MediaAlreadyExistException;
import com.stackroute.recommendationService.exception.MediaNotFoundException;
import com.stackroute.recommendationService.repository.GenreRepository;
import com.stackroute.recommendationService.repository.LanguageRepository;
import com.stackroute.recommendationService.repository.MediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.List;

@Service
public class MediaServiceImpl implements MediaService {

    @Autowired
    private MediaRepository mediaRepository;

    @Autowired
    private LanguageRepository languageRepository;

    @Autowired
    private GenreRepository genreRepository;

    public Collection<Media> getMedias()throws MediaNotFoundException {
        if (mediaRepository.getAllMedias()==null){
            throw new MediaNotFoundException();
        }
        else {
            return mediaRepository.getAllMedias();
        }
    }

    public List<Media> displayMedia() throws MediaNotFoundException {
        if (mediaRepository.findAll()==null)
        {
            throw new MediaNotFoundException();
        }
        else {
            return (List<Media>) mediaRepository.findAll();
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

    public Media getMediaByTitle(String title)throws MediaNotFoundException{
        if (mediaRepository.getByTitle(title)==null){
            throw new MediaNotFoundException();
        }
        else {
            return mediaRepository.getByTitle(title);
        }
    }

    public Media saveMedia(Media media)throws MediaAlreadyExistException {
        if (mediaRepository.getByTitle(media.getTitle())==null)
        {
            int length = media.getGenre().size();
            for (int i = 0; i < length; i++)
            {
                if (languageRepository.findLanguageByName(media.getLanguage()) == null)
                {
                    if (genreRepository.findGenreByName(media.getGenre().get(i)) == null)
                    {
                        mediaRepository.saveNewMediaLanguage(media.getTitle(), media.getGenre().get(i), media.getLanguage());
                        mediaRepository.createGenreNode(media.getGenre().get(i));
                        mediaRepository.createGenreRelation(media.getTitle(), media.getGenre().get(i));
                    }
                    else {
                        mediaRepository.saveNewMediaLanguage(media.getTitle(), media.getGenre().get(i), media.getLanguage());
                        mediaRepository.createGenreRelation(media.getTitle(), media.getGenre().get(i));
                    }
                }
                else if (genreRepository.findGenreByName(media.getGenre().get(i)) == null)
                {
                    if (mediaRepository.getByTitle(media.getTitle()) == null)
                    {
                        mediaRepository.createMediaNode(media.getTitle(), media.getGenre().get(i), media.getLanguage());
                        mediaRepository.createLanguageRelation(media.getTitle(), media.getLanguage());
                        mediaRepository.createGenreNode(media.getGenre().get(i));
                        mediaRepository.createGenreRelation(media.getTitle(), media.getGenre().get(i));
                    }
                    else {
                        mediaRepository.createGenreNode(media.getGenre().get(i));
                        mediaRepository.createGenreRelation(media.getTitle(), media.getGenre().get(i));
                    }
                }
                else {
                    if (mediaRepository.getByTitle(media.getTitle()) == null)
                    {
                        mediaRepository.createMediaNode(media.getTitle(), media.getGenre().get(i), media.getLanguage());
                        mediaRepository.createLanguageRelation(media.getTitle(), media.getLanguage());
                        mediaRepository.createGenreRelation(media.getTitle(), media.getGenre().get(i));
                    }
                    else {
                        mediaRepository.createGenreRelation(media.getTitle(), media.getGenre().get(i));
                    }
                }
            }
        }
        else{
            throw new MediaAlreadyExistException();
        }
        return media;
    }
}
