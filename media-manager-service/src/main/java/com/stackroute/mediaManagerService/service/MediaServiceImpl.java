package com.stackroute.mediaManagerService.service;

import com.stackroute.mediaManagerService.domain.Episode;
import com.stackroute.mediaManagerService.domain.EpisodicMedia;
import com.stackroute.mediaManagerService.domain.Media;
import com.stackroute.mediaManagerService.exceptions.FileNotUploadedException;
import com.stackroute.mediaManagerService.exceptions.MediaAlreadyExistsException;
import com.stackroute.mediaManagerService.exceptions.MediaNotFoundException;
import com.stackroute.mediaManagerService.repository.EpisodicMediaRepository;
import com.stackroute.mediaManagerService.repository.MediaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.kafka.core.KafkaTemplate;
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

    @Autowired
    KafkaTemplate<Media, Media> kafkaTemplate;

    @Autowired
    KafkaTemplate<EpisodicMedia, EpisodicMedia> kafkaTemplate1;

    @Autowired
    KafkaTemplate<Episode, Episode> kafkaTemplate2;

    private static String topic = "saveMedia";
    private static String topic1 = "saveEpisodicMedia";
    private static String topic2 = "saveEpisode";

    private String mediaNotFound="Media not found";
    private String mediaAlreadyExist="Media already exists";
    private String fail="Fail";


    Logger log = LoggerFactory.getLogger(this.getClass().getName());
    private final Path rootLocation = Paths.get("/home/sakshi/stackroute/manoranjan-task/red5-server-1.1.0/red5-server/webapps/vod/streams");

    //displaying all the standalone media
    @Override
    public List<Media> getAllMedia() throws MediaNotFoundException {
        List<Media> medias = mediaRepository.findAll();
        if (medias == null) {
            throw new MediaNotFoundException("Media not found");
        } else return medias;
    }

    //searching standalone media by passing mediaTitle
    @Override
    public Media getMediaById(String mediaTitle) throws MediaNotFoundException {
        if (mediaRepository.existsById(mediaTitle)) {
            return mediaRepository.findById(mediaTitle).get();
        } else throw new MediaNotFoundException("Media not found");
    }

    //saving standalone media by sending media object
    @Override
    public Media saveMedia(Media media) throws MediaAlreadyExistsException {
        Media media1;
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

    //deleting statndalone media by media tilte
    @Override
    public Media deleteMedia(String mediaTitle) throws MediaNotFoundException {
        Media media;
        if (mediaRepository.existsById(mediaTitle)) {
            media = mediaRepository.findById(mediaTitle).get();
            mediaRepository.deleteById(mediaTitle);
            return media;
        } else throw new MediaNotFoundException("Media not found");
    }

    //seraching standalone media by genre
    @Override
    public List<Media> getMediaByGenre(String genre) throws MediaNotFoundException {
        List<Media> allMedia = mediaRepository.findAll();
        if (allMedia == null) {
            throw new MediaNotFoundException("Media not found");
        } else {
            List<Media> genreMedia = new ArrayList<>();
            for (Media media : allMedia) {
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

    //searching standalone media by category
    @Override
    public List<Media> getMediaByCategory(String category) throws MediaNotFoundException {
        List<Media> allMedia = mediaRepository.findAll();
        if (allMedia == null) {
            throw new MediaNotFoundException("Media not found");
        } else {
            List<Media> catMedia = new ArrayList<>();
            for (Media media : allMedia) {
                if (media.getMediaCategory().equals(category)) {
                    catMedia.add(media);
                }
            }
            if (catMedia == null) {
                throw new MediaNotFoundException("Media not found");
            } else return catMedia;
        }
    }

    //saving episodic media by sending episodic media object
    @Override
    public EpisodicMedia saveSerial(EpisodicMedia serial) throws MediaAlreadyExistsException {
        EpisodicMedia media;
        if (episodicMediaRepository.existsById(serial.getEpisodicTitle())) {
            throw new MediaAlreadyExistsException("Media already exists");
        } else {
            media = episodicMediaRepository.save(serial);
        }
        if (media == null) {
            throw new MediaAlreadyExistsException("Media already exists");
        }
        kafkaTemplate1.send(topic1, serial);
        return media;
    }

    //displaying all episodic media
    @Override
    public List<EpisodicMedia> getAllSerials() throws MediaNotFoundException {
        List<EpisodicMedia> episodicMediaList = episodicMediaRepository.findAll();
        if (episodicMediaList == null) {
            throw new MediaNotFoundException("Media not found");
        } else return episodicMediaList;
    }

    //seraching episodic media by episodicTitle
    @Override
    public EpisodicMedia getSerialByTitle(String episodicTitle) throws MediaNotFoundException {
        EpisodicMedia media;
        if (episodicMediaRepository.existsById(episodicTitle)) {
            media = episodicMediaRepository.findById(episodicTitle).get();
            return media;
        } else throw new MediaNotFoundException("Media not found");
    }

    //deleting episodic media by serialTitle
    @Override
    public EpisodicMedia deleteSerial(String serialTitle) throws MediaNotFoundException {
        EpisodicMedia media;
        if (episodicMediaRepository.existsById(serialTitle)) {
            media = episodicMediaRepository.findById(serialTitle).get();
            episodicMediaRepository.deleteById(serialTitle);
            return media;
        } else throw new MediaNotFoundException("Media not found");
    }

    //seraching episodic media by category
    @Override
    public List<EpisodicMedia> getSerialByCategory(String category) throws MediaNotFoundException {
        List<EpisodicMedia> allSerials = episodicMediaRepository.findAll();
        if (allSerials == null) {
            throw new MediaNotFoundException("Media not found");
        } else {
            List<EpisodicMedia> catSerials = new ArrayList<>();
            for (EpisodicMedia serial : allSerials) {
                if (serial.getEpisodicCategory().equals(category)) {
                    catSerials.add(serial);
                }
            }
            if (catSerials == null) {
                throw new MediaNotFoundException("Media not found");
            } else return catSerials;
        }
    }

    //searching episodic media by language
    @Override
    public List<EpisodicMedia> getTvSerialByLanguage(String language) throws MediaNotFoundException {
        List<EpisodicMedia> allSerials = episodicMediaRepository.findAll();
        if (allSerials == null) {
            throw new MediaNotFoundException("Media not found");
        } else {
            List<EpisodicMedia> lanSerials = new ArrayList<>();
            for (EpisodicMedia serial : allSerials) {
                if (serial.getEpisodicCategory().equals("TV Episodes")) {
                    if (serial.getEpisodicLanguage().equals(language)) {
                        lanSerials.add(serial);
                    }
                }
            }
            if (lanSerials == null) {
                throw new MediaNotFoundException("Media not found");
            } else return lanSerials;
        }
    }

    //adding episode to episodic media
    @Override
    public Episode addEpisode(String serialTitle, Episode episode) throws MediaAlreadyExistsException, MediaNotFoundException {
        if (episodicMediaRepository.existsById(serialTitle)) {
            EpisodicMedia media = episodicMediaRepository.findById(serialTitle).get();
            List<Episode> episodes = media.getEpisodeList();
            for (Episode i : episodes) {
                if (i.getEpisodeNo() == episode.getEpisodeNo()) {
                    throw new MediaAlreadyExistsException("Media already exists");
                }
            }
            episodes.add(episode);
            media.setEpisodeList(episodes);
            episodicMediaRepository.save(media);
            kafkaTemplate2.send(topic2, episode);
            return episode;
        } else throw new MediaNotFoundException("Media not found");
    }

    //deleting episode from episodic media by serialTitle and episodeNumber
    @Override
    public Episode deleteEpisode(String serialTitle, int episodeNumber) throws MediaNotFoundException {
        if (episodicMediaRepository.existsById(serialTitle)) {
            EpisodicMedia media = episodicMediaRepository.findById(serialTitle).get();
            List<Episode> episodes = media.getEpisodeList();
            int flag = 0;
            Episode episode = new Episode();
            for (Episode i : episodes) {
                if (i.getEpisodeNo() == episodeNumber) {
                    episode = i;
                    flag = 1;
                    break;
                }
            }
            if (flag == 0) {
                throw new MediaNotFoundException("Media not found");
            } else {
                episodes.remove(episode);
                media.setEpisodeList(episodes);
                episodicMediaRepository.save(media);
                return episode;
            }
        } else throw new MediaNotFoundException("Media not found");
    }

    //searching episode by serialTitle and episodeNumber
    @Override
    public Episode getEpisodeById(String serialTitle, int episodeNumber) throws MediaNotFoundException {
        if (episodicMediaRepository.existsById(serialTitle)) {
            EpisodicMedia media = episodicMediaRepository.findById(serialTitle).get();
            List<Episode> episodes = media.getEpisodeList();

            Episode episode = new Episode();
            int flag = 0;
            for (Episode i : episodes) {
                if (i.getEpisodeNo() == episodeNumber) {
                    episode = i;
                    flag = 1;
                    break;
                }
            }
            if (flag == 0) {
                throw new MediaNotFoundException("Media not found");
            } else return episode;
        } else throw new MediaNotFoundException("Media not found");
    }

    @Override
    public List<Episode> getAllEpisodes(String serialTitle) throws MediaNotFoundException {
        if (episodicMediaRepository.existsById(serialTitle)) {
            EpisodicMedia media = episodicMediaRepository.findById(serialTitle).get();
            List<Episode> medias = media.getEpisodeList();
            if (medias == null) {
                throw new MediaNotFoundException("Media not found");
            } else return medias;
        } else {
            throw new MediaNotFoundException("Media not found");
        }
    }

    //getting all the mediaList
    @Override
    public List<Object> getMediaList(List<List<String>> mediaList) {
        List<Object> medias = new ArrayList<>();
        for (List<String> item :
                mediaList) {
            String title = item.get(0);
            String category = item.get(1);

            if (mediaRepository.existsById(title)) {
                Media media = mediaRepository.findById(title).get();
                if (media.getMediaCategory().equals(category)) {
                    medias.add(media);
                }
            } else if (episodicMediaRepository.existsById(title)) {
                EpisodicMedia media = episodicMediaRepository.findById(title).get();
                if (media.getEpisodicCategory().equals(category)) {
                    medias.add(media);
                }
            }
        }
        return medias;
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

    //deleting all media from rootLocation
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }

    //creating rootLocation directory
    public void init() {
        try {
            Files.createDirectory(rootLocation);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize storage!");
        }
    }
}
