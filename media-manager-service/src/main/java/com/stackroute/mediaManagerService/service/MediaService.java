package com.stackroute.mediaManagerService.service;

import com.stackroute.mediaManagerService.domain.Episode;
import com.stackroute.mediaManagerService.domain.EpisodicMedia;
import com.stackroute.mediaManagerService.domain.Media;
import com.stackroute.mediaManagerService.exceptions.FileNotUploadedException;
import com.stackroute.mediaManagerService.exceptions.MediaAlreadyExistsException;
import com.stackroute.mediaManagerService.exceptions.MediaNotFoundException;
import com.stackroute.mediaManagerService.repository.MediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MediaService {

    //getting all the standalone media
    public List<Media> getAllMedia() throws MediaNotFoundException;

    //getting the standalone media by mediaTitle
    public Media getMediaById(String mediaTitle) throws MediaNotFoundException;

    //added new standalone media
    public Media saveMedia(Media media) throws MediaAlreadyExistsException;

    //deleting media by media Title
    public Media deleteMedia(String mediaTitle) throws MediaNotFoundException;

    //fetching standalone media details by genre
    public List<Media> getMediaByGenre(String Genre) throws MediaNotFoundException;

    //fetching standalone media by category
    public List<Media> getMediaByCategory(String category) throws MediaNotFoundException;

    //adding episodic media
    public EpisodicMedia saveSerial(EpisodicMedia serial) throws MediaAlreadyExistsException;

    //fetching all the episodic media
    public List<EpisodicMedia> getAllSerials() throws MediaNotFoundException;

    //fetching episodic media by episodic Title
    public EpisodicMedia getSerialByTitle(String episodicTitle) throws MediaNotFoundException;

    //deleting episode by episode title
    public EpisodicMedia deleteSerial(String serialTitle) throws MediaNotFoundException;

    //fetching episodic media by category
    public List<EpisodicMedia> getSerialByCategory(String category) throws MediaNotFoundException;

    //fetching episodic media by language
    public List<EpisodicMedia> getTvSerialByLanguage(String language) throws MediaNotFoundException;

    //adding episode
    public Episode addEpisode(String serialTitle, Episode episode) throws MediaAlreadyExistsException, MediaNotFoundException;

    //deleting episode by episode title and episode number
    public Episode deleteEpisode(String serialTitle, int episodeNumber) throws MediaNotFoundException;

    //fetching the episode by serailTitle and episode Number
    public Episode getEpisodeById(String serialTitle, int episodeNumber) throws MediaNotFoundException;

    //fetching all the episodes
    public List<Episode> getAllEpisodes(String serialTitle) throws MediaNotFoundException;

    //fetching all the media list
    public List<Object> getMediaList(List<List<String>> mediaList);

    //adding file to a directory
    public void store(MultipartFile file) throws FileNotUploadedException;

    //loading resource file
    public Resource loadFile(String filename) throws FileNotUploadedException;

    //deleting all files from directory
    public void deleteAll();

    //initializing
    public void init() throws FileNotUploadedException;

}
