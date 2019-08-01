<<<<<<< HEAD
/*
package com.stackroute.recommendationService.service;

import com.stackroute.recommendationService.domain.StandaloneMedia;
import com.stackroute.recommendationService.exception.MediaNotFoundException;
import com.stackroute.recommendationService.repository.LanguageRepository;
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
    private StandaloneMedia standaloneMedia;

    @Mock
    //this specify which mock obiject to be injected
    private StandaloneMediaRepository standaloneMediaRepository;
    Optional optional;
    LanguageRepository languageRepository;

    @InjectMocks
    private MediaServiceImpl mediaService;
    List<StandaloneMedia> list = null;

    @Before
    public void setUp() {
        //Initialising the mock object
        MockitoAnnotations.initMocks(this);
        standaloneMedia = new StandaloneMedia();
        standaloneMedia.setId((long) 1);
        standaloneMedia.setTitle("Mohobbatein");
        List<String> genre = new ArrayList<>();
        genre.add("Action");
        genre.add("Romantic");
        standaloneMedia.setGenre(genre);
        standaloneMedia.setLanguage("Hindi");
        list = new ArrayList<>();
        list.add(standaloneMedia);
        optional = Optional.of(standaloneMedia);
    }

    @Test
    public void getAllMediasTestSuccess() throws MediaNotFoundException {
        standaloneMediaRepository.save(standaloneMedia);
        //stubbing the mock to return specific data
        when(standaloneMediaRepository.findAll()).thenReturn(list);
        List<StandaloneMedia> standaloneMediaList = mediaService.displayMedia();
        Assert.assertEquals(list, standaloneMediaList);
    }

}
*/
=======
//package com.stackroute.recommendationService.service;
//
//import com.stackroute.recommendationService.domain.StandaloneMedia;
//import com.stackroute.recommendationService.exception.MediaNotFoundException;
//import com.stackroute.recommendationService.repository.LanguageRepository;
//import com.stackroute.recommendationService.repository.StandaloneMediaRepository;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//
//public class MediaServiceTest {
//    private StandaloneMedia standaloneMedia;
//
//    @Mock
//    //this specify which mock obiject to be injected
//    private StandaloneMediaRepository standaloneMediaRepository;
//    Optional optional;
//    LanguageRepository languageRepository;
//
//    @InjectMocks
//    private MediaServiceImpl mediaService;
//    List<StandaloneMedia> list = null;
//
//    @Before
//    public void setUp() {
//        //Initialising the mock object
//        MockitoAnnotations.initMocks(this);
//        standaloneMedia = new StandaloneMedia();
//        standaloneMedia.setId((long) 1);
//        standaloneMedia.setTitle("Mohobbatein");
//        List<String> genre = new ArrayList<>();
//        genre.add("Action");
//        genre.add("Romantic");
//        standaloneMedia.setGenre(genre);
//        standaloneMedia.setLanguage("Hindi");
//        list = new ArrayList<>();
//        list.add(standaloneMedia);
//        optional = Optional.of(standaloneMedia);
//    }
//
//    @Test
//    public void getAllMediasTestSuccess() throws MediaNotFoundException {
//        standaloneMediaRepository.save(standaloneMedia);
//        //stubbing the mock to return specific data
//        when(standaloneMediaRepository.findAll()).thenReturn(list);
//        List<StandaloneMedia> standaloneMediaList = mediaService.displayMedia();
//        Assert.assertEquals(list, standaloneMediaList);
//    }
//
//}
>>>>>>> a9582d9eb5e87ce3882d318fe745b8719223076a
