package com.stackroute.episodicmediaservice.service;

import com.stackroute.episodicmediaservice.domain.Episode;
import com.stackroute.episodicmediaservice.domain.EpisodicMedia;
import com.stackroute.episodicmediaservice.exception.MediaAlreadyExistsException;
import com.stackroute.episodicmediaservice.exception.MediaNotFoundException;
import com.stackroute.episodicmediaservice.repository.EpisodicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class EpisodicServiceImpl implements EpisodicService {

    @Autowired
    private EpisodicRepository episodicMediaRepository;

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

    @Override
    public List<EpisodicMedia> getAllSerials() throws MediaNotFoundException {
        List<EpisodicMedia> episodicMediaList = episodicMediaRepository.findAll();
        if (episodicMediaList == null) {
            throw new MediaNotFoundException("Media not found");
        } else return episodicMediaList;
    }

    @Override
    public EpisodicMedia getSerialByTitle(String episodicTitle) throws MediaNotFoundException {
        EpisodicMedia media;
        if (episodicMediaRepository.existsById(episodicTitle)) {
            media = episodicMediaRepository.findById(episodicTitle).get();
            return media;
        } else throw new MediaNotFoundException("Media not found");
    }

    @Override
    public EpisodicMedia deleteSerial(String serialTitle) throws MediaNotFoundException {
        EpisodicMedia media;
        if (episodicMediaRepository.existsById(serialTitle)) {
            media = episodicMediaRepository.findById(serialTitle).get();
            episodicMediaRepository.deleteById(serialTitle);
            return media;
        } else throw new MediaNotFoundException("Media not found");
    }

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

    @Override
    public List<EpisodicMedia> getWishlist(List<String> titles) throws MediaNotFoundException {
        List<EpisodicMedia> wishlist=new ArrayList<>();
        for (String title:titles) {
            if(episodicMediaRepository.existsById(title)){
                wishlist.add(episodicMediaRepository.findById(title).get());
            }
        }
        if(wishlist==null){
            throw new MediaNotFoundException(mediaNotFound);
        }
        return wishlist;
    }

}
