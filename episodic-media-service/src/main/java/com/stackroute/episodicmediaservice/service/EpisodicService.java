package com.stackroute.episodicmediaservice.service;

import com.stackroute.episodicmediaservice.domain.Episode;
import com.stackroute.episodicmediaservice.domain.EpisodicMedia;
import com.stackroute.episodicmediaservice.exception.MediaAlreadyExistsException;
import com.stackroute.episodicmediaservice.exception.MediaNotFoundException;

import java.util.List;

public interface EpisodicService {

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

    //fetching all the media in wishlist
    public List<EpisodicMedia> getWishlist(List<String> titles) throws MediaNotFoundException;

}
