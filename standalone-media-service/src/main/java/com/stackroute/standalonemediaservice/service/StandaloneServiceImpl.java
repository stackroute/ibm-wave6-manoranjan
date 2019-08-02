package com.stackroute.standalonemediaservice.service;

import com.stackroute.standalonemediaservice.domain.StandaloneMedia;
import com.stackroute.standalonemediaservice.exception.MediaAlreadyExistsException;
import com.stackroute.standalonemediaservice.exception.MediaNotFoundException;
import com.stackroute.standalonemediaservice.repository.StandaloneRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import org.slf4j.Logger;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class StandaloneServiceImpl implements StandaloneService{

    @Autowired
    private StandaloneRepository mediaRepository;

    @Autowired
    KafkaTemplate<StandaloneMedia,StandaloneMedia> kafkaTemplate;

    private static String topic = "saveMedia";
    private static String topic1 = "saveEpisodicMedia";
    private static String topic2 = "saveEpisode";

    private String mediaNotFound="Media not found";
    private String mediaAlreadyExist="Media already exists";
    private String fail="Fail";


    Logger log = LoggerFactory.getLogger(this.getClass().getName());
    private final Path rootLocation = Paths.get("/home/sakshi/stackroute/manoranjan-task/red5-server-1.1.0/red5-server/webapps/vod/streams");

    @Override
    public List<StandaloneMedia> getAllMedia() throws MediaNotFoundException {
        List<StandaloneMedia> medias = mediaRepository.findAll();
        if (medias == null) {
            throw new MediaNotFoundException("Media not found");
        } else return medias;
    }

    @Override
    public StandaloneMedia getMediaById(String mediaTitle) throws MediaNotFoundException {
        if (mediaRepository.existsById(mediaTitle)) {
            return mediaRepository.findById(mediaTitle).get();
        }
        else throw new MediaNotFoundException("Media not found");
    }

    @Override
    public StandaloneMedia saveMedia(StandaloneMedia media) throws MediaAlreadyExistsException {
        StandaloneMedia media1;
        if (mediaRepository.existsById(media.getMediaTitle())) {
            throw new MediaAlreadyExistsException("Media already exists");
        } else {
            media1 = mediaRepository.save(media);
            if (media1 == null) {
                throw new MediaAlreadyExistsException("Media already exists");
            }
        }
        kafkaTemplate.send(topic, media1);
        return media1;
    }

    @Override
    public StandaloneMedia deleteMedia(String mediaTitle) throws MediaNotFoundException {
        StandaloneMedia media;
        if (mediaRepository.existsById(mediaTitle)) {
            media = mediaRepository.findById(mediaTitle).get();
            mediaRepository.deleteById(mediaTitle);
            return media;
        } else throw new MediaNotFoundException("Media not found");
    }

    @Override
    public List<StandaloneMedia> getMediaByGenre(String genre) throws MediaNotFoundException {
        List<StandaloneMedia> allMedia = mediaRepository.findAll();
        if (allMedia == null) {
            throw new MediaNotFoundException("Media not found");
        } else {
            List<StandaloneMedia> genreMedia = new ArrayList<>();
            for (StandaloneMedia media : allMedia) {
                if (media.getMediaCategory().equals("Movie")) {
                    if (media.getMediaGenre().contains(genre)) {
                        genreMedia.add(media);
                    }
                }
            }
            if (genreMedia == null) {
                throw new MediaNotFoundException("Media not found");
            } else return genreMedia;
        }
    }

    @Override
    public List<StandaloneMedia> getMediaByCategory(String category) throws MediaNotFoundException {
        List<StandaloneMedia> allMedia = mediaRepository.findAll();
        if (allMedia == null) {
            throw new MediaNotFoundException("Media not found");
        } else {
            List<StandaloneMedia> catMedia = new ArrayList<>();
            for (StandaloneMedia media : allMedia) {
                if (media.getMediaCategory().equals(category)) {
                    catMedia.add(media);
                }
            }
            if (catMedia == null) {
                throw new MediaNotFoundException("Media not found");
            } else return catMedia;
        }
    }

    @Override
    public List<StandaloneMedia> getWishlist(List<String> titles) throws MediaNotFoundException {
        List<StandaloneMedia> wishlist=new ArrayList<>();
        for (String title:titles) {
            if(mediaRepository.existsById(title)){
                wishlist.add(mediaRepository.findById(title).get());
            }
        }
        if(wishlist==null){
            throw new MediaNotFoundException(mediaNotFound);
        }
        return wishlist;
    }


    public void store(MultipartFile file) {
        try {
            Files.copy(file.getInputStream(), this.rootLocation.resolve(file.getOriginalFilename()));
        } catch (Exception e) {
            throw new RuntimeException("FAIL!");
        }
    }

    public Resource loadFile(String filename) {
        try {
            Path file = rootLocation.resolve(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("FAIL!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("FAIL!");
        }
    }

    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }

    public void init() {
        try {
            Files.createDirectory(rootLocation);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize storage!");
        }
    }
}
