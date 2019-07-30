package com.stackroute.recommendationService.repository;

import com.stackroute.recommendationService.domain.Media;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Collection;

@Repository
public interface MediaRepository extends Neo4jRepository<Media,Long> {

    @Query("MATCH (m:Media) RETURN m")
    Collection<Media> getAllMedias();

    Media getByTitle(@Param("title") String title);

    @Query("CREATE (m:Media {title:{title}, genre:{genre}, language:{language}})-[r:Language]->(l:Language {language:m.language})")
    Media saveNewMediaLanguage(@Param("title") String title, @Param("genre") String genre, @Param("language") String language);

    @Query("CREATE (m:Media {title:{title}, genre:{genre}, language:{language}})")
    Media createMediaNode(@Param("title") String title, @Param("genre") String genre, @Param("language") String language);

    @Query("MATCH (m:Media),(l:Language) WHERE m.title={title} and l.language={language} CREATE (m)-[r:Language]->(l) RETURN m, r, l")
    Media createLanguageRelation(@Param("title") String title, @Param("language") String language);

    @Query("CREATE (g:Genre {genre:{genre}})")
    Media createGenreNode(@Param("genre") String genre);

    @Query("MATCH (m:Media),(g:Genre) WHERE m.title={title} and g.genre={genre} CREATE (m)-[r:Genre]->(g) RETURN m, r, g")
     Media createGenreRelation(@Param("title") String title, @Param("genre") String genre);

}
