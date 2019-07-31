package com.stackroute.recommendationService.service;

import com.stackroute.recommendationService.domain.Viewer;
import com.stackroute.recommendationService.exception.ViewerAlreadyExistException;
import com.stackroute.recommendationService.exception.ViewerNotFoundException;
import com.stackroute.recommendationService.repository.EpisodicMediaRepository;
import com.stackroute.recommendationService.repository.GenreRepository;
import com.stackroute.recommendationService.repository.StandaloneMediaRepository;
import com.stackroute.recommendationService.repository.ViewerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;

@Service
public class ViewerServiceImpl implements ViewerService {

    @Autowired
    private ViewerRepository viewerRepository;

    @Autowired
    private GenreRepository genreRepository;
    @Autowired
    EpisodicMediaRepository episodicMediaRepository;
    @Autowired
    StandaloneMediaRepository standaloneMediaRepository;

    public Collection<Viewer> getAll()throws ViewerNotFoundException {
        if (viewerRepository.getAllViewers()==null){
            throw new ViewerNotFoundException();
        }
        else {
            return viewerRepository.getAllViewers();
        }
    }

    public Viewer saveViewer(Viewer viewer)throws ViewerAlreadyExistException {
        if (viewerRepository.findByEmailId(viewer.getEmailId())==null) {
            int length = viewer.getGenre().size();
            for (int i = 0; i < length; i++) {
                if (genreRepository.findGenreByName(viewer.getGenre().get(i)) == null) {
                    if (viewerRepository.findByEmailId(viewer.getEmailId()) == null) {
                        viewerRepository.createNewViewerWithGenre(viewer.getName(), viewer.getEmailId(), viewer.getGenre().get(i));
                    } else {
                        viewerRepository.createGenreNode(viewer.getGenre().get(i));
                        viewerRepository.createGenreRelation(viewer.getEmailId(), viewer.getGenre().get(i));
                    }
                } else if (viewerRepository.findByEmailId(viewer.getEmailId()) == null) {
                    viewerRepository.createViewer(viewer.getName(), viewer.getEmailId(), viewer.getGenre().get(i));
                    viewerRepository.createGenreRelation(viewer.getEmailId(), viewer.getGenre().get(i));
                } else {
                    viewerRepository.createGenreRelation(viewer.getEmailId(), viewer.getGenre().get(i));
                }
            }
        }
        else{
            throw new ViewerAlreadyExistException();
        }
        return viewer;
    }

    public Viewer getViewerByEmailId(String emailId)throws ViewerNotFoundException{
        if (viewerRepository.findByEmailId(emailId)==null){
            throw new ViewerNotFoundException();
        }
        else {
            return viewerRepository.findByEmailId(emailId);
        }
    }

    public Viewer updateDetails(Viewer viewer)throws ViewerNotFoundException{
        if (viewerRepository.findByEmailId(viewer.getEmailId())==null){
            throw new ViewerNotFoundException();
        }
        else {
            Viewer viewer1 = viewerRepository.findByEmailId(viewer.getEmailId());
            viewer1.setEmailId(viewer.getEmailId());
            viewer1.setGenre(viewer.getGenre());
            viewerRepository.save(viewer1);
            return viewer1;
        }
    }

    public Collection<Viewer> deleteViewer(String emailId)throws ViewerNotFoundException{
        if (viewerRepository.findByEmailId(emailId)==null){
            throw new ViewerNotFoundException();
        }
        else {
            Viewer viewer = viewerRepository.findByEmailId(emailId);
            viewerRepository.delete(viewer);
            return viewerRepository.getAllViewers();
        }
    }

    public Viewer saveStandaloneMediaRelation(String emailId,String title)throws ViewerNotFoundException {
        if (viewerRepository.findByEmailId(emailId)==null){
            throw new ViewerNotFoundException();
        }
        else {
            return viewerRepository.createStandaloneMediaRelation(emailId, title);
        }
    }

    public Viewer saveEpisodicMediaRelation(String emailId,String title)throws ViewerNotFoundException {
        if (viewerRepository.findByEmailId(emailId)==null){
            throw new ViewerNotFoundException();
        }
        else {
            return viewerRepository.createEpisodicMediaRelation(emailId, title);
        }
    }
}