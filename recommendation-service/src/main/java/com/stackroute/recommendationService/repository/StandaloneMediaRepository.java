package com.stackroute.recommendationService.repository;

import com.stackroute.recommendationService.domain.StandaloneMedia;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Collection;

@Repository
public interface StandaloneMediaRepository extends Neo4jRepository<StandaloneMedia,Long> {

    @Query("MATCH (s:StandaloneMedia) RETURN s")
    Collection<StandaloneMedia> getAllStandaloneMedias();

    StandaloneMedia getStandaloneMediaByTitle(@Param("title") String title);

    @Query("CREATE (s:StandaloneMedia {title:{title}, genre:{genre}, language:{language}})-[r:Language]->(l:Language {language:s.language})")
    StandaloneMedia saveNewStandaloneMediaLanguage(@Param("title") String title, @Param("genre") String genre, @Param("language") String language);

    @Query("CREATE (s:StandaloneMedia {title:{title}, genre:{genre}, language:{language}})")
    StandaloneMedia createStandaloneMediaNode(@Param("title") String title, @Param("genre") String genre, @Param("language") String language);

    @Query("MATCH (s:StandaloneMedia),(l:Language) WHERE s.title={title} and l.language={language} CREATE (s)-[r:Language]->(l) RETURN s, r, l")
    StandaloneMedia createLanguageRelation(@Param("title") String title, @Param("language") String language);

    @Query("CREATE (g:Genre {genre:{genre}})")
    StandaloneMedia createGenreNode(@Param("genre") String genre);

    @Query("MATCH (s:StandaloneMedia),(g:Genre) WHERE s.title={title} and g.genre={genre} CREATE (s)-[r:Genre]->(g) RETURN s, r, g")
    StandaloneMedia createGenreRelation(@Param("title") String title, @Param("genre") String genre);

}
