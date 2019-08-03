package com.stackroute.episodicmediaservice.controller;

import com.stackroute.episodicmediaservice.domain.Episode;
import com.stackroute.episodicmediaservice.domain.EpisodicMedia;
import com.stackroute.episodicmediaservice.exception.MediaAlreadyExistsException;
import com.stackroute.episodicmediaservice.exception.MediaNotFoundException;
import com.stackroute.episodicmediaservice.service.EpisodicService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(value = "EpisodicControllerApi",produces = MediaType.APPLICATION_JSON_VALUE)
@RequestMapping(value = "/stream/v1")
@CrossOrigin("*")
public class EpisodicController {
    @Autowired
    private EpisodicService mediaService;

    //posting episodic media
    @PostMapping("/serial")
    @ApiOperation("Save serial")
    @ApiResponses(value = {@ApiResponse(code = 200,message = "OK",response = EpisodicMedia.class)})
    public ResponseEntity<?> saveSerial(@RequestBody EpisodicMedia media) throws MediaAlreadyExistsException {
        EpisodicMedia media1;
        media1=mediaService.saveSerial(media);
        return new ResponseEntity<>(media1, HttpStatus.OK);
    }

    //posting single episode
    @PostMapping("/episode/{serial-title}")
    @ApiOperation("Save episode")
    @ApiResponses(value = {@ApiResponse(code = 200,message = "OK")})
    public ResponseEntity<?> saveEpisode(@PathVariable("serial-title") String serialTitle, @RequestBody Episode episode) throws MediaNotFoundException, MediaAlreadyExistsException {
        return new ResponseEntity<>(mediaService.addEpisode(serialTitle, episode), HttpStatus.OK);
    }

    //getting all the episodic media
    @GetMapping("/serials")
    @ApiOperation("Get all serials ")
    @ApiResponses(value = {@ApiResponse(code = 200,message = "OK")})
    public ResponseEntity<?> getAllSerials() throws MediaNotFoundException {
        List<EpisodicMedia> episodicMediaList;
        episodicMediaList=mediaService.getAllSerials();
        return new ResponseEntity<>(episodicMediaList,HttpStatus.OK);
    }

    //searching all the episodes of single episodic media
    @GetMapping("/episodes/{title}")
    @ApiOperation("Get all episodes ")
    @ApiResponses(value = {@ApiResponse(code = 200,message = "OK")})
    public ResponseEntity<?> getAllEpisodes(@PathVariable("title") String serialTitle) throws MediaNotFoundException {
        return new ResponseEntity<>(mediaService.getAllEpisodes(serialTitle),HttpStatus.OK);
    }

    //searching single episodic media by title
    @GetMapping("/serial/{title}")
    @ApiOperation("Get serial by id ")
    @ApiResponses(value = {@ApiResponse(code = 200,message = "OK")})
    public ResponseEntity<?> getSerialById(@PathVariable("title") String serialTitle) throws MediaNotFoundException {
        return new ResponseEntity<>(mediaService.getSerialByTitle(serialTitle),HttpStatus.OK);
    }


    //seraching the episode by episode title and episode number
    @GetMapping("/episode/{title}/{episode-num}")
    @ApiOperation("Get episode by id ")
    @ApiResponses(value = {@ApiResponse(code = 200,message = "OK")})
    public ResponseEntity<?> getEpisodeById(@PathVariable("title") String serialTitle, @PathVariable("episodeNum") int episodeNum) throws MediaNotFoundException {
        return new ResponseEntity<>(mediaService.getEpisodeById(serialTitle, episodeNum), HttpStatus.OK);
    }

    //searching the episodic media by language
    @GetMapping("/series/tv/{language}")
    @ApiOperation("Get serial by language")
    @ApiResponses(value = {@ApiResponse(code = 200,message = "OK")})
    public ResponseEntity<?> getSerialByLanguage(@PathVariable("language") String language) throws MediaNotFoundException {
        return new ResponseEntity<>(mediaService.getTvSerialByLanguage(language),HttpStatus.OK);
    }

    //searching the episodic media by category
    @GetMapping("/series/category/{category}")
    @ApiOperation("Get episode by category ")
    @ApiResponses(value = {@ApiResponse(code = 200,message = "OK")})
    public ResponseEntity<?> getEpisodicByCategory(@PathVariable("category") String category) throws MediaNotFoundException {
        return new ResponseEntity<>(mediaService.getSerialByCategory(category),HttpStatus.OK);
    }

    //deleting episodic media by title
    @DeleteMapping("/serial/{title}")
    @ApiOperation("Delete serial ")
    @ApiResponses(value = {@ApiResponse(code = 200,message = "OK")})
    public ResponseEntity<?> deleteSerial(@PathVariable("title") String serialTitle) throws MediaNotFoundException {
        return new ResponseEntity<>(mediaService.deleteSerial(serialTitle),HttpStatus.OK);

    }

    //deleting episodic media by tiel an episode number
    @DeleteMapping("/episode/{title}/{episode-num}")
    @ApiOperation("Delete episode")
    @ApiResponses(value = {@ApiResponse(code = 200,message = "OK")})
    public ResponseEntity<?> deleteEpisode(@PathVariable("title") String serialTitle, @PathVariable("episodeNum") int episodeNum) throws MediaNotFoundException {
        return new ResponseEntity<>(mediaService.deleteEpisode(serialTitle, episodeNum),HttpStatus.OK);
    }

    //getting list of standalone media in wishlist
    @PatchMapping("/wish")
    @ApiOperation("Get media list ")
    @ApiResponses(value = {@ApiResponse(code = 200,message = "OK")})
    public ResponseEntity<?> getWishlist(@RequestBody List<String> titles) throws MediaNotFoundException {
        return new ResponseEntity<>(mediaService.getWishlist(titles),HttpStatus.OK);
    }
}
