package com.stackroute.recommendationService.repository;

import com.stackroute.recommendationService.domain.Genre;
import com.stackroute.recommendationService.domain.Language;
import com.stackroute.recommendationService.domain.Media;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.neo4j.DataNeo4jTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@DataNeo4jTest
public class MediaRepositoryTest {
    @Autowired
    private MediaRepository mediaRepository;
    private Media media;

    @Autowired
    private GenreRepository genreRepository;
    private Genre genre;

    @Autowired
    private LanguageRepository languageRepository;
    private Language language;

    @Before
    public void setUp() {
        media = new Media();
        media.setId((long) 1);
        media.setTitle("The Lion King");
        List<String> genre = new ArrayList<>();
        genre.add("Action");
        genre.add("Kid");
        media.setGenre(genre);
        media.setLanguage("Hindi");
    }

    @After
    public void tearDown() {
        mediaRepository.deleteAll();
    }

    @Test
    public void displayAllMediaTest()
    {
        Media media1=new Media();
        Media media2=new Media();
        mediaRepository.save(media1);
        mediaRepository.save(media2);

    }

    @Test
    public void displayAllMediaTestFailure()
    {

    }
}
