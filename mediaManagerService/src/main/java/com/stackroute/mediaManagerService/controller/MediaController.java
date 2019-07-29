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
    public Media saveMedia(@RequestBody Media media){
        Media media1=new Media();
        try{
            media1=mediaService.saveMedia(media);
        }
        catch (MediaAlreadyExistsException exception){
            System.out.println(exception);
        }
        return media1;
    }

    @PostMapping("/serial")
    public EpisodicMedia saveSerial(@RequestBody EpisodicMedia media){
        EpisodicMedia media1=new EpisodicMedia();
        try{
            media1=mediaService.saveSerial(media);
        }
        catch (MediaAlreadyExistsException exception){
            System.out.println(exception);
        }
        return media1;
    }

    @PostMapping("/episode/{serialTitle}")
    public String saveEpisode(@PathVariable("serialTitle") String serialTitle, @RequestBody Episode episode){
        return mediaService.addEpisode(serialTitle,episode);
    }

    @GetMapping("/medias")
    public List<Media> getAllMedias(){
        List<Media> mediaList=new ArrayList<>();
        try{
            mediaList=mediaService.getAllMedia();
        }
        catch (MediaNotFoundException exception){
            System.out.println(exception);
        }
        return mediaList;
    }

    @GetMapping("/serials")
    public List<EpisodicMedia> getAllSerials(){
        List<EpisodicMedia> episodicMediaList=new ArrayList<>();
        try {
            episodicMediaList=mediaService.getAllSerials();
        }
        catch (MediaNotFoundException exception){
            System.out.println(exception);
        }
        return episodicMediaList;
    }

    @GetMapping("/episodes/{title}")
    public List<Episode> getAllEpisodes(@PathVariable("title") String serialTitle){
        return mediaService.getAllEpisodes(serialTitle);
    }

    @GetMapping("/media/{title}")
    public Media getMediaById(@PathVariable("title") String mediaTitle){
        Media media=new Media();
        try{
            media=mediaService.getMediaById(mediaTitle);
        }
        catch (MediaNotFoundException exception){
            System.out.println(exception);
        }
        return media;
    }

    @GetMapping("/serial/{title}")
    public EpisodicMedia getSerialById(@PathVariable("title") String serialTitle){
        EpisodicMedia media=new EpisodicMedia();
        try{
            media=mediaService.getSerialByTitle(serialTitle);
        }
        catch (MediaNotFoundException exception){
            System.out.println(exception);
        }
        return media;
    }

    @GetMapping("/episode/{title}/{episodeNum}")
    public Episode getEpisodeById(@PathVariable("title") String serialTitle,@PathVariable("episodeNum") int episodeNum){
        return mediaService.getEpisodeById(serialTitle, episodeNum);
    }

    @GetMapping("/media/movie/{genre}")
    public List<Media> getMediaByGenre(@PathVariable("genre") String genre){
        return mediaService.getMediaByGenre(genre);
    }

    @GetMapping("/series/tv/{language}")
    public List<EpisodicMedia> getSerialByLanguage(@PathVariable("language") String language){
        return mediaService.getTvSerialByLanguage(language);
    }

    @GetMapping("/media/category/{category}")
    public List<Media> getSpecificCategoryMedia(@PathVariable("category") String category){
        return mediaService.getMediaByCategory(category);
    }

    @GetMapping("/series/category/{category}")
    public List<EpisodicMedia> getEpisodicByCategory(@PathVariable("category") String category){
        return mediaService.getSerialByCategory(category);
    }

    @DeleteMapping("/media/{title}")
    public Media deleteMedia(@PathVariable("title") String mediaTitle){
        Media media=new Media();
        try{
            media=mediaService.deleteMedia(mediaTitle);
        }
        catch (MediaNotFoundException exception){
            System.out.println(exception);
        }
        return media;
    }

    @DeleteMapping("/serial/{title}")
    public EpisodicMedia deleteSerial(@PathVariable("title") String serialTitle){
        EpisodicMedia media=new EpisodicMedia();
        try{
            media=mediaService.deleteSerial(serialTitle);
        }
        catch (MediaNotFoundException exception){
            System.out.println(exception);
        }
        return media;
    }

    @DeleteMapping("/episode/{title}/{episodeNum}")
    public String deleteEpisode(@PathVariable("title") String serialTitle,@PathVariable("episodeNum") int episodeNum){
        return mediaService.deleteEpisode(serialTitle, episodeNum);
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
