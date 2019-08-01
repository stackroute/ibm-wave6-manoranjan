package com.stackroute.mediaManagerService.controller;

import com.stackroute.mediaManagerService.domain.*;
import com.stackroute.mediaManagerService.exceptions.GlobalControllerHandler;
import com.stackroute.mediaManagerService.service.MediaService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest
public class MediaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MediaService mediaService;

    @InjectMocks
    private MediaController mediaController;

    private Media media;
    private EpisodicMedia episodicMedia;

    private List<Media> mediaList=null;
    private List<EpisodicMedia> episodicMediaList=null;

    private List<Episode> episodes;

    private Episode episode;
    private Episode episode1;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc= MockMvcBuilders.standaloneSetup(mediaController).setControllerAdvice(new GlobalControllerHandler()).build();
        media=new Media();

        media.setMediaTitle("2 states");
        media.setMediaCategory("Movie");
        media.setMediaSynopsis("Movies based on people from 2 different states");

        List<String> genres = new ArrayList<String>();
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

        Crew crew = new Crew("Chetan Bhagat", "Writer");
        Cast cast = new Cast("Krish", "Arjun");
        Cast cast1 = new Cast("Ananya", "Alia");

        List<Crew> crews = new ArrayList<>();
        crews.add(crew);

        media.setMediaCrew(crews);

        List<Cast> casts = new ArrayList<>();
        casts.add(cast);
        casts.add(cast1);

        media.setMediaCast(casts);
        media.setMediaUrl("2states.mp4");
        media.setMediaTrailerUrl("trailer.mp4");
        media.setMediaType("Free");

        mediaList = new ArrayList<>();
        mediaList.add(media);

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

        Date dateEM = simpleDateFormat.parse("2019-06-09");
        episode.setEpisodeReleaseDate(dateEM);

        episode1 = new Episode();
        episode1.setEpisodeNo(2);
        episode1.setEpisodeDescription("Second meeting");
        episode1.setEpisodeUrl("video.mp4");
        episode1.setEpisodePosterUrl("https://cdn.pinkvilla.com/files/styles/contentpreview/public/yehrishtatrptopperweek.jpg?itok=p9vofO7z");

        Date date1 = simpleDateFormat.parse("2019-06-10");
        episode1.setEpisodeReleaseDate(date1);

        episodes = new ArrayList<>();
        episodes.add(episode);

        episodicMedia.setEpisodeList(episodes);

        episodicMediaList = new ArrayList<>();
        episodicMediaList.add(episodicMedia);

    }

    @After
    public void tearDown() throws Exception {
        mediaService.deleteAll();
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //test case for savemedia
    @Test
    public void saveMediaTest_returnOkHttpStatus() throws Exception {
        when(mediaService.saveMedia(any())).thenReturn(media);
        mockMvc.perform(MockMvcRequestBuilders.post("/stream/v1/media")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(media)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    //testcase for save media failure
    @Test
    public void saveMediaTest_returnConflictHttpStatus() throws Exception {
        when(mediaService.saveMedia(any())).thenReturn(null);
        mockMvc.perform(MockMvcRequestBuilders.post("/stream/v1/media")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(null)))
                .andExpect(MockMvcResultMatchers.status().isConflict())
                .andDo(MockMvcResultHandlers.print());
    }

    //testcase for save serial
    @Test
    public void saveSerialTest_returnOkHttpStatus() throws Exception {
        when(mediaService.saveSerial(any())).thenReturn(episodicMedia);
        mockMvc.perform(MockMvcRequestBuilders.post("/stream/v1/serial")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(episodicMedia)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    //testcase for saveserial failure
    @Test
    public void saveSerialTest_returnConflictHttpStatus() throws Exception {
        when(mediaService.saveSerial(any())).thenReturn(null);
        mockMvc.perform(MockMvcRequestBuilders.post("/stream/v1/serial")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(null)))
                .andExpect(MockMvcResultMatchers.status().isConflict())
                .andDo(MockMvcResultHandlers.print());
    }

    //testcase for save episode
    @Test
    public void saveEpisodeTest_returnOkHttpStatus() throws Exception {
        when(mediaService.addEpisode(any(), any())).thenReturn(episode);
        mockMvc.perform(MockMvcRequestBuilders.post("/stream/v1/episode/Yeh rista")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(episode)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    //testcase for save serial failure
    @Test
    public void saveEpisodeTest_returnConflictHttpStatus() throws Exception {
        when(mediaService.addEpisode(any(), any())).thenReturn(null);
        mockMvc.perform(MockMvcRequestBuilders.post("/stream/v1/episode/Yeh rista")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(null)))
                .andExpect(MockMvcResultMatchers.status().isConflict())
                .andDo(MockMvcResultHandlers.print());
    }

    //testcase for getAllMedia
    @Test
    public void getAllMediasTest_returnOkHttpStatus() throws Exception {
        when(mediaService.getAllMedia()).thenReturn(mediaList);
        mockMvc.perform(MockMvcRequestBuilders.get("/stream/v1/medias")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(media)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    //testcase for getallserials
    @Test
    public void getAllSerialsTest() throws Exception {
        when(mediaService.getAllSerials()).thenReturn(episodicMediaList);
        mockMvc.perform(MockMvcRequestBuilders.get("/stream/v1/serials")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(episodicMedia)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    //testcase for getallepisodes
    @Test
    public void getAllEpisodesTest() throws Exception {
        when(mediaService.getAllEpisodes("Yeh rishta")).thenReturn(episodes);
        mockMvc.perform(MockMvcRequestBuilders.get("/stream/v1/episodes/Yeh rishta")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(episode)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    //testcase for getmediabyid
    @Test
    public void getMediaByIdTest() throws Exception {
        when(mediaService.getMediaById("2 states")).thenReturn(media);
        mockMvc.perform(MockMvcRequestBuilders.get("/stream/v1/media/2 states")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(media)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    //testcase for getserialbyId
    @Test
    public void getSerialByIdTest() throws Exception {
        when(mediaService.getSerialByTitle("Yeh rishta")).thenReturn(episodicMedia);
        mockMvc.perform(MockMvcRequestBuilders.get("/stream/v1/serial/Yeh rishta")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(episodicMedia)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    //testcase for getmediabygenre
    @Test
    public void getMediaByGenreTest() throws Exception {
        when(mediaService.getMediaByGenre("action")).thenReturn(mediaList);
        mockMvc.perform(MockMvcRequestBuilders.get("/stream/v1/media/movie/action")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(mediaList)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    //testcase for get serial by language
    @Test
    public void getSerialByLanguageTest() throws Exception {
        when(mediaService.getTvSerialByLanguage("Hindi")).thenReturn(episodicMediaList);
        mockMvc.perform(MockMvcRequestBuilders.get("/stream/v1/series/tv/Hindi")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(episodicMediaList)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    //testcase for get specific category media
    @Test
    public void getSpecificCategoryMediaTest() throws Exception {
        when(mediaService.getMediaByCategory("Movie")).thenReturn(mediaList);
        mockMvc.perform(MockMvcRequestBuilders.get("/stream/v1/media/category/Movie")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(mediaList)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    //testcase for get episode by category
    @Test
    public void getEpisodicByCategoryTest() throws Exception {
        when(mediaService.getSerialByCategory("TV Episodes")).thenReturn(episodicMediaList);
        mockMvc.perform(MockMvcRequestBuilders.get("/stream/v1/series/category/TV Episodes")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(episodicMediaList)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    //testcase for deleting media
    @Test
    public void deleteMediaTest() throws Exception {
        when(mediaService.deleteMedia(media.getMediaTitle())).thenReturn(media);
        mockMvc.perform(MockMvcRequestBuilders.delete("/stream/v1/media/2 states")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(media)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    //testcase for deleting serial
    @Test
    public void deleteSerialTest() throws Exception {
        when(mediaService.deleteSerial(episodicMedia.getEpisodicTitle())).thenReturn(episodicMedia);
        mockMvc.perform(MockMvcRequestBuilders.delete("/stream/v1/serial/Yeh rishta")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(episodicMedia)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

}