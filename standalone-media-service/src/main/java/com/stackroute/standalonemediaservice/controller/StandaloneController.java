package com.stackroute.standalonemediaservice.controller;

import com.stackroute.standalonemediaservice.domain.StandaloneMedia;
import com.stackroute.standalonemediaservice.exception.FileNotUploadedException;
import com.stackroute.standalonemediaservice.exception.MediaAlreadyExistsException;
import com.stackroute.standalonemediaservice.exception.MediaNotFoundException;
import com.stackroute.standalonemediaservice.service.StandaloneService;
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
public class StandaloneController {

    @Autowired
    private StandaloneService mediaService;

    @PostMapping("/media")
    public ResponseEntity<?> saveMedia(@RequestBody StandaloneMedia media) throws MediaAlreadyExistsException {
        StandaloneMedia media1;
        media1=mediaService.saveMedia(media);
        return new ResponseEntity<>(media1, HttpStatus.OK);

    }

    @GetMapping("/medias")
    public ResponseEntity<?> getAllMedias() throws MediaNotFoundException {
        List<StandaloneMedia> mediaList;
        mediaList=mediaService.getAllMedia();
        return new ResponseEntity<>(mediaList,HttpStatus.OK);
    }

    @GetMapping("/media/{title}")
    public ResponseEntity<?> getMediaById(@PathVariable("title") String mediaTitle) throws MediaNotFoundException {
        return new ResponseEntity<>(mediaService.getMediaById(mediaTitle),HttpStatus.OK);
    }

    @GetMapping("/media/movie/{genre}")
    public ResponseEntity<?> getMediaByGenre(@PathVariable("genre") String genre) throws MediaNotFoundException {
        return new ResponseEntity<>(mediaService.getMediaByGenre(genre),HttpStatus.OK);
    }

    @GetMapping("/media/category/{category}")
    public ResponseEntity<?> getSpecificCategoryMedia(@PathVariable("category") String category) throws MediaNotFoundException {
        return new ResponseEntity<>(mediaService.getMediaByCategory(category),HttpStatus.OK);
    }

    @PatchMapping("/wish")
    public ResponseEntity<?> getWishlist(@RequestBody List<String> titles) throws MediaNotFoundException {
        return new ResponseEntity<>(mediaService.getWishlist(titles),HttpStatus.OK);
    }

    @DeleteMapping("/media/{title}")
    public ResponseEntity<?> deleteMedia(@PathVariable("title") String mediaTitle) throws MediaNotFoundException {
        return new ResponseEntity<>(mediaService.deleteMedia(mediaTitle),HttpStatus.OK);
    }

    List<String> files = new ArrayList<>();

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
                        .fromMethodName(StandaloneController.class, "getFile", fileName).build().toString())
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(fileNames);
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getFile(@PathVariable String filename) throws FileNotUploadedException {
        Resource file = mediaService.loadFile(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                .body(file);
    }
}
