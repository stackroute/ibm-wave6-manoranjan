package com.stackroute.recommendationService.service;

import com.stackroute.recommendationService.domain.Viewer;
import com.stackroute.recommendationService.exception.ViewerAlreadyExistException;
import com.stackroute.recommendationService.exception.ViewerNotFoundException;
import com.stackroute.recommendationService.repository.GenreRepository;
import com.stackroute.recommendationService.repository.ViewerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import java.util.Collection;

@CacheConfig(cacheNames = "viewer")
@Service
public class ViewerServiceImpl implements ViewerService {

    @Autowired
    private ViewerRepository viewerRepository;

    @Autowired
    private GenreRepository genreRepository;

    //to handle delay
    public void simulateDelay(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Cacheable
    public Collection<Viewer> getAll() throws ViewerNotFoundException {
        if (viewerRepository.getAllViewers() == null) {
            throw new ViewerNotFoundException();
        } else {
            return viewerRepository.getAllViewers();
        }
    }

    @CacheEvict(allEntries = true)
    public Viewer saveViewer(Viewer viewer) throws ViewerAlreadyExistException {
        if (viewerRepository.findByEmailId(viewer.getEmailId()) == null) {
            viewerRepository.createViewer(viewer.getName(), viewer.getEmailId());
            int length = viewer.getGenre().size();
            for (int i = 0; i < length; i++) {
                viewerRepository.createGenreRelation(viewer.getEmailId(), viewer.getGenre().get(i));
            }
        }
        else {
            throw new ViewerAlreadyExistException();
        }
        return viewer;
    }

    @Cacheable
    public Viewer getViewerByEmailId(String emailId) throws ViewerNotFoundException {
        if (viewerRepository.findByEmailId(emailId) == null) {
            throw new ViewerNotFoundException();
        } else {
            return viewerRepository.findByEmailId(emailId);
        }
    }

    @CacheEvict(allEntries = true)
    public Viewer updateDetails(Viewer viewer) throws ViewerNotFoundException {
        if (viewerRepository.findByEmailId(viewer.getEmailId()) == null) {
            throw new ViewerNotFoundException();
        } else {
            Viewer viewer1 = viewerRepository.findByEmailId(viewer.getEmailId());
            viewer1.setEmailId(viewer.getEmailId());
            viewer1.setGenre(viewer.getGenre());
            viewerRepository.save(viewer1);
            return viewer1;
        }
    }

    @CacheEvict(allEntries = true)
    public Collection<Viewer> deleteViewer(String emailId) throws ViewerNotFoundException {
        if (viewerRepository.findByEmailId(emailId) == null) {
            throw new ViewerNotFoundException();
        } else {
            Viewer viewer = viewerRepository.findByEmailId(emailId);
            viewerRepository.delete(viewer);
            return viewerRepository.getAllViewers();
        }
    }

    @CacheEvict(allEntries = true)
    public Viewer saveStandaloneMediaRelation(String emailId, String title) throws ViewerNotFoundException {
        if (viewerRepository.findByEmailId(emailId) == null) {
            throw new ViewerNotFoundException();
        } else {
            return viewerRepository.createStandaloneMediaRelation(emailId, title);
        }
    }

    @CacheEvict(allEntries = true)
    public Viewer saveEpisodicMediaRelation(String emailId, String title) throws ViewerNotFoundException {
        if (viewerRepository.findByEmailId(emailId) == null) {
            throw new ViewerNotFoundException();
        } else {
            return viewerRepository.createEpisodicMediaRelation(emailId, title);
        }
    }
}