package com.stackroute.episodicmediaservice.controller;

import com.stackroute.episodicmediaservice.domain.Episode;
import com.stackroute.episodicmediaservice.domain.EpisodicMedia;
import com.stackroute.episodicmediaservice.exception.MediaAlreadyExistsException;
import com.stackroute.episodicmediaservice.exception.MediaNotFoundException;
import com.stackroute.episodicmediaservice.service.EpisodicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/stream/v1")
@CrossOrigin("*")
public class EpisodicController {
    @Autowired
    private EpisodicService mediaService;

    @PostMapping("/serial")
    public ResponseEntity<?> saveSerial(@RequestBody EpisodicMedia media) throws MediaAlreadyExistsException {
        EpisodicMedia media1;
        media1=mediaService.saveSerial(media);
        return new ResponseEntity<>(media1, HttpStatus.OK);
    }

    @PostMapping("/episode/{serial-title}")
    public ResponseEntity<?> saveEpisode(@PathVariable("serial-title") String serialTitle, @RequestBody Episode episode) throws MediaNotFoundException, MediaAlreadyExistsException {
        return new ResponseEntity<>(mediaService.addEpisode(serialTitle, episode), HttpStatus.OK);
    }

    @GetMapping("/serials")
    public ResponseEntity<?> getAllSerials() throws MediaNotFoundException {
        List<EpisodicMedia> episodicMediaList;
        episodicMediaList=mediaService.getAllSerials();
        return new ResponseEntity<>(episodicMediaList,HttpStatus.OK);
    }

    @GetMapping("/episodes/{title}")
    public ResponseEntity<?> getAllEpisodes(@PathVariable("title") String serialTitle) throws MediaNotFoundException {
        return new ResponseEntity<>(mediaService.getAllEpisodes(serialTitle),HttpStatus.OK);
    }

    @GetMapping("/serial/{title}")
    public ResponseEntity<?> getSerialById(@PathVariable("title") String serialTitle) throws MediaNotFoundException {
        return new ResponseEntity<>(mediaService.getSerialByTitle(serialTitle),HttpStatus.OK);
    }


    @GetMapping("/episode/{title}/{episode-num}")
    public ResponseEntity<?> getEpisodeById(@PathVariable("title") String serialTitle, @PathVariable("episodeNum") int episodeNum) throws MediaNotFoundException {
        return new ResponseEntity<>(mediaService.getEpisodeById(serialTitle, episodeNum), HttpStatus.OK);
    }

    @GetMapping("/series/tv/{language}")
    public ResponseEntity<?> getSerialByLanguage(@PathVariable("language") String language) throws MediaNotFoundException {
        return new ResponseEntity<>(mediaService.getTvSerialByLanguage(language),HttpStatus.OK);
    }

    @GetMapping("/series/category/{category}")
    public ResponseEntity<?> getEpisodicByCategory(@PathVariable("category") String category) throws MediaNotFoundException {
        return new ResponseEntity<>(mediaService.getSerialByCategory(category),HttpStatus.OK);
    }

    @DeleteMapping("/serial/{title}")
    public ResponseEntity<?> deleteSerial(@PathVariable("title") String serialTitle) throws MediaNotFoundException {
        return new ResponseEntity<>(mediaService.deleteSerial(serialTitle),HttpStatus.OK);

    }

    @DeleteMapping("/episode/{title}/{episode-num}")
    public ResponseEntity<?> deleteEpisode(@PathVariable("title") String serialTitle, @PathVariable("episodeNum") int episodeNum) throws MediaNotFoundException {
        return new ResponseEntity<>(mediaService.deleteEpisode(serialTitle, episodeNum),HttpStatus.OK);
    }

    @PatchMapping("/wish")
    public ResponseEntity<?> getWishlist(@RequestBody List<String> titles) throws MediaNotFoundException {
        return new ResponseEntity<>(mediaService.getWishlist(titles),HttpStatus.OK);
    }
}
