package com.stackroute.standalonemediaservice.service;

import com.stackroute.standalonemediaservice.domain.StandaloneMedia;
import com.stackroute.standalonemediaservice.exception.FileNotUploadedException;
import com.stackroute.standalonemediaservice.exception.MediaAlreadyExistsException;
import com.stackroute.standalonemediaservice.exception.MediaNotFoundException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;

import java.util.List;

public interface StandaloneService {


    public List<StandaloneMedia> getAllMedia() throws MediaNotFoundException;

    public StandaloneMedia getMediaById(String mediaTitle) throws MediaNotFoundException;

    public StandaloneMedia saveMedia(StandaloneMedia media) throws MediaAlreadyExistsException;

    public StandaloneMedia deleteMedia(String mediaTitle) throws MediaNotFoundException;

    public List<StandaloneMedia> getMediaByGenre(String Genre) throws MediaNotFoundException;

    public List<StandaloneMedia> getMediaByCategory(String category) throws MediaNotFoundException;

    public List<StandaloneMedia> getWishlist(List<String> titles) throws MediaNotFoundException;

    public void store(MultipartFile file) throws FileNotUploadedException;
    public Resource loadFile(String filename) throws FileNotUploadedException;
    public void deleteAll();
    public void init() throws FileNotUploadedException;

}
