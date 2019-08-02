package com.stackroute.recommendationService.service;

import com.stackroute.recommendationService.domain.Viewer;
import com.stackroute.recommendationService.exception.ViewerAlreadyExistException;
import com.stackroute.recommendationService.exception.ViewerNotFoundException;
import com.stackroute.recommendationService.repository.GenreRepository;
import com.stackroute.recommendationService.repository.ViewerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import java.util.Collection;

@Service
@Primary
public class ViewerServiceImpl implements ViewerService {

    @Autowired
    private ViewerRepository viewerRepository;

    @Autowired
    private GenreRepository genreRepository;

    //Method to get all viewers
    public Collection<Viewer> getAll() throws ViewerNotFoundException {
        if (viewerRepository.getAllViewers() == null) {
            throw new ViewerNotFoundException();
        } else {
            return viewerRepository.getAllViewers();
        }
    }

    //method to save viewer
    public Viewer saveViewer(Viewer viewer) throws ViewerAlreadyExistException {
        if (viewerRepository.findByEmailId(viewer.getEmailId()) == null) {
            viewerRepository.createViewer(viewer.getName(), viewer.getEmailId());
            int length = viewer.getInterest().size();
            for (int i = 0; i < length; i++) {
                viewerRepository.createGenreRelation(viewer.getEmailId(), viewer.getInterest().get(i));
            }
        }
        else {
            throw new ViewerAlreadyExistException();
        }
        return viewer;
    }

    //method to get viewer by emailId
    public Viewer getViewerByEmailId(String emailId) throws ViewerNotFoundException {
        if (viewerRepository.findByEmailId(emailId) == null) {
            throw new ViewerNotFoundException();
        } else {
            return viewerRepository.findByEmailId(emailId);
        }
    }

    //method to update viewer details
    public Viewer updateDetails(Viewer viewer) throws ViewerNotFoundException {
        if (viewerRepository.findByEmailId(viewer.getEmailId()) == null) {
            throw new ViewerNotFoundException();
        } else {
            Viewer viewer1 = viewerRepository.findByEmailId(viewer.getEmailId());
            viewer1.setEmailId(viewer.getEmailId());
            viewer1.setInterest(viewer.getInterest());
            viewerRepository.save(viewer1);
            return viewer1;
        }
    }

    //method to delete viewer by emailId
    public Collection<Viewer> deleteViewer(String emailId) throws ViewerNotFoundException {
        if (viewerRepository.findByEmailId(emailId) == null) {
            throw new ViewerNotFoundException();
        } else {
            Viewer viewer = viewerRepository.findByEmailId(emailId);
            viewerRepository.delete(viewer);
            return viewerRepository.getAllViewers();
        }
    }

    //method to save viewer and documentary relation
    public Viewer saveDocumentaryRelation(String emailId, String title) throws ViewerNotFoundException {
        if (viewerRepository.findByEmailId(emailId) == null) {
            throw new ViewerNotFoundException();
        } else {
            return viewerRepository.createDocumentaryRelation(emailId, title);
        }
    }

    //method to save viewer and movie relation
    public Viewer saveMovieRelation(String emailId, String title) throws ViewerNotFoundException {
        if (viewerRepository.findByEmailId(emailId) == null) {
            throw new ViewerNotFoundException();
        } else {
            return viewerRepository.createMovieRelation(emailId, title);
        }
    }

    //method to save viewer and TvEpisodes relation
    public Viewer saveTvEpisodesRelation(String emailId, String title) throws ViewerNotFoundException {
        if (viewerRepository.findByEmailId(emailId) == null) {
            throw new ViewerNotFoundException();
        } else {
            return viewerRepository.createTvEpisodesRelation(emailId, title);
        }
    }

    //method to save viewer and WebSeries relation
    public Viewer saveWebSeriesRelation(String emailId, String title) throws ViewerNotFoundException {
        if (viewerRepository.findByEmailId(emailId) == null) {
            throw new ViewerNotFoundException();
        } else {
            return viewerRepository.createWebSeriesRelation(emailId, title);
        }
    }
}