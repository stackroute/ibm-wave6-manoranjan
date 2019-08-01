package com.stackroute.episodicmediaservice.service;

import com.stackroute.episodicmediaservice.domain.Episode;
import com.stackroute.episodicmediaservice.domain.EpisodicMedia;
import com.stackroute.episodicmediaservice.exception.MediaAlreadyExistsException;
import com.stackroute.episodicmediaservice.exception.MediaNotFoundException;

import java.util.List;

public interface EpisodicService {
    public EpisodicMedia saveSerial(EpisodicMedia serial) throws MediaAlreadyExistsException;

    public List<EpisodicMedia> getAllSerials() throws MediaNotFoundException;

    public EpisodicMedia getSerialByTitle(String episodicTitle) throws MediaNotFoundException;

    public EpisodicMedia deleteSerial(String serialTitle) throws MediaNotFoundException;

    public List<EpisodicMedia> getSerialByCategory(String category) throws MediaNotFoundException;

    public List<EpisodicMedia> getTvSerialByLanguage(String language) throws MediaNotFoundException;

    public Episode addEpisode(String serialTitle, Episode episode) throws MediaAlreadyExistsException, MediaNotFoundException;

    public Episode deleteEpisode(String serialTitle, int episodeNumber) throws MediaNotFoundException;

    public Episode getEpisodeById(String serialTitle, int episodeNumber) throws MediaNotFoundException;

    public List<Episode> getAllEpisodes(String serialTitle) throws MediaNotFoundException;

}
