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

    //getting documentaries
    @GetMapping("/documentaries")
    public Collection<Documentary> getDocumentary() throws MediaNotFoundException {
        return mediaService.getDocumentary();
    }

    //getting movies
    @GetMapping("/movies")
    public Collection<Movie> getMovie() throws MediaNotFoundException {
        return mediaService.getMovie();
    }

    //getting tv episodes
    @GetMapping("/tvEpisodes")
    public Collection<TvEpisodes> getTvEpisodes() throws MediaNotFoundException {
        return mediaService.getTvEpisodes();
    }

    //getting web series
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

    //getting documentary by title
    @GetMapping("/documentary/{title}")
    public Documentary getDocumentaryByTitle(@PathVariable("title") String title) throws MediaNotFoundException {
        return mediaService.getDocumentaryByTitle(title);
    }

    //getting movie by title
    @GetMapping("/movie/{title}")
    public Movie getMovieByTitle(@PathVariable("title") String title) throws MediaNotFoundException {
        return mediaService.getMovieByTitle(title);
    }

    //getting tv Episodes by title
    @GetMapping("/tvEpisodes/{title}")
    public TvEpisodes getTvEpisodesByTitle(@PathVariable("title") String title) throws MediaNotFoundException {
        return mediaService.getTvEpisodesByTitle(title);
    }

    //getting web series by title
    @GetMapping("/webSeries/{title}")
    public WebSeries getWebSeriesByTitle(@PathVariable("title") String title) throws MediaNotFoundException {
        return mediaService.getWebSeriesByTitle(title);
    }

    //posting documentary
    @PostMapping("/standalone/documentary")
    public Documentary saveNewDocumentary(@RequestBody Documentary documentary) throws MediaAlreadyExistException {
        return mediaService.saveDocumentary(documentary);
    }

    //posting movie
    @PostMapping("/standalone/movie")
    public Movie saveNewMovie(@RequestBody Movie movie) throws MediaAlreadyExistException {
        return mediaService.saveMovie(movie);
    }

    //posting tv episodes
    @PostMapping("/episodicMedia/tvEpisode")
    public TvEpisodes saveNewTvEpisodes(@RequestBody TvEpisodes tvEpisodes) throws MediaAlreadyExistException {
        return mediaService.saveTvEpisodes(tvEpisodes);
    }

    //posting web series
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

    //posting the emailId of viewer and title of documentary
    @PostMapping("graphDocumentary/{email}/{title}")
    public Viewer saveDocumentaryRelation(@PathVariable String email, @PathVariable String title) throws ViewerNotFoundException {
        return viewerService.saveDocumentaryRelation(email, title);
    }

    //posting the emailId of viewer and title of movie
    @PostMapping("graphMovie/{email}/{title}")
    public Viewer saveMovieRelation(@PathVariable String email, @PathVariable String title) throws ViewerNotFoundException {
        return viewerService.saveMovieRelation(email, title);
    }

    //posting the emailId of viewer and title of tv episodes
    @PostMapping("graphTvEpisodes/{email}/{title}")
    public Viewer saveTvEpisodesRelation(@PathVariable String email, @PathVariable String title) throws ViewerNotFoundException {
        return viewerService.saveTvEpisodesRelation(email, title);
    }

    //posting the emailId of viewer and title of web series
    @PostMapping("graphWebSeries/{email}/{title}")
    public Viewer saveWebSeriesRelation(@PathVariable String email, @PathVariable String title) throws ViewerNotFoundException {
        return viewerService.saveWebSeriesRelation(email, title);
    }
}
