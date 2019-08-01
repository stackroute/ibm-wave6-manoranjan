package com.stackroute.mediaManagerService.controller;

import com.stackroute.mediaManagerService.domain.Episode;
import com.stackroute.mediaManagerService.domain.EpisodicMedia;
import com.stackroute.mediaManagerService.domain.Media;
import com.stackroute.mediaManagerService.exceptions.FileNotUploadedException;
import com.stackroute.mediaManagerService.exceptions.MediaAlreadyExistsException;
import com.stackroute.mediaManagerService.exceptions.MediaNotFoundException;
import com.stackroute.mediaManagerService.service.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/stream/v1")
@CrossOrigin("*")
public class MediaController {

    @Autowired
    private MediaService mediaService;

    //posting standalone media
    @PostMapping("/media")
    public ResponseEntity<?> saveMedia(@RequestBody Media media) throws MediaAlreadyExistsException {
        Media media1;
        media1=mediaService.saveMedia(media);
        return new ResponseEntity<>(media1,HttpStatus.OK);

    }

    //posting episodic media
    @PostMapping("/serial")
    public ResponseEntity<?> saveSerial(@RequestBody EpisodicMedia media) throws MediaAlreadyExistsException {
        EpisodicMedia media1;
        media1=mediaService.saveSerial(media);
        return new ResponseEntity<>(media1,HttpStatus.OK);
    }

    //posting single episode
    @PostMapping("/episode/{serial-title}")
    public ResponseEntity<?> saveEpisode(@PathVariable("serial-title") String serialTitle, @RequestBody Episode episode) throws MediaNotFoundException, MediaAlreadyExistsException {
        return new ResponseEntity<>(mediaService.addEpisode(serialTitle, episode), HttpStatus.OK);
    }

    //getting all the standalone media
    @GetMapping("/medias")
    public ResponseEntity<?> getAllMedias() throws MediaNotFoundException {
        List<Media> mediaList;
        mediaList=mediaService.getAllMedia();
        return new ResponseEntity<>(mediaList,HttpStatus.OK);
    }

    //getting all the episodic media
    @GetMapping("/serials")
    public ResponseEntity<?> getAllSerials() throws MediaNotFoundException {
        List<EpisodicMedia> episodicMediaList;
        episodicMediaList=mediaService.getAllSerials();
        return new ResponseEntity<>(episodicMediaList,HttpStatus.OK);
    }

    //searching all the episodes of single episodic media
    @GetMapping("/episodes/{title}")
    public ResponseEntity<?> getAllEpisodes(@PathVariable("title") String serialTitle) throws MediaNotFoundException {
        return new ResponseEntity<>(mediaService.getAllEpisodes(serialTitle),HttpStatus.OK);
    }

    //searching single standalone media by title
    @GetMapping("/media/{title}")
    public ResponseEntity<?> getMediaById(@PathVariable("title") String mediaTitle) throws MediaNotFoundException {
        return new ResponseEntity<>(mediaService.getMediaById(mediaTitle),HttpStatus.OK);
    }

    //searching single episodic media by title
    @GetMapping("/serial/{title}")
    public ResponseEntity<?> getSerialById(@PathVariable("title") String serialTitle) throws MediaNotFoundException {
        return new ResponseEntity<>(mediaService.getSerialByTitle(serialTitle),HttpStatus.OK);
    }

    //seraching the episode by episode title and episode number
    @GetMapping("/episode/{title}/{episode-num}")
    public ResponseEntity<?> getEpisodeById(@PathVariable("title") String serialTitle, @PathVariable("episodeNum") int episodeNum) throws MediaNotFoundException {
        return new ResponseEntity<>(mediaService.getEpisodeById(serialTitle, episodeNum), HttpStatus.OK);
    }

    //searching the standalone media by genre
    @GetMapping("/media/movie/{genre}")
    public ResponseEntity<?> getMediaByGenre(@PathVariable("genre") String genre) throws MediaNotFoundException {
        return new ResponseEntity<>(mediaService.getMediaByGenre(genre),HttpStatus.OK);
    }

    //searching the episodic media by language
    @GetMapping("/series/tv/{language}")
    public ResponseEntity<?> getSerialByLanguage(@PathVariable("language") String language) throws MediaNotFoundException {
        return new ResponseEntity<>(mediaService.getTvSerialByLanguage(language),HttpStatus.OK);
    }

    //searching the standalone media by category
    @GetMapping("/media/category/{category}")
    public ResponseEntity<?> getSpecificCategoryMedia(@PathVariable("category") String category) throws MediaNotFoundException {
        return new ResponseEntity<>(mediaService.getMediaByCategory(category),HttpStatus.OK);
    }

    //searching the episodic media by category
    @GetMapping("/series/category/{category}")
    public ResponseEntity<?> getEpisodicByCategory(@PathVariable("category") String category) throws MediaNotFoundException {
        return new ResponseEntity<>(mediaService.getSerialByCategory(category),HttpStatus.OK);
    }

    //displaying all the standalone media
    @PostMapping("/media/list")
    public ResponseEntity<?> getMediaList(@RequestBody List<List<String>> medias){
        return new ResponseEntity<>(mediaService.getMediaList(medias),HttpStatus.OK);
    }

    //deleting standalone media by title
    @DeleteMapping("/media/{title}")
    public ResponseEntity<?> deleteMedia(@PathVariable("title") String mediaTitle) throws MediaNotFoundException {
        return new ResponseEntity<>(mediaService.deleteMedia(mediaTitle),HttpStatus.OK);
    }

    //deleting episodic media by title
    @DeleteMapping("/serial/{title}")
    public ResponseEntity<?> deleteSerial(@PathVariable("title") String serialTitle) throws MediaNotFoundException {
        return new ResponseEntity<>(mediaService.deleteSerial(serialTitle),HttpStatus.OK);

    }

    //deleting episodic media by tiel an episode number
    @DeleteMapping("/episode/{title}/{episode-num}")
    public ResponseEntity<?> deleteEpisode(@PathVariable("title") String serialTitle, @PathVariable("episodeNum") int episodeNum) throws MediaNotFoundException {
        return new ResponseEntity<>(mediaService.deleteEpisode(serialTitle, episodeNum),HttpStatus.OK);
    }

    List<String> files = new ArrayList<>();

    //uploading files
    @PostMapping("/post")
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) {
        String message = "";
        try {
            mediaService.store(file);
            files.add(file.getOriginalFilename());

            message = "You successfully uploaded " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.OK).body(message);
        } catch (Exception e) {
            message = "FAIL to upload " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
        }
    }

    @GetMapping("/getallfiles")
    public ResponseEntity<List<String>> getListFiles(Model model) {
        List<String> fileNames = files
                .stream().map(fileName -> MvcUriComponentsBuilder
                        .fromMethodName(MediaController.class, "getFile", fileName).build().toString())
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(fileNames);
    }

    //searching file by filenames
    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getFile(@PathVariable String filename) throws FileNotUploadedException {
        Resource file = mediaService.loadFile(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                .body(file);
    }
}
