package com.stackroute.episodicmediaservice.service;

import com.stackroute.episodicmediaservice.domain.Cast;
import com.stackroute.episodicmediaservice.domain.Crew;
import com.stackroute.episodicmediaservice.domain.Episode;
import com.stackroute.episodicmediaservice.domain.EpisodicMedia;
import com.stackroute.episodicmediaservice.exception.MediaAlreadyExistsException;
import com.stackroute.episodicmediaservice.exception.MediaNotFoundException;
import com.stackroute.episodicmediaservice.repository.EpisodicRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class EpisodicServiceTest {

    @Mock
    private EpisodicRepository episodicMediaRepository;

    @InjectMocks
    private EpisodicServiceImpl  mediaService;

    private List<EpisodicMedia> episodicMediaList=null;

    private Optional optional1;

    private EpisodicMedia episodicMedia;

    private Episode episode;
    private Episode episode1;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);//initializes mock object
        episodicMedia = new EpisodicMedia();
        episodicMedia.setEpisodicType("Yeh rista");
        episodicMedia.setEpisodicSynopsis("Yeh Rishta Kya Kehlata Hai tells the story of how the two meet, " +
                "time and again, which leads to their relationship progressing further with each encounter.");
        episodicMedia.setEpisodicLanguage("Hindi");
        episodicMedia.setEpisodicType("premium");
        episodicMedia.setEpisodicCategory("TV Episodes");
        episodicMedia.setEpisodicPosterUrl("https://cdn.pinkvilla.com/files/styles/contentpreview/public/yehrishtatrptopperweek.jpg?itok=p9vofO7z");
        episodicMedia.setEpisodicStudioName("Red chillies");

        Crew crewEM = new Crew("Chetan", "Writer");
        Cast castEM = new Cast("Naira", "Shivangi");
        Cast cast1EM = new Cast("Kartik", "Mohsin");

        List<Crew> crewsEM = new ArrayList<>();
        crewsEM.add(crewEM);

        episodicMedia.setEpisodicCrew(crewsEM);

        List<Cast> castsEM = new ArrayList<>();
        castsEM.add(castEM);
        castsEM.add(cast1EM);

        episodicMedia.setEpisodicCast(castsEM);

        episode = new Episode();
        episode.setEpisodeNo(1);
        episode.setEpisodeDescription("First meeting");
        episode.setEpisodeUrl("video.mp4");
        episode.setEpisodePosterUrl("https://cdn.pinkvilla.com/files/styles/contentpreview/public/yehrishtatrptopperweek.jpg?itok=p9vofO7z");

        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date dateEM = simpleDateFormat.parse("2019-06-09");
        episode.setEpisodeReleaseDate(dateEM);

        episode1 = new Episode();
        episode1.setEpisodeNo(2);
        episode1.setEpisodeDescription("Second meeting");
        episode1.setEpisodeUrl("video.mp4");
        episode1.setEpisodePosterUrl("https://cdn.pinkvilla.com/files/styles/contentpreview/public/yehrishtatrptopperweek.jpg?itok=p9vofO7z");

        Date date1 = simpleDateFormat.parse("2019-06-10");
        episode1.setEpisodeReleaseDate(date1);

        List<Episode> episodes = new ArrayList<>();
        episodes.add(episode);

        episodicMedia.setEpisodeList(episodes);

        episodicMediaList = new ArrayList<>();
        episodicMediaList.add(episodicMedia);

        optional1 = Optional.of(episodicMedia);

    }

    @After
    public void tearDown() throws Exception {
        episodicMediaRepository.deleteAll();
    }

    //testcase for saveserial failure
    @Test(expected = MediaAlreadyExistsException.class)
    public void saveSerialTestFailure_returnSavedSerial() throws MediaAlreadyExistsException {
        when(episodicMediaRepository.existsById(episodicMedia.getEpisodicTitle())).thenReturn(true);
        when(episodicMediaRepository.save(any())).thenReturn(null);
        EpisodicMedia episodicMedia1 = mediaService.saveSerial(episodicMedia);
        Assert.assertEquals(episodicMedia, episodicMedia1);

    }

    //testcase for get all serials
    @Test
    public void getAllSerialsTest_returnListOfEpisodicMedia() throws MediaNotFoundException {
        when(episodicMediaRepository.findAll()).thenReturn(episodicMediaList);
        List<EpisodicMedia> mediaList = mediaService.getAllSerials();

        Assert.assertEquals(true, mediaList.contains(episodicMedia));
    }

    //testcase for get all serials failure
    @Test(expected = MediaNotFoundException.class)
    public void getAllSerialsTestFailure_returnNull() throws MediaNotFoundException {
        when(episodicMediaRepository.findAll()).thenReturn(null);
        List<EpisodicMedia> mediaList = mediaService.getAllSerials();

        Assert.assertEquals(true, mediaList.contains(episodicMedia));
    }

    //testcase for get serial by title
    @Test
    public void getSerialByTitleTest_returnEpisodicMediaByItsTitle() throws MediaNotFoundException {
        when(episodicMediaRepository.existsById(episodicMedia.getEpisodicTitle())).thenReturn(true);
        when(episodicMediaRepository.findById(episodicMedia.getEpisodicTitle())).thenReturn(optional1);
        EpisodicMedia foundMedia = mediaService.getSerialByTitle(episodicMedia.getEpisodicTitle());
        Assert.assertEquals(optional1.get(), foundMedia);

    }

    //testcase for get serial by title failure
    @Test(expected = MediaNotFoundException.class)
    public void getSerialByTitleTestFailure_throwsMediaNotFoundException() throws MediaNotFoundException {
        when(episodicMediaRepository.existsById(episodicMedia.getEpisodicTitle())).thenReturn(false);
        when(episodicMediaRepository.findById(episodicMedia.getEpisodicTitle())).thenReturn(null);
        EpisodicMedia foundMedia = mediaService.getSerialByTitle(episodicMedia.getEpisodicTitle());
        Assert.assertEquals(optional1.get(), foundMedia);
    }

    //testcase for delete serial
    @Test
    public void deleteSerialTest_returnsDeletedEpisodicMedia() throws MediaNotFoundException {
        episodicMediaRepository.save(episodicMedia);
        when(episodicMediaRepository.existsById(episodicMedia.getEpisodicTitle())).thenReturn(true);
        when(episodicMediaRepository.findById(episodicMedia.getEpisodicTitle())).thenReturn(optional1);
        episodicMediaRepository.deleteById(episodicMedia.getEpisodicTitle());

        Assert.assertEquals(episodicMedia, mediaService.deleteSerial(episodicMedia.getEpisodicTitle()));
    }

    //testcase for delete serial failure
    @Test(expected = MediaNotFoundException.class)
    public void deleteSerialFailureTest_throwsMediaNotFoundException() throws MediaNotFoundException {
        episodicMediaRepository.save(episodicMedia);
        when(episodicMediaRepository.existsById(episodicMedia.getEpisodicTitle())).thenReturn(false);
        when(episodicMediaRepository.findById(episodicMedia.getEpisodicTitle())).thenReturn(null);
        episodicMediaRepository.deleteById(episodicMedia.getEpisodicTitle());

        Assert.assertEquals(episodicMedia, mediaService.deleteSerial(episodicMedia.getEpisodicTitle()));
    }

    //testcase for get serial by category
    @Test
    public void getSerialByCategoryTest_returnEpisodicMediaByItsCategory() throws MediaNotFoundException {
        episodicMediaRepository.save(episodicMedia);
        when(episodicMediaRepository.findAll()).thenReturn(episodicMediaList);
        List<EpisodicMedia> mediaList = mediaService.getSerialByCategory("TV Episodes");
        Assert.assertEquals(true, mediaList.contains(episodicMedia));
    }

    //testcase for test serial by category failure
    @Test(expected = MediaNotFoundException.class)
    public void getSerialByCategoryFailureTest_throwsMediaNotFoundException() throws MediaNotFoundException {
        when(episodicMediaRepository.findAll()).thenReturn(null);
        List<EpisodicMedia> mediaList = mediaService.getSerialByCategory("TV Episodes");
        Assert.assertEquals(true, mediaList.contains(episodicMedia));
    }

    //testcase for get tv serial by language
    @Test
    public void getTvSerialByLanguageTest_returnTvEpisodesByLanguage() throws MediaNotFoundException {
        episodicMediaRepository.save(episodicMedia);
        when(episodicMediaRepository.findAll()).thenReturn(episodicMediaList);
        List<EpisodicMedia> mediaList = mediaService.getTvSerialByLanguage("Hindi");
        Assert.assertEquals(true, mediaList.contains(episodicMedia));
    }

    //testcase for get tv serial by language
    @Test(expected = MediaNotFoundException.class)
    public void getTvSerialByLanguageFailureTest_throwsMediaNotFoundException() throws MediaNotFoundException {
        when(episodicMediaRepository.findAll()).thenReturn(null);
        List<EpisodicMedia> mediaList = mediaService.getTvSerialByLanguage("Hindi");
        Assert.assertEquals(true, mediaList.contains(episodicMedia));
    }

    //testcase for add episode failure
    @Test(expected = MediaNotFoundException.class)
    public void addEpisodeFailureTest_throwsMediaNotFoundException() throws MediaNotFoundException, MediaAlreadyExistsException {
        when(episodicMediaRepository.existsById(episodicMedia.getEpisodicTitle())).thenReturn(false);
        when(episodicMediaRepository.findById(episodicMedia.getEpisodicTitle())).thenReturn(optional1);
        when(episodicMediaRepository.save(any())).thenReturn(episodicMedia);
        Episode episode2 = mediaService.addEpisode(episodicMedia.getEpisodicTitle(), episode1);

        Assert.assertEquals(episode1, episode2);

    }

    //testcase for add episode failure
    @Test(expected = MediaAlreadyExistsException.class)
    public void addEpisodeFailureTest_throwsMediaAlreadyExistsException() throws MediaNotFoundException, MediaAlreadyExistsException {
        when(episodicMediaRepository.existsById(episodicMedia.getEpisodicTitle())).thenReturn(true);
        when(episodicMediaRepository.findById(episodicMedia.getEpisodicTitle())).thenReturn(optional1);
        when(episodicMediaRepository.save(any())).thenReturn(episodicMedia);
        Episode episode2 = mediaService.addEpisode(episodicMedia.getEpisodicTitle(), episode);

        Assert.assertEquals(episode, episode2);

    }

    //testcase for delete episode
    @Test
    public void deleteEpisodeTest_returnsDeletedEpisodeFromGivenEpisodicMedia() throws MediaNotFoundException {
        when(episodicMediaRepository.existsById(episodicMedia.getEpisodicTitle())).thenReturn(true);
        when(episodicMediaRepository.findById(episodicMedia.getEpisodicTitle())).thenReturn(optional1);
        when(episodicMediaRepository.save(any())).thenReturn(episodicMedia);
        Episode episode2 = mediaService.deleteEpisode(episodicMedia.getEpisodicTitle(), episode.getEpisodeNo());

        Assert.assertEquals(episode, episode2);
    }

    //testcase for delete episode failure
    @Test(expected = MediaNotFoundException.class)
    public void deleteEpisodeFailureTest_throwsMediaNotFoundException() throws MediaNotFoundException {
        when(episodicMediaRepository.existsById(episodicMedia.getEpisodicTitle())).thenReturn(false);
        when(episodicMediaRepository.findById(episodicMedia.getEpisodicTitle())).thenReturn(optional1);
        when(episodicMediaRepository.save(any())).thenReturn(episodicMedia);
        Episode episode2 = mediaService.deleteEpisode(episodicMedia.getEpisodicTitle(), episode.getEpisodeNo());

        Assert.assertEquals(episode, episode2);
    }

    //testcase for delete episode failure 2
    @Test(expected = MediaNotFoundException.class)
    public void deleteEpisodeFailure2Test_throwsMediaNotFoundException() throws MediaNotFoundException {
        when(episodicMediaRepository.existsById(episodicMedia.getEpisodicTitle())).thenReturn(true);
        when(episodicMediaRepository.findById(episodicMedia.getEpisodicTitle())).thenReturn(optional1);
        when(episodicMediaRepository.save(any())).thenReturn(episodicMedia);
        Episode episode2 = mediaService.deleteEpisode(episodicMedia.getEpisodicTitle(), 10);

        Assert.assertEquals(episode, episode2);
    }

    //testcase for get episode by id
    @Test
    public void getEpisodeByIdTest_returnsEpisodeOfEpisodicMedia() throws MediaNotFoundException {
        when(episodicMediaRepository.existsById(episodicMedia.getEpisodicTitle())).thenReturn(true);
        when(episodicMediaRepository.findById(episodicMedia.getEpisodicTitle())).thenReturn(optional1);
        Episode episode2 = mediaService.getEpisodeById(episodicMedia.getEpisodicTitle(), 1);

        Assert.assertEquals(episode, episode2);
    }

    //testcase for get episode by id failure
    @Test(expected = MediaNotFoundException.class)
    public void getEpisodeByIdFailureTest_throwsMediaNotFoundException() throws MediaNotFoundException {
        when(episodicMediaRepository.existsById(episodicMedia.getEpisodicTitle())).thenReturn(false);
        when(episodicMediaRepository.findById(episodicMedia.getEpisodicTitle())).thenReturn(optional1);
        Episode episode2 = mediaService.getEpisodeById(episodicMedia.getEpisodicTitle(), 1);

        Assert.assertEquals(episode, episode2);
    }

    //testcase for get episode by id  failure 2
    @Test(expected = MediaNotFoundException.class)
    public void getEpisodeByIdFailure2Test_throwsMediaNotFoundException() throws MediaNotFoundException {
        when(episodicMediaRepository.existsById(episodicMedia.getEpisodicTitle())).thenReturn(true);
        when(episodicMediaRepository.findById(episodicMedia.getEpisodicTitle())).thenReturn(optional1);
        Episode episode2 = mediaService.getEpisodeById(episodicMedia.getEpisodicTitle(), 10);

        Assert.assertEquals(episode, episode2);
    }

    //testcase for get all episodes
    @Test
    public void getAllEpisodesTest_returnsListOfEpisodesOfEpisodicMedia() throws MediaNotFoundException {
        when(episodicMediaRepository.existsById(episodicMedia.getEpisodicTitle())).thenReturn(true);
        when(episodicMediaRepository.findById(episodicMedia.getEpisodicTitle())).thenReturn(optional1);
        List<Episode> episodes = mediaService.getAllEpisodes(episodicMedia.getEpisodicTitle());

        Assert.assertEquals(true, episodes.contains(episode));
    }

    //testcase for get all episodes failure
    @Test(expected = MediaNotFoundException.class)
    public void getAllEpisodesFailureTest_throwsMediaNotFoundException() throws MediaNotFoundException {
        when(episodicMediaRepository.existsById(episodicMedia.getEpisodicTitle())).thenReturn(false);
        when(episodicMediaRepository.findById(episodicMedia.getEpisodicTitle())).thenReturn(optional1);
        List<Episode> episodes = mediaService.getAllEpisodes(episodicMedia.getEpisodicTitle());

        Assert.assertEquals(true, episodes.contains(episode));
    }

}