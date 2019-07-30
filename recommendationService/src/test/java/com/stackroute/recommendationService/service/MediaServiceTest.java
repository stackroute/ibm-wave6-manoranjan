package com.stackroute.recommendationService.service;

import com.stackroute.recommendationService.domain.Media;
import com.stackroute.recommendationService.exception.MediaNotFoundException;
import com.stackroute.recommendationService.repository.LanguageRepository;
import com.stackroute.recommendationService.repository.MediaRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class MediaServiceTest {
    private Media media;

    @Mock
    //this specify which mock obiject to be injected
    private MediaRepository mediaRepository;
    Optional optional;
    LanguageRepository languageRepository;

    @InjectMocks
    private MediaServiceImpl mediaService;
    List<Media> list= null;

    @Before
    public void setUp(){
        //Initialising the mock object
        MockitoAnnotations.initMocks(this);
        media = new Media();
        media.setId((long) 1);
        media.setTitle("Mohobbatein");
        List<String> genre = new ArrayList<>();
        genre.add("Action");
        genre.add("Romantic");
        media.setGenre(genre);
        media.setLanguage("Hindi");
        list = new ArrayList<>();
        list.add(media);
        optional=Optional.of(media);
    }

    @Test
    public void getAllMediasTestSuccess() throws MediaNotFoundException {
        mediaRepository.save(media);
        //stubbing the mock to return specific data
        when(mediaRepository.findAll()).thenReturn(list);
        List<Media> mediaList = mediaService.displayMedia();
        Assert.assertEquals(list,mediaList);
    }

//    @Test
//    public void getAllMediasTestFailure() throws MediaNotFoundException {
//        mediaRepository.save(media);
//        //stubbing the mock to return specific data
//        when(mediaRepository.findAll()).thenReturn(null);
//        List<Media> mediaList = mediaService.displayMedia();
//        Assert.assertNotEquals(list,mediaList);
//    }

//    @Test
//    public void getMediaByTitleTestSuccess() throws MediaNotFoundException {
//        when(mediaRepository.findById(media.getId())).thenReturn(optional);
//        Media savedMedia = mediaService.getMediaByTitle(media.getTitle());
//        Assert.assertEquals(Optional.of(1), savedMedia.getId());
//    }

//    @Test
//    public void saveMediaTestSuccess() throws MediaAlreadyExistException {
//        when(mediaRepository.save((Media) any())).thenReturn(media);
//        Media savedMedia = mediaService.saveMedia(media);
//        Assert.assertEquals(media,savedMedia);
//        System.out.println(savedMedia);
//        //verify here verifies that muzixRepository save method is only called once
//        verify(mediaRepository,times(1)).save(media);
//    }
//    @Test
//    public void saveTrackTestFailure() throws MediaAlreadyExistException {
//        when(mediaRepository.save((Media) any())).thenReturn(null);
//        Media savedMedia = mediaService.saveMedia(media);
//        Assert.assertNotEquals(media,savedMedia);
//    }
}
