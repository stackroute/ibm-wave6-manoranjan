package com.stackroute.recommendationService.repository;

import com.stackroute.recommendationService.domain.Documentary;
import com.stackroute.recommendationService.domain.EpisodicMedia;
import com.stackroute.recommendationService.domain.TvEpisodes;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Collection;

@Repository
public interface TvEpisodesRepository extends Neo4jRepository<TvEpisodes, Long> {

    @Query("MATCH (t:TvEpisodes) RETURN t")
    Collection<TvEpisodes> getAllTvEpisodes();

    TvEpisodes findTvEpisodeByTitle(@Param("title") String title);

    @Query("CREATE (t:TvEpisodes {title:{title}})")
    TvEpisodes createTvEpisodesNode(@Param("title") String title);

    @Query("MATCH (t:TvEpisodes),(l:Language) WHERE t.title={title} and l.language={language} CREATE (t)-[r:In_Language]->(l) RETURN t, r, l")
    TvEpisodes createLanguageRelation(@Param("title") String title, @Param("language") String language);

    @Query("MATCH (t:TvEpisodes),(c:Category) WHERE t.title={title} and c.category={category} CREATE (t)-[r:Of_Category]->(c) RETURN t, r, c")
    Documentary createCategoryRelation(@Param("title") String title, @Param("category") String category);
}
