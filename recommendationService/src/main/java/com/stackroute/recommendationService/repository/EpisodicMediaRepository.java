package com.stackroute.recommendationService.repository;

import com.stackroute.recommendationService.domain.EpisodicMedia;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Collection;

@Repository
public interface EpisodicMediaRepository extends Neo4jRepository<EpisodicMedia,Long> {

    @Query("MATCH (e:EpisodicMedia) RETURN e")
    Collection<EpisodicMedia> getAllEpisodicMedias();

    EpisodicMedia findByTitle(@Param("title") String title);

    @Query("CREATE (e:EpisodicMedia {title:{title}, language:{language}})-[r:Language]->(l:Language {language:e.language})")
    EpisodicMedia saveNewEpisodicMediaLanguage(@Param("title") String title, @Param("language") String language);

    @Query("CREATE (e:EpisodicMedia {title:{title}, language:{language}})")
    EpisodicMedia createEpisodicMediaNode(@Param("title") String title, @Param("language") String language);

    @Query("MATCH (e:EpisodicMedia),(l:Language) WHERE e.title={title} and l.language={language} CREATE (e)-[r:Language]->(l) RETURN e, r, l")
    EpisodicMedia createLanguageRelation(@Param("title") String title, @Param("language") String language);

}