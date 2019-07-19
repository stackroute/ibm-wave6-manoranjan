package com.stackroute.mediaManagerService.service;

import com.stackroute.mediaManagerService.domain.Episode;
import com.stackroute.mediaManagerService.domain.EpisodicMedia;
import com.stackroute.mediaManagerService.domain.Media;
import com.stackroute.mediaManagerService.exceptions.MediaAlreadyExistsException;
import com.stackroute.mediaManagerService.exceptions.MediaNotFoundException;
import com.stackroute.mediaManagerService.repository.EpisodicMediaRepository;
import com.stackroute.mediaManagerService.repository.MediaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class MediaServiceImpl implements MediaService {

    @Autowired
    private MediaRepository mediaRepository;

    @Autowired
    private EpisodicMediaRepository episodicMediaRepository;


    Logger log = LoggerFactory.getLogger(this.getClass().getName());
    private final Path rootLocation = Paths.get("../../../../../../../../red5-server-1.1.0/red5-server/webapps/vod/streams");

    @Override
    public List<Media> getAllMedia() throws MediaNotFoundException{
        List<Media> medias=mediaRepository.findAll();
        if(medias.isEmpty()){
            throw new MediaNotFoundException("Media not found");
        }
        else return medias;
    }

    @Override
    public Media getMediaById(String mediaTitle) throws MediaNotFoundException {
        if(mediaRepository.existsById(mediaTitle)){
            return mediaRepository.findById(mediaTitle).get();
        }
        else throw new MediaNotFoundException("Media not found");
    }

    @Override
    public Media saveMedia(Media media) throws MediaAlreadyExistsException {
        Media media1;
        if(mediaRepository.existsById(media.getMediaTitle())){
            throw new MediaAlreadyExistsException("Media already exists");
        }
        else {
            media1= mediaRepository.save(media);
        }
        return media1;
    }

    @Override
    public Media updateDetails(Media media) {
        return null;
    }

    @Override
    public Media deleteMedia(String mediaTitle) throws MediaNotFoundException {
        Media media;
        if(mediaRepository.existsById(mediaTitle)){
            media=mediaRepository.findById(mediaTitle).get();
            mediaRepository.deleteById(mediaTitle);
            return media;
        }
        else throw new MediaNotFoundException("Media not found");
    }

    @Override
    public List<Media> getMediaByGenre(String genre) {
        List<Media> allMedia=mediaRepository.findAll();
        List<Media> genreMedia=new ArrayList<>();
        for (Media media:allMedia
             ) {
            if(media.getMediaGenre().contains(genre)){
                genreMedia.add(media);
            }
        }
        return genreMedia;
    }

    @Override
    public EpisodicMedia saveSerial(EpisodicMedia serial) throws MediaAlreadyExistsException {
        if(episodicMediaRepository.existsById(serial.getEpisodicTitle())){
            throw new MediaAlreadyExistsException("Media Already Exist");
        }
        else {
            episodicMediaRepository.save(serial);
        }
        return serial;
    }

    @Override
    public List<EpisodicMedia> getAllSerials() throws MediaNotFoundException {
        List<EpisodicMedia> episodicMediaList=episodicMediaRepository.findAll();
        if(episodicMediaList.isEmpty()){
            throw new MediaNotFoundException("Media not found");
        }
        else return episodicMediaList;
    }

    @Override
    public EpisodicMedia getSerialByTitle(String episodicTitle) throws MediaNotFoundException {
        EpisodicMedia media=new EpisodicMedia();
        if(episodicMediaRepository.existsById(episodicTitle)){
            media=episodicMediaRepository.findById(episodicTitle).get();
            return media;
        }
        else throw new MediaNotFoundException("Media not found");
    }

    @Override
    public EpisodicMedia deleteSerial(String serialTitle) throws MediaNotFoundException {
        EpisodicMedia media;
        if(episodicMediaRepository.existsById(serialTitle)){
            media=episodicMediaRepository.findById(serialTitle).get();
            episodicMediaRepository.deleteById(serialTitle);
            return media;
        }
        else throw new MediaNotFoundException("Media not found");
    }

    @Override
    public String addEpisode(String serialTitle, Episode episode) {
        EpisodicMedia media=episodicMediaRepository.findById(serialTitle).get();
        List<Episode> episodes=media.getEpisodeList();
        for (Episode i:
             episodes) {
            if(i.getEpisodeNo()==episode.getEpisodeNo()){
                return "Episode already exist";
            }
        }
        episodes.add(episode);
        media.setEpisodeList(episodes);
        episodicMediaRepository.save(media);
        return "Episode added";
    }

    @Override
    public String deleteEpisode(String serialTitle, int episodeNumber) {
        EpisodicMedia media=episodicMediaRepository.findById(serialTitle).get();
        List<Episode> episodes=media.getEpisodeList();
        Episode episode=new Episode();
        for (Episode i:
             episodes) {
            if(i.getEpisodeNo()==episodeNumber){
                episode=i;
                break;
            }
        }
        episodes.remove(episode);
        media.setEpisodeList(episodes);
        episodicMediaRepository.save(media);
        return "Episode deleted";
    }

    @Override
    public Episode getEpisodeById(String serialTitle, int episodeNumber) {
        EpisodicMedia media=episodicMediaRepository.findById(serialTitle).get();
        List<Episode> episodes=media.getEpisodeList();

        Episode episode=new Episode();
        for (Episode i:
                episodes) {
            if(i.getEpisodeNo()==episodeNumber){
                episode=i;
                break;
            }
        }
        return episode;
    }

    @Override
    public List<Episode> getAllEpisodes(String serialTitle) {
        EpisodicMedia media=episodicMediaRepository.findById(serialTitle).get();
        return media.getEpisodeList();
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
