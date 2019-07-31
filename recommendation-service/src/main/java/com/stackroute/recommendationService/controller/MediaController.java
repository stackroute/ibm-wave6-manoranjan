package com.stackroute.recommendationService.controller;

import com.stackroute.recommendationService.domain.*;
import com.stackroute.recommendationService.exception.*;
import com.stackroute.recommendationService.service.MediaServiceImpl;
import com.stackroute.recommendationService.service.ViewerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/rest/neo4j")
public class MediaController {

    @Autowired
    private MediaServiceImpl mediaService;

    @Autowired
    private ViewerServiceImpl viewerService;

    @GetMapping("/standaloneMedias")
    public Collection<StandaloneMedia> getStandaloneMedias() throws MediaNotFoundException {
        return mediaService.getStandaloneMedias();
    }

    @GetMapping("/episodicMedias")
    public Collection<EpisodicMedia> getEpisodicMedias() throws MediaNotFoundException {
        return mediaService.getEpisodicMedias();
    }

    @GetMapping("/languages")
    public Collection<Language> getLanguages() throws LanguageNotFoundException {
        return mediaService.getLanguages();
    }

    @GetMapping("/genres")
    public Collection<Genre> getGenres() throws GenreNotFoundException {
        return mediaService.getGenres();
    }

    @PostMapping("/standaloneMedia")
    public StandaloneMedia saveNewStandaloneMedia(@RequestBody StandaloneMedia standaloneMedia) throws MediaAlreadyExistException {
        return mediaService.saveStandaloneMedia(standaloneMedia);
    }

    @PostMapping("/episodicMedia")
    public EpisodicMedia saveNewEpisodicMedia(@RequestBody EpisodicMedia episodicMedia) throws MediaAlreadyExistException {
        return mediaService.saveEpisodicMedia(episodicMedia);
    }

    @GetMapping("/standaloneMedia/{title}")
    public StandaloneMedia getStandaloneMediaByTitle(@PathVariable("title") String title) throws MediaNotFoundException {
        return mediaService.getStandaloneMediaByTitle(title);
    }

    @GetMapping("/episodicMedia/{title}")
    public EpisodicMedia getEpisodicMediaByTitle(@PathVariable("title") String title) throws MediaNotFoundException {
        return mediaService.getEpisodicMediaByTitle(title);
    }

    @GetMapping("/viewers")
    public Collection<Viewer> getViewer() throws ViewerNotFoundException {
        return viewerService.getAll();
    }

    @PostMapping("/viewer")
    public Viewer saveViewer(@RequestBody Viewer viewer) throws ViewerAlreadyExistException {
        return viewerService.saveViewer(viewer);
    }

    @GetMapping("/viewer/{emailId}")
    public Viewer getByEmailId(@PathVariable("emailId") String emailId) throws ViewerNotFoundException {
        return viewerService.getViewerByEmailId(emailId);
    }

    @PutMapping("/viewer")
    public Viewer updateDetails(@RequestBody Viewer viewer) throws ViewerNotFoundException {
        return viewerService.updateDetails(viewer);
    }

    @DeleteMapping("/viewer")
    public Collection<Viewer> deleteViewer(@RequestBody Viewer viewer) throws ViewerNotFoundException {
        return viewerService.deleteViewer(viewer.getEmailId());
    }

    @PostMapping("graphStandaloneMedia/{emailId}/{title}")
    public Viewer saveStandaloneMediaRelation(@PathVariable String emailId, @PathVariable String title) throws ViewerNotFoundException {
        return viewerService.saveStandaloneMediaRelation(emailId, title);
    }

    @PostMapping("graphEpisodicMedia/{emailId}/{title}")
    public Viewer saveEpisodicMediaRelation(@PathVariable String emailId, @PathVariable String title) throws ViewerNotFoundException {
        return viewerService.saveEpisodicMediaRelation(emailId, title);
    }
}
