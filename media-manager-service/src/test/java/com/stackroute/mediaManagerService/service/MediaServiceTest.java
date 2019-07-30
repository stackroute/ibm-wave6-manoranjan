package com.stackroute.mediaManagerService.service;

import com.stackroute.mediaManagerService.domain.*;
import com.stackroute.mediaManagerService.exceptions.MediaAlreadyExistsException;
import com.stackroute.mediaManagerService.exceptions.MediaNotFoundException;
import com.stackroute.mediaManagerService.repository.EpisodicMediaRepository;
import com.stackroute.mediaManagerService.repository.MediaRepository;
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
import static org.mockito.Mockito.*;

public class MediaServiceTest {

    @Mock
    private MediaRepository mediaRepository;

    @Mock
    private EpisodicMediaRepository episodicMediaRepository;

    @InjectMocks
    private MediaServiceImpl mediaService;

    private Media media;

    List<Media> mediaList=null;
    List<EpisodicMedia> episodicMediaList=null;

    Optional optional;
    Optional optional1;

    private EpisodicMedia episodicMedia;

    private Episode episode;
    private Episode episode1;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);//initializes mock object
        media=new Media();
        media.setMediaTitle("2 states");
        media.setMediaCategory("Movie");
        media.setMediaSynopsis("Movies based on people from 2 different states");

        List<String> genres=new ArrayList<String>();
        genres.add("romantic");
        genres.add("action");

        media.setMediaGenre(genres);
        media.setMediaLanguage("Hindi");

        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date date = simpleDateFormat.parse("2018-09-09");
        media.setMediaReleaseDate(date);
        media.setMediaPosterUrl("https://images-na.ssl-images-amazon.com/images/I/51ZvSZqM9UL.jpg");
        media.setMediaStudioName("Red chillies");

        Crew crew=new Crew("Chetan Bhagat","Writer");
        Cast cast=new Cast("Krish","Arjun");
        Cast cast1=new Cast("Ananya","Alia");

        List<Crew> crews=new ArrayList<>();
        crews.add(crew);

        media.setMediaCrew(crews);

        List<Cast> casts=new ArrayList<>();
        casts.add(cast);
        casts.add(cast1);

        media.setMediaCast(casts);
        media.setMediaUrl("2states.mp4");
        media.setMediaTrailerUrl("trailer.mp4");
        media.setMediaType("Free");

        mediaList=new ArrayList<>();
        mediaList.add(media);

        optional= Optional.of(media);

        episodicMedia=new EpisodicMedia();
        episodicMedia.setEpisodicType("Yeh rista");
        episodicMedia.setEpisodicSynopsis("Yeh Rishta Kya Kehlata Hai tells the story of how the two meet, " +
                "time and again, which leads to their relationship progressing further with each encounter.");
        episodicMedia.setEpisodicLanguage("Hindi");
        episodicMedia.setEpisodicType("premium");
        episodicMedia.setEpisodicCategory("TV Episodes");
        episodicMedia.setEpisodicPosterUrl("https://cdn.pinkvilla.com/files/styles/contentpreview/public/yehrishtatrptopperweek.jpg?itok=p9vofO7z");
        episodicMedia.setEpisodicStudioName("Red chillies");

        Crew crewEM=new Crew("Chetan","Writer");
        Cast castEM=new Cast("Naira","Shivangi");
        Cast cast1EM=new Cast("Kartik","Mohsin");

        List<Crew> crewsEM=new ArrayList<>();
        crewsEM.add(crewEM);

        episodicMedia.setEpisodicCrew(crewsEM);

        List<Cast> castsEM=new ArrayList<>();
        castsEM.add(castEM);
        castsEM.add(cast1EM);

        episodicMedia.setEpisodicCast(castsEM);

        episode=new Episode();
        episode.setEpisodeNo(1);
        episode.setEpisodeDescription("First meeting");
        episode.setEpisodeUrl("video.mp4");
        episode.setEpisodePosterUrl("https://cdn.pinkvilla.com/files/styles/contentpreview/public/yehrishtatrptopperweek.jpg?itok=p9vofO7z");

        Date dateEM = simpleDateFormat.parse("2019-06-09");
        episode.setEpisodeReleaseDate(dateEM);

        episode1=new Episode();
        episode1.setEpisodeNo(2);
        episode1.setEpisodeDescription("Second meeting");
        episode1.setEpisodeUrl("video.mp4");
        episode1.setEpisodePosterUrl("https://cdn.pinkvilla.com/files/styles/contentpreview/public/yehrishtatrptopperweek.jpg?itok=p9vofO7z");

        Date date1 = simpleDateFormat.parse("2019-06-10");
        episode1.setEpisodeReleaseDate(date1);

        List<Episode> episodes=new ArrayList<>();
        episodes.add(episode);

        episodicMedia.setEpisodeList(episodes);

        episodicMediaList=new ArrayList<>();
        episodicMediaList.add(episodicMedia);

        optional1=Optional.of(episodicMedia);

    }

    @After
    public void tearDown() throws Exception {
        mediaRepository.deleteAll();
        episodicMediaRepository.deleteAll();
    }

    @Test
    public void getAllMediaTest_returnListOfMediaContainingMedia() throws MediaNotFoundException {
        mediaRepository.save(media);
        when(mediaRepository.findAll()).thenReturn(mediaList);
        List<Media> mediaList1=mediaService.getAllMedia();
        Assert.assertEquals(true,mediaList1.contains(media));
    }

    @Test(expected = MediaNotFoundException.class)
    public void getAllMediaFailureTest_returnListOfMediaContainingNull() throws MediaNotFoundException {
        when(mediaRepository.findAll()).thenReturn(null);
        List<Media> mediaList1=mediaService.getAllMedia();
        Assert.assertEquals(true,mediaList1.contains(media));
    }

    @Test
    public void getMediaByIdTest_returnMediaContainingGivenMediaTitle() throws MediaNotFoundException {
        mediaRepository.save(media);
        when(mediaRepository.existsById(media.getMediaTitle())).thenReturn(true);
        when(mediaRepository.findById(media.getMediaTitle())).thenReturn(optional);
        Media foundMedia=mediaService.getMediaById(media.getMediaTitle());
        Assert.assertEquals(optional.get(),foundMedia);
    }

    @Test(expected = MediaNotFoundException.class)
    public void getMediaByIdFailureTest_returnNull() throws MediaNotFoundException {
        mediaRepository.save(media);
        when(mediaRepository.existsById(media.getMediaTitle())).thenReturn(false);
        when(mediaRepository.findById(media.getMediaTitle())).thenReturn(null);
        Media foundMedia=mediaService.getMediaById(media.getMediaTitle());
        Assert.assertEquals(media.getMediaTitle(),foundMedia.getMediaTitle());
    }

    @Test(expected = MediaAlreadyExistsException.class)
    public void saveMediaFailureTest_returnSavedMediaAsNull() throws MediaAlreadyExistsException{
        when(mediaRepository.save(any())).thenReturn(null);
        Media savedMedia=mediaService.saveMedia(media);
        Assert.assertEquals(media,savedMedia);
    }

    @Test
    public void deleteMediaTest_returnDeletedMedia() throws MediaNotFoundException {
        mediaRepository.save(media);
        when(mediaRepository.existsById(media.getMediaTitle())).thenReturn(true);
        when(mediaRepository.findById(media.getMediaTitle())).thenReturn(optional);
        mediaRepository.deleteById(media.getMediaTitle());
        Assert.assertEquals(media,mediaService.deleteMedia(media.getMediaTitle()));
    }

    @Test(expected = MediaNotFoundException.class)
    public void deleteMediaFailureTest_returnNull() throws MediaNotFoundException {
        mediaRepository.save(media);
        when(mediaRepository.existsById(media.getMediaTitle())).thenReturn(false);
        when(mediaRepository.findById(media.getMediaTitle())).thenReturn(null);
        mediaRepository.deleteById(media.getMediaTitle());
        Assert.assertEquals(media,mediaService.deleteMedia(media.getMediaTitle()));
    }

    @Test
    public void getMediaByGenreTest_returnListOfMediaOfGivenGenre() throws MediaNotFoundException {
        mediaRepository.save(media);
        when(mediaRepository.findAll()).thenReturn(mediaList);
        List<Media> mediaList=mediaService.getMediaByGenre("action");
        Assert.assertEquals(true,mediaList.contains(media));
    }

    @Test(expected = MediaNotFoundException.class)
    public void getMediaByGenreFailureTest_returnNull() throws MediaNotFoundException {
        mediaRepository.save(media);
        when(mediaRepository.findAll()).thenReturn(null);
        List<Media> mediaList=mediaService.getMediaByGenre("action");
        Assert.assertEquals(false,mediaList.contains(media));
    }

    @Test
    public void getMediaByCategoryTest_returnListOfMediaOfGivenCategory() throws MediaNotFoundException {
        mediaRepository.save(media);
        when(mediaRepository.findAll()).thenReturn(mediaList);
        List<Media> mediaList=mediaService.getMediaByCategory("Movie");
        Assert.assertEquals(true,mediaList.contains(media));
    }

    @Test
    public void getMediaByCategoryNegativeTest_returnMediaList() throws MediaNotFoundException {
        mediaRepository.save(media);
        when(mediaRepository.findAll()).thenReturn(mediaList);
        List<Media> mediaList=mediaService.getMediaByCategory("Documentary");
        Assert.assertNotEquals(true,mediaList.contains(media));
    }

    @Test(expected = MediaNotFoundException.class)
    public void getMediaByCategoryFailureTest_returnNullMediaList() throws MediaNotFoundException {
        mediaRepository.save(media);
        when(mediaRepository.findAll()).thenReturn(null);
        List<Media> mediaList=mediaService.getMediaByCategory("Movie");
        Assert.assertEquals(false,mediaList.contains(media));
    }

    @Test(expected = MediaAlreadyExistsException.class)
    public void saveSerialTestFailure_returnSavedSerial() throws MediaAlreadyExistsException{
        when(episodicMediaRepository.existsById(episodicMedia.getEpisodicTitle())).thenReturn(true);
        when(episodicMediaRepository.save(any())).thenReturn(null);
        EpisodicMedia episodicMedia1=mediaService.saveSerial(episodicMedia);
        Assert.assertEquals(episodicMedia,episodicMedia1);

    }

    @Test
    public void getAllSerialsTest_returnListOfEpisodicMedia() throws MediaNotFoundException {
        when(episodicMediaRepository.findAll()).thenReturn(episodicMediaList);
        List<EpisodicMedia> mediaList=mediaService.getAllSerials();

        Assert.assertEquals(true,mediaList.contains(episodicMedia));
    }

    @Test(expected = MediaNotFoundException.class)
    public void getAllSerialsTestFailure_returnNull() throws MediaNotFoundException {
        when(episodicMediaRepository.findAll()).thenReturn(null);
        List<EpisodicMedia> mediaList=mediaService.getAllSerials();

        Assert.assertEquals(true,mediaList.contains(episodicMedia));
    }

    @Test
    public void getSerialByTitleTest_returnEpisodicMediaByItsTitle() throws MediaNotFoundException {
        when(episodicMediaRepository.existsById(episodicMedia.getEpisodicTitle())).thenReturn(true);
        when(episodicMediaRepository.findById(episodicMedia.getEpisodicTitle())).thenReturn(optional1);
        EpisodicMedia foundMedia=mediaService.getSerialByTitle(episodicMedia.getEpisodicTitle());
        Assert.assertEquals(optional1.get(),foundMedia);

    }

    @Test(expected = MediaNotFoundException.class)
    public void getSerialByTitleTestFailure_throwsMediaNotFoundException() throws MediaNotFoundException {
        when(episodicMediaRepository.existsById(episodicMedia.getEpisodicTitle())).thenReturn(false);
        when(episodicMediaRepository.findById(episodicMedia.getEpisodicTitle())).thenReturn(null);
        EpisodicMedia foundMedia=mediaService.getSerialByTitle(episodicMedia.getEpisodicTitle());
        Assert.assertEquals(optional1.get(),foundMedia);
    }

    @Test
    public void deleteSerialTest_returnsDeletedEpisodicMedia() throws MediaNotFoundException{
        episodicMediaRepository.save(episodicMedia);
        when(episodicMediaRepository.existsById(episodicMedia.getEpisodicTitle())).thenReturn(true);
        when(episodicMediaRepository.findById(episodicMedia.getEpisodicTitle())).thenReturn(optional1);
        episodicMediaRepository.deleteById(episodicMedia.getEpisodicTitle());

        Assert.assertEquals(episodicMedia,mediaService.deleteSerial(episodicMedia.getEpisodicTitle()));
    }

    @Test(expected = MediaNotFoundException.class)
    public void deleteSerialFailureTest_throwsMediaNotFoundException() throws MediaNotFoundException{
        episodicMediaRepository.save(episodicMedia);
        when(episodicMediaRepository.existsById(episodicMedia.getEpisodicTitle())).thenReturn(false);
        when(episodicMediaRepository.findById(episodicMedia.getEpisodicTitle())).thenReturn(null);
        episodicMediaRepository.deleteById(episodicMedia.getEpisodicTitle());

        Assert.assertEquals(episodicMedia,mediaService.deleteSerial(episodicMedia.getEpisodicTitle()));
    }

    @Test
    public void getSerialByCategoryTest_returnEpisodicMediaByItsCategory() throws MediaNotFoundException {
        episodicMediaRepository.save(episodicMedia);
        when(episodicMediaRepository.findAll()).thenReturn(episodicMediaList);
        List<EpisodicMedia> mediaList=mediaService.getSerialByCategory("TV Episodes");
        Assert.assertEquals(true,mediaList.contains(episodicMedia));
    }

    @Test(expected = MediaNotFoundException.class)
    public void getSerialByCategoryFailureTest_throwsMediaNotFoundException() throws MediaNotFoundException {
        when(episodicMediaRepository.findAll()).thenReturn(null);
        List<EpisodicMedia> mediaList=mediaService.getSerialByCategory("TV Episodes");
        Assert.assertEquals(true,mediaList.contains(episodicMedia));
    }

    @Test
    public void getTvSerialByLanguageTest_returnTvEpisodesByLanguage() throws MediaNotFoundException {
        episodicMediaRepository.save(episodicMedia);
        when(episodicMediaRepository.findAll()).thenReturn(episodicMediaList);
        List<EpisodicMedia> mediaList=mediaService.getTvSerialByLanguage("Hindi");
        Assert.assertEquals(true,mediaList.contains(episodicMedia));
    }

    @Test(expected = MediaNotFoundException.class)
    public void getTvSerialByLanguageFailureTest_throwsMediaNotFoundException() throws MediaNotFoundException {
        when(episodicMediaRepository.findAll()).thenReturn(null);
        List<EpisodicMedia> mediaList=mediaService.getTvSerialByLanguage("Hindi");
        Assert.assertEquals(true,mediaList.contains(episodicMedia));
    }

    @Test(expected = MediaNotFoundException.class)
    public void addEpisodeFailureTest_throwsMediaNotFoundException() throws MediaNotFoundException, MediaAlreadyExistsException {
        when(episodicMediaRepository.existsById(episodicMedia.getEpisodicTitle())).thenReturn(false);
        when(episodicMediaRepository.findById(episodicMedia.getEpisodicTitle())).thenReturn(optional1);
        when(episodicMediaRepository.save(any())).thenReturn(episodicMedia);
        Episode episode2=mediaService.addEpisode(episodicMedia.getEpisodicTitle(),episode1);

        Assert.assertEquals(episode1,episode2);

    }

    @Test(expected = MediaAlreadyExistsException.class)
    public void addEpisodeFailureTest_throwsMediaAlreadyExistsException() throws MediaNotFoundException, MediaAlreadyExistsException {
        when(episodicMediaRepository.existsById(episodicMedia.getEpisodicTitle())).thenReturn(true);
        when(episodicMediaRepository.findById(episodicMedia.getEpisodicTitle())).thenReturn(optional1);
        when(episodicMediaRepository.save(any())).thenReturn(episodicMedia);
        Episode episode2=mediaService.addEpisode(episodicMedia.getEpisodicTitle(),episode);

        Assert.assertEquals(episode,episode2);

    }

    @Test
    public void deleteEpisodeTest_returnsDeletedEpisodeFromGivenEpisodicMedia() throws MediaNotFoundException {
        when(episodicMediaRepository.existsById(episodicMedia.getEpisodicTitle())).thenReturn(true);
        when(episodicMediaRepository.findById(episodicMedia.getEpisodicTitle())).thenReturn(optional1);
        when(episodicMediaRepository.save(any())).thenReturn(episodicMedia);
        Episode episode2=mediaService.deleteEpisode(episodicMedia.getEpisodicTitle(),episode.getEpisodeNo());

        Assert.assertEquals(episode,episode2);
    }

    @Test(expected = MediaNotFoundException.class)
    public void deleteEpisodeFailureTest_throwsMediaNotFoundException() throws MediaNotFoundException {
        when(episodicMediaRepository.existsById(episodicMedia.getEpisodicTitle())).thenReturn(false);
        when(episodicMediaRepository.findById(episodicMedia.getEpisodicTitle())).thenReturn(optional1);
        when(episodicMediaRepository.save(any())).thenReturn(episodicMedia);
        Episode episode2=mediaService.deleteEpisode(episodicMedia.getEpisodicTitle(),episode.getEpisodeNo());

        Assert.assertEquals(episode,episode2);
    }

    @Test(expected = MediaNotFoundException.class)
    public void deleteEpisodeFailure2Test_throwsMediaNotFoundException() throws MediaNotFoundException {
        when(episodicMediaRepository.existsById(episodicMedia.getEpisodicTitle())).thenReturn(true);
        when(episodicMediaRepository.findById(episodicMedia.getEpisodicTitle())).thenReturn(optional1);
        when(episodicMediaRepository.save(any())).thenReturn(episodicMedia);
        Episode episode2=mediaService.deleteEpisode(episodicMedia.getEpisodicTitle(),10);

        Assert.assertEquals(episode,episode2);
    }

    @Test
    public void getEpisodeByIdTest_returnsEpisodeOfEpisodicMedia() throws MediaNotFoundException {
        when(episodicMediaRepository.existsById(episodicMedia.getEpisodicTitle())).thenReturn(true);
        when(episodicMediaRepository.findById(episodicMedia.getEpisodicTitle())).thenReturn(optional1);
        Episode episode2=mediaService.getEpisodeById(episodicMedia.getEpisodicTitle(),1);

        Assert.assertEquals(episode,episode2);
    }

    @Test(expected = MediaNotFoundException.class)
    public void getEpisodeByIdFailureTest_throwsMediaNotFoundException() throws MediaNotFoundException {
        when(episodicMediaRepository.existsById(episodicMedia.getEpisodicTitle())).thenReturn(false);
        when(episodicMediaRepository.findById(episodicMedia.getEpisodicTitle())).thenReturn(optional1);
        Episode episode2=mediaService.getEpisodeById(episodicMedia.getEpisodicTitle(),1);

        Assert.assertEquals(episode,episode2);
    }

    @Test(expected = MediaNotFoundException.class)
    public void getEpisodeByIdFailure2Test_throwsMediaNotFoundException() throws MediaNotFoundException {
        when(episodicMediaRepository.existsById(episodicMedia.getEpisodicTitle())).thenReturn(true);
        when(episodicMediaRepository.findById(episodicMedia.getEpisodicTitle())).thenReturn(optional1);
        Episode episode2=mediaService.getEpisodeById(episodicMedia.getEpisodicTitle(),10);

        Assert.assertEquals(episode,episode2);
    }

    @Test
    public void getAllEpisodesTest_returnsListOfEpisodesOfEpisodicMedia() throws MediaNotFoundException {
        when(episodicMediaRepository.existsById(episodicMedia.getEpisodicTitle())).thenReturn(true);
        when(episodicMediaRepository.findById(episodicMedia.getEpisodicTitle())).thenReturn(optional1);
        List<Episode> episodes=mediaService.getAllEpisodes(episodicMedia.getEpisodicTitle());

        Assert.assertEquals(true,episodes.contains(episode));
    }

    @Test(expected = MediaNotFoundException.class)
    public void getAllEpisodesFailureTest_throwsMediaNotFoundException() throws MediaNotFoundException {
        when(episodicMediaRepository.existsById(episodicMedia.getEpisodicTitle())).thenReturn(false);
        when(episodicMediaRepository.findById(episodicMedia.getEpisodicTitle())).thenReturn(optional1);
        List<Episode> episodes=mediaService.getAllEpisodes(episodicMedia.getEpisodicTitle());

        Assert.assertEquals(true,episodes.contains(episode));
    }
}