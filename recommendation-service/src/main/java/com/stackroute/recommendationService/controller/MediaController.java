package com.stackroute.recommendationService.controller;

import com.stackroute.recommendationService.domain.Genre;
import com.stackroute.recommendationService.domain.Language;
import com.stackroute.recommendationService.domain.StandaloneMedia;
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
    private StandaloneMedia standaloneMedia;

    @GetMapping("/medias")
    public Collection<StandaloneMedia> getMedias() throws MediaNotFoundException {
        return mediaService.getMedias();
    }

    @GetMapping("/languages")
    public Collection<Language> getLanguages() throws LanguageNotFoundException {
        return mediaService.getLanguages();
    }

    @GetMapping("/genres")
    public Collection<Genre> getGenres() throws GenreNotFoundException {
        return mediaService.getGenres();
    }

    @PostMapping("/media")
    public StandaloneMedia saveNewMedia(@RequestBody StandaloneMedia standaloneMedia) throws MediaAlreadyExistException {
        return mediaService.saveMedia(standaloneMedia);
    }

    @GetMapping("/media/{title}")
    public StandaloneMedia getMediaByTitle(@PathVariable("title") String title) throws MediaNotFoundException {
        return mediaService.getMediaByTitle(title);
    }
}
