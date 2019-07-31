package com.stackroute.recommendationService.controller;

import com.stackroute.recommendationService.domain.Viewer;
import com.stackroute.recommendationService.exception.ViewerAlreadyExistException;
import com.stackroute.recommendationService.exception.ViewerNotFoundException;
import com.stackroute.recommendationService.service.ViewerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/rest/neo4j/viewer")
public class ViewerController {

    @Autowired
    private ViewerServiceImpl viewerService;

    @GetMapping("/viewers")
    public Collection<Viewer> getViewer() throws ViewerNotFoundException {
        return viewerService.getAll();
    }

    @PostMapping("/viewer")
    public Viewer saveViewer(@RequestBody Viewer viewer) throws ViewerAlreadyExistException {
        return viewerService.saveViewer(viewer);
    }

    @GetMapping("/viewer/{email}")
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

    @PostMapping("graphMedia/{email}/{title}")
    public Viewer saveMediaRelation(@PathVariable String emailId, @PathVariable String title) throws ViewerNotFoundException {
        return viewerService.saveMediaRelation(emailId, title);
    }
}
