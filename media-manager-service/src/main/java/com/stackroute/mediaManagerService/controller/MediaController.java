package com.stackroute.mediaManagerService.controller;

import com.stackroute.mediaManagerService.domain.Episode;
import com.stackroute.mediaManagerService.domain.EpisodicMedia;
import com.stackroute.mediaManagerService.domain.Media;
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

    @PostMapping("/media")
    public ResponseEntity<?> saveMedia(@RequestBody Media media) throws MediaAlreadyExistsException {
        Media media1;
        media1=mediaService.saveMedia(media);
        return new ResponseEntity<Media>(media1,HttpStatus.OK);
    }

    @PostMapping("/serial")
    public ResponseEntity<?> saveSerial(@RequestBody EpisodicMedia media) throws MediaAlreadyExistsException {
        EpisodicMedia media1=new EpisodicMedia();
        media1=mediaService.saveSerial(media);
        return new ResponseEntity<EpisodicMedia>(media1,HttpStatus.OK);
    }

    @PostMapping("/episode/{serial-title}")
    public ResponseEntity<?> saveEpisode(@PathVariable("serial-title") String serialTitle, @RequestBody Episode episode) throws MediaNotFoundException, MediaAlreadyExistsException {
        return new ResponseEntity<>(mediaService.addEpisode(serialTitle,episode),HttpStatus.OK);
    }

    @GetMapping("/medias")
    public ResponseEntity<?> getAllMedias() throws MediaNotFoundException {
        List<Media> mediaList;
        mediaList=mediaService.getAllMedia();
        return new ResponseEntity<List<Media>>(mediaList,HttpStatus.OK);
    }

    @GetMapping("/serials")
    public ResponseEntity<?> getAllSerials() throws MediaNotFoundException {
        List<EpisodicMedia> episodicMediaList;
        episodicMediaList=mediaService.getAllSerials();
        return new ResponseEntity<List<EpisodicMedia>>(episodicMediaList,HttpStatus.OK);
    }

    @GetMapping("/episodes/{title}")
    public ResponseEntity<?> getAllEpisodes(@PathVariable("title") String serialTitle) throws MediaNotFoundException {
        return new ResponseEntity<List<Episode>>(mediaService.getAllEpisodes(serialTitle),HttpStatus.OK);
    }

    @GetMapping("/media/{title}")
    public ResponseEntity<?> getMediaById(@PathVariable("title") String mediaTitle) throws MediaNotFoundException {
        return new ResponseEntity<Media>(mediaService.getMediaById(mediaTitle),HttpStatus.OK);
    }

    @GetMapping("/serial/{title}")
    public ResponseEntity<?> getSerialById(@PathVariable("title") String serialTitle) throws MediaNotFoundException {
        return new ResponseEntity<EpisodicMedia>(mediaService.getSerialByTitle(serialTitle),HttpStatus.OK);
    }

    @GetMapping("/episode/{title}/{episode-num}")
    public ResponseEntity<?> getEpisodeById(@PathVariable("title") String serialTitle,@PathVariable("episodeNum") int episodeNum) throws MediaNotFoundException {
        return new ResponseEntity<>(mediaService.getEpisodeById(serialTitle, episodeNum),HttpStatus.OK);
    }

    @GetMapping("/media/movie/{genre}")
    public ResponseEntity<?> getMediaByGenre(@PathVariable("genre") String genre) throws MediaNotFoundException {
        return new ResponseEntity<List<Media>>(mediaService.getMediaByGenre(genre),HttpStatus.OK);
    }

    @GetMapping("/series/tv/{language}")
    public ResponseEntity<?> getSerialByLanguage(@PathVariable("language") String language) throws MediaNotFoundException {
        return new ResponseEntity<List<EpisodicMedia>>(mediaService.getTvSerialByLanguage(language),HttpStatus.OK);
    }

    @GetMapping("/media/category/{category}")
    public ResponseEntity<?> getSpecificCategoryMedia(@PathVariable("category") String category) throws MediaNotFoundException {
        return new ResponseEntity<List<Media>>(mediaService.getMediaByCategory(category),HttpStatus.OK);
    }

    @GetMapping("/series/category/{category}")
    public ResponseEntity<?> getEpisodicByCategory(@PathVariable("category") String category) throws MediaNotFoundException {
        return new ResponseEntity<List<EpisodicMedia>>(mediaService.getSerialByCategory(category),HttpStatus.OK);
    }

    @PostMapping("/media/list")
    public ResponseEntity<?> getMediaList(@RequestBody List<List<String>> medias){
        return new ResponseEntity<List<Object>>(mediaService.getMediaList(medias),HttpStatus.OK);
    }

    @DeleteMapping("/media/{title}")
    public ResponseEntity<?> deleteMedia(@PathVariable("title") String mediaTitle) throws MediaNotFoundException {
        return new ResponseEntity<Media>(mediaService.deleteMedia(mediaTitle),HttpStatus.OK);
    }

    @DeleteMapping("/serial/{title}")
    public ResponseEntity<?> deleteSerial(@PathVariable("title") String serialTitle) throws MediaNotFoundException {
        return new ResponseEntity<EpisodicMedia>(mediaService.deleteSerial(serialTitle),HttpStatus.OK);

    }

    @DeleteMapping("/episode/{title}/{episode-num}")
    public ResponseEntity<?> deleteEpisode(@PathVariable("title") String serialTitle, @PathVariable("episodeNum") int episodeNum) throws MediaNotFoundException {
        return new ResponseEntity<Episode>(mediaService.deleteEpisode(serialTitle, episodeNum),HttpStatus.OK);
    }

    List<String> files = new ArrayList<String>();

    @PostMapping("/post")
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) {
        String message = "";
        try {
            mediaService.store(file);
            files.add(file.getOriginalFilename());

            message = "You successfully uploaded " + file.getOriginalFilename() + "!";
            System.out.println("message:"+message);
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

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        Resource file = mediaService.loadFile(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                .body(file);
    }
}
