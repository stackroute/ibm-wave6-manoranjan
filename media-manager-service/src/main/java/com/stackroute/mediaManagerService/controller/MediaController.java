package com.stackroute.mediaManagerService.controller;

import com.stackroute.mediaManagerService.domain.Episode;
import com.stackroute.mediaManagerService.domain.EpisodicMedia;
import com.stackroute.mediaManagerService.domain.Media;
import com.stackroute.mediaManagerService.exceptions.FileNotUploadedException;
import com.stackroute.mediaManagerService.exceptions.MediaAlreadyExistsException;
import com.stackroute.mediaManagerService.exceptions.MediaNotFoundException;
import com.stackroute.mediaManagerService.service.MediaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Api(value = "MediaControllerApi",produces = MediaType.APPLICATION_JSON_VALUE)
@RequestMapping(value = "/stream/v1")
@CrossOrigin("*")
public class MediaController {

    @Autowired
    private MediaService mediaService;

    //posting standalone media
    @PostMapping("/media")
    @ApiOperation("Save media")
    @ApiResponses(value = {@ApiResponse(code = 200,message = "OK",response = Media.class)})
    public ResponseEntity<?> saveMedia(@RequestBody Media media) throws MediaAlreadyExistsException {
        Media media1;
        media1=mediaService.saveMedia(media);
        return new ResponseEntity<>(media1,HttpStatus.OK);

    }

    //posting episodic media
    @PostMapping("/serial")
    @ApiOperation("Save serial")
    @ApiResponses(value = {@ApiResponse(code = 200,message = "OK",response = EpisodicMedia.class)})
    public ResponseEntity<?> saveSerial(@RequestBody EpisodicMedia media) throws MediaAlreadyExistsException {
        EpisodicMedia media1;
        media1=mediaService.saveSerial(media);
        return new ResponseEntity<>(media1,HttpStatus.OK);
    }

    //posting single episode
    @PostMapping("/episode/{serial-title}")
    @ApiOperation("Save episode")
    @ApiResponses(value = {@ApiResponse(code = 200,message = "OK")})
    public ResponseEntity<?> saveEpisode(@PathVariable("serial-title") String serialTitle, @RequestBody Episode episode) throws MediaNotFoundException, MediaAlreadyExistsException {
        return new ResponseEntity<>(mediaService.addEpisode(serialTitle, episode), HttpStatus.OK);
    }

    //getting all the standalone media
    @GetMapping("/medias")
    @ApiOperation("Get all medias ")
    @ApiResponses(value = {@ApiResponse(code = 200,message = "OK")})
    public ResponseEntity<?> getAllMedias() throws MediaNotFoundException {
        List<Media> mediaList;
        mediaList=mediaService.getAllMedia();
        return new ResponseEntity<>(mediaList,HttpStatus.OK);
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

    //searching single standalone media by title
    @GetMapping("/media/{title}")
    @ApiOperation("Get media by id ")
    @ApiResponses(value = {@ApiResponse(code = 200,message = "OK")})
    public ResponseEntity<?> getMediaById(@PathVariable("title") String mediaTitle) throws MediaNotFoundException {
        return new ResponseEntity<>(mediaService.getMediaById(mediaTitle),HttpStatus.OK);
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

    //searching the standalone media by genre
    @GetMapping("/media/movie/{genre}")
    @ApiOperation("Get media by genre ")
    @ApiResponses(value = {@ApiResponse(code = 200,message = "OK")})
    public ResponseEntity<?> getMediaByGenre(@PathVariable("genre") String genre) throws MediaNotFoundException {
        return new ResponseEntity<>(mediaService.getMediaByGenre(genre),HttpStatus.OK);
    }

    //searching the episodic media by language
    @GetMapping("/series/tv/{language}")
    @ApiOperation("Get serial by language")
    @ApiResponses(value = {@ApiResponse(code = 200,message = "OK")})
    public ResponseEntity<?> getSerialByLanguage(@PathVariable("language") String language) throws MediaNotFoundException {
        return new ResponseEntity<>(mediaService.getTvSerialByLanguage(language),HttpStatus.OK);
    }

    //searching the standalone media by category
    @GetMapping("/media/category/{category}")
    @ApiOperation("Get medias of spacific category ")
    @ApiResponses(value = {@ApiResponse(code = 200,message = "OK")})
    public ResponseEntity<?> getSpecificCategoryMedia(@PathVariable("category") String category) throws MediaNotFoundException {
        return new ResponseEntity<>(mediaService.getMediaByCategory(category),HttpStatus.OK);
    }

    //searching the episodic media by category
    @GetMapping("/series/category/{category}")
    @ApiOperation("Get episode by category ")
    @ApiResponses(value = {@ApiResponse(code = 200,message = "OK")})
    public ResponseEntity<?> getEpisodicByCategory(@PathVariable("category") String category) throws MediaNotFoundException {
        return new ResponseEntity<>(mediaService.getSerialByCategory(category),HttpStatus.OK);
    }

    //displaying all the standalone media
    @PostMapping("/media/list")
    @ApiOperation("Get emedia list ")
    @ApiResponses(value = {@ApiResponse(code = 200,message = "OK")})
    public ResponseEntity<?> getMediaList(@RequestBody List<List<String>> medias){
        return new ResponseEntity<>(mediaService.getMediaList(medias),HttpStatus.OK);
    }

    //deleting standalone media by title
    @DeleteMapping("/media/{title}")
    @ApiOperation("Delete media")
    @ApiResponses(value = {@ApiResponse(code = 200,message = "OK")})
    public ResponseEntity<?> deleteMedia(@PathVariable("title") String mediaTitle) throws MediaNotFoundException {
        return new ResponseEntity<>(mediaService.deleteMedia(mediaTitle),HttpStatus.OK);
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

    List<String> files = new ArrayList<>();

    //uploading files
    @PostMapping("/post")
    @ApiOperation("handle file upload")
    @ApiResponses(value = {@ApiResponse(code = 200,message = "OK")})
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
    @ApiOperation("Get list of files ")
    @ApiResponses(value = {@ApiResponse(code = 200,message = "OK")})
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
