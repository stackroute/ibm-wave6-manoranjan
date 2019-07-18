package com.stackroute.mediaManagerService.service;

import com.stackroute.mediaManagerService.domain.Episode;
import com.stackroute.mediaManagerService.domain.EpisodicMedia;
import com.stackroute.mediaManagerService.domain.Media;
import com.stackroute.mediaManagerService.exceptions.MediaAlreadyExistsException;
import com.stackroute.mediaManagerService.exceptions.MediaNotFoundException;
import com.stackroute.mediaManagerService.repository.MediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MediaService {

    public List<Media> getAllMedia() throws MediaNotFoundException;
    public Media getMediaById(String mediaTitle) throws MediaNotFoundException;
    public Media saveMedia(Media media) throws MediaAlreadyExistsException;
    public Media updateDetails(Media media);
    public Media deleteMedia(String mediaTitle) throws MediaNotFoundException;
    public List<Media> getMediaByGenre(String Genre);

    public EpisodicMedia saveSerial(EpisodicMedia serial) throws MediaAlreadyExistsException;
    public List<EpisodicMedia> getAllSerials() throws MediaNotFoundException;
    public EpisodicMedia getSerialByTitle(String episodicTitle) throws MediaNotFoundException;
    public EpisodicMedia deleteSerial(String serialTitle) throws MediaNotFoundException;

    public String addEpisode(String serialTitle,Episode episode);
    public String deleteEpisode(String serialTitle,int episodeNumber);
    public Episode getEpisodeById(String serialTitle,int episodeNumber);
    public List<Episode> getAllEpisodes(String serialTitle);

    public void store(MultipartFile file);
    public Resource loadFile(String filename);
    public void deleteAll();
    public void init();
}
