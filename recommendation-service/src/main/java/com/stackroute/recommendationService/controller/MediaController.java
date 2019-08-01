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

    @GetMapping("/languages")
    public Collection<Language> getLanguages() throws LanguageNotFoundException {
        return mediaService.getLanguages();
    }

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