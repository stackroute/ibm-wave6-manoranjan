//<<<<<<< HEAD
///*
//package com.stackroute.recommendationService.repository;
//
//import com.stackroute.recommendationService.domain.Genre;
//import com.stackroute.recommendationService.domain.Language;
//import com.stackroute.recommendationService.domain.StandaloneMedia;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.data.neo4j.DataNeo4jTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@RunWith(SpringRunner.class)
//@DataNeo4jTest
//public class StandaloneMediaRepositoryTest {
//    @Autowired
//    private StandaloneMediaRepository standaloneMediaRepository;
//    private StandaloneMedia standaloneMedia;
//
//    @Autowired
//    private GenreRepository genreRepository;
//    private Genre genre;
//
//    @Autowired
//    private LanguageRepository languageRepository;
//    private Language language;
//
//    @Before
//    public void setUp() {
//        standaloneMedia = new StandaloneMedia();
//        standaloneMedia.setId((long) 1);
//        standaloneMedia.setTitle("The Lion King");
//        List<String> genre = new ArrayList<>();
//        genre.add("Action");
//        genre.add("Kid");
//        standaloneMedia.setGenre(genre);
//        standaloneMedia.setLanguage("Hindi");
//    }
//
//    @After
//    public void tearDown() {
//        standaloneMediaRepository.deleteAll();
//    }
//
//    @Test
//    public void displayAllMediaTest() {
//        StandaloneMedia standaloneMedia1 = new StandaloneMedia();
//        StandaloneMedia standaloneMedia2 = new StandaloneMedia();
//        standaloneMediaRepository.save(standaloneMedia1);
//        standaloneMediaRepository.save(standaloneMedia2);
//
//    }
//
//    @Test
//    public void displayAllMediaTestFailure() {
//
//    }
//}
//*/
//=======
////package com.stackroute.recommendationService.repository;
////
////import com.stackroute.recommendationService.domain.Genre;
////import com.stackroute.recommendationService.domain.Language;
////import com.stackroute.recommendationService.domain.StandaloneMedia;
////import org.junit.After;
////import org.junit.Before;
////import org.junit.Test;
////import org.junit.runner.RunWith;
////import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.boot.test.autoconfigure.data.neo4j.DataNeo4jTest;
////import org.springframework.test.context.junit4.SpringRunner;
////
////import java.util.ArrayList;
////import java.util.List;
////
////@RunWith(SpringRunner.class)
////@DataNeo4jTest
////public class StandaloneMediaRepositoryTest {
////    @Autowired
////    private StandaloneMediaRepository standaloneMediaRepository;
////    private StandaloneMedia standaloneMedia;
////
////    @Autowired
////    private GenreRepository genreRepository;
////    private Genre genre;
////
////    @Autowired
////    private LanguageRepository languageRepository;
////    private Language language;
////
////    @Before
////    public void setUp() {
////        standaloneMedia = new StandaloneMedia();
////        standaloneMedia.setId((long) 1);
////        standaloneMedia.setTitle("The Lion King");
////        List<String> genre = new ArrayList<>();
////        genre.add("Action");
////        genre.add("Kid");
////        standaloneMedia.setGenre(genre);
////        standaloneMedia.setLanguage("Hindi");
////    }
////
////    @After
////    public void tearDown() {
////        standaloneMediaRepository.deleteAll();
////    }
////
////    @Test
////    public void displayAllMediaTest() {
////        StandaloneMedia standaloneMedia1 = new StandaloneMedia();
////        StandaloneMedia standaloneMedia2 = new StandaloneMedia();
////        standaloneMediaRepository.save(standaloneMedia1);
////        standaloneMediaRepository.save(standaloneMedia2);
////
////    }
////
////    @Test
////    public void displayAllMediaTestFailure() {
////
////    }
////}
//>>>>>>> a9582d9eb5e87ce3882d318fe745b8719223076a
