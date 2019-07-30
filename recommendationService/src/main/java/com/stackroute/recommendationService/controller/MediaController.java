package com.stackroute.recommendationService.controller;

import com.stackroute.recommendationService.domain.Genre;
import com.stackroute.recommendationService.domain.Language;
import com.stackroute.recommendationService.domain.Media;
import com.stackroute.recommendationService.exception.GenreNotFoundException;
import com.stackroute.recommendationService.exception.LanguageNotFoundException;
import com.stackroute.recommendationService.exception.MediaAlreadyExistException;
import com.stackroute.recommendationService.exception.MediaNotFoundException;
import com.stackroute.recommendationService.service.MediaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;

@RestController
@RequestMapping("/rest/neo4j")
public class MediaController {

    @Autowired
    private MediaServiceImpl mediaService;
    private Media media;

    @GetMapping("/medias")
    public Collection<Media> getMedias()throws MediaNotFoundException {
        return mediaService.getMedias();
    }

    @GetMapping("/languages")
    public Collection<Language> getLanguages()throws LanguageNotFoundException {
        return mediaService.getLanguages();
    }

    @GetMapping("/genres")
    public Collection<Genre> getGenres()throws GenreNotFoundException {
        return mediaService.getGenres();
    }

    @PostMapping("/media")
    public Media saveNewMedia(@RequestBody Media media)throws MediaAlreadyExistException {
        return mediaService.saveMedia(media);
    }

    @GetMapping("/media/{title}")
    public Media getMediaByTitle(@PathVariable("title") String title)throws MediaNotFoundException{
        return mediaService.getMediaByTitle(title);
    }
}
