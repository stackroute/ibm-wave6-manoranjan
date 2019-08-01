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

    @GetMapping("/documentaries")
    public Collection<Documentary> getDocumentary() throws MediaNotFoundException {
        return mediaService.getDocumentary();
    }

    @GetMapping("/movies")
    public Collection<Movie> getMovie() throws MediaNotFoundException {
        return mediaService.getMovie();
    }

    @GetMapping("/tvEpisodes")
    public Collection<TvEpisodes> getTvEpisodes() throws MediaNotFoundException {
        return mediaService.getTvEpisodes();
    }

    @GetMapping("/webSeries")
    public Collection<WebSeries> getWebSeries() throws MediaNotFoundException {
        return mediaService.getWebSeries();
    }

    //getting all the languages
    @GetMapping("/languages")
    public Collection<Language> getLanguages() throws LanguageNotFoundException {
        return mediaService.getLanguages();
    }

    //getting all the genres
    @GetMapping("/genres")
    public Collection<Genre> getGenres() throws GenreNotFoundException {
        return mediaService.getGenres();
    }

    @GetMapping("/documentary/{title}")
    public Documentary getDocumentaryByTitle(@PathVariable("title") String title) throws MediaNotFoundException {
        return mediaService.getDocumentaryByTitle(title);
    }

    @GetMapping("/movie/{title}")
    public Movie getMovieByTitle(@PathVariable("title") String title) throws MediaNotFoundException {
        return mediaService.getMovieByTitle(title);
    }

    @GetMapping("/tvEpisodes/{title}")
    public TvEpisodes getTvEpisodesByTitle(@PathVariable("title") String title) throws MediaNotFoundException {
        return mediaService.getTvEpisodesByTitle(title);
    }

    @GetMapping("/webSeries/{title}")
    public WebSeries getWebSeriesByTitle(@PathVariable("title") String title) throws MediaNotFoundException {
        return mediaService.getWebSeriesByTitle(title);
    }

    @PostMapping("/standalone/documentary")
    public Documentary saveNewDocumentary(@RequestBody Documentary documentary) throws MediaAlreadyExistException {
        return mediaService.saveDocumentary(documentary);
    }

    @PostMapping("/standalone/movie")
    public Movie saveNewMovie(@RequestBody Movie movie) throws MediaAlreadyExistException {
        return mediaService.saveMovie(movie);
    }

    @PostMapping("/episodicMedia/tvEpisode")
    public TvEpisodes saveNewTvEpisodes(@RequestBody TvEpisodes tvEpisodes) throws MediaAlreadyExistException {
        return mediaService.saveTvEpisodes(tvEpisodes);
    }

    @PostMapping("/episodicMedia/webSeries")
    public WebSeries saveNewWebSeries(@RequestBody WebSeries webSeries) throws MediaAlreadyExistException {
        return mediaService.saveWebSeries(webSeries);
    }

    //getting all the viewers
    @GetMapping("/viewers")
    public Collection<Viewer> getViewer() throws ViewerNotFoundException {
        return viewerService.getAll();
    }

    //posting the viewer
    @PostMapping("/viewer")
    public Viewer saveViewer(@RequestBody Viewer viewer) throws ViewerAlreadyExistException {
        return viewerService.saveViewer(viewer);
    }

    //getting viewer by emailId
    @GetMapping("/viewer/{email}")
    public Viewer getByEmailId(@PathVariable("email") String email) throws ViewerNotFoundException {
        return viewerService.getViewerByEmailId(email);
    }

    //updating viewer details
    @PutMapping("/viewer")
    public Viewer updateDetails(@RequestBody Viewer viewer) throws ViewerNotFoundException {
        return viewerService.updateDetails(viewer);
    }

    //deleting viewer
    @DeleteMapping("/viewer")
    public Collection<Viewer> deleteViewer(@RequestBody Viewer viewer) throws ViewerNotFoundException {
        return viewerService.deleteViewer(viewer.getEmailId());
    }

    //posting the emailId of viewer and title of standalone media
    @PostMapping("graphDocumentary/{email}/{title}")
    public Viewer saveDocumentaryRelation(@PathVariable String email, @PathVariable String title) throws ViewerNotFoundException {
        return viewerService.saveDocumentaryRelation(email, title);
    }

    //posting the emailId of viewer and title of episodic media
    @PostMapping("graphMovie/{email}/{title}")
    public Viewer saveMovieRelation(@PathVariable String email, @PathVariable String title) throws ViewerNotFoundException {
        return viewerService.saveMovieRelation(email, title);
    }

    @PostMapping("graphTvEpisodes/{email}/{title}")
    public Viewer saveTvEpisodesRelation(@PathVariable String email, @PathVariable String title) throws ViewerNotFoundException {
        return viewerService.saveTvEpisodesRelation(email, title);
    }

    @PostMapping("graphWebSeries/{email}/{title}")
    public Viewer saveWebSeriesRelation(@PathVariable String email, @PathVariable String title) throws ViewerNotFoundException {
        return viewerService.saveWebSeriesRelation(email, title);
    }
}
