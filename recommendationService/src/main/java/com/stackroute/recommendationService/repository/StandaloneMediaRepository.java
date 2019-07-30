package com.stackroute.recommendationService.repository;

import com.stackroute.recommendationService.domain.StandaloneMedia;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Collection;

@Repository
public interface StandaloneMediaRepository extends Neo4jRepository<StandaloneMedia,Long> {

    @Query("MATCH (m:Media) RETURN m")
    Collection<StandaloneMedia> getAllMedias();

    StandaloneMedia getByTitle(@Param("title") String title);

    @Query("CREATE (m:Media {title:{title}, genre:{genre}, language:{language}})-[r:Language]->(l:Language {language:m.language})")
    StandaloneMedia saveNewMediaLanguage(@Param("title") String title, @Param("genre") String genre, @Param("language") String language);

    @Query("CREATE (m:Media {title:{title}, genre:{genre}, language:{language}})")
    StandaloneMedia createMediaNode(@Param("title") String title, @Param("genre") String genre, @Param("language") String language);

    @Query("MATCH (m:Media),(l:Language) WHERE m.title={title} and l.language={language} CREATE (m)-[r:Language]->(l) RETURN m, r, l")
    StandaloneMedia createLanguageRelation(@Param("title") String title, @Param("language") String language);

    @Query("CREATE (g:Genre {genre:{genre}})")
    StandaloneMedia createGenreNode(@Param("genre") String genre);

    @Query("MATCH (m:Media),(g:Genre) WHERE m.title={title} and g.genre={genre} CREATE (m)-[r:Genre]->(g) RETURN m, r, g")
    StandaloneMedia createGenreRelation(@Param("title") String title, @Param("genre") String genre);

}
