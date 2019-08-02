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

    //query to get all TvEpisodes
    @Query("MATCH (t:TvEpisodes) RETURN t")
    Collection<TvEpisodes> getAllTvEpisodes();

    //query to find TvEpisodes by title
    TvEpisodes findTvEpisodeByTitle(@Param("title") String title);

    //query to create TvEpisodes node
    @Query("CREATE (t:TvEpisodes {title:{title}})")
    TvEpisodes createTvEpisodesNode(@Param("title") String title);

    //query to create TvEpisodes and language relation
    @Query("MATCH (t:TvEpisodes),(l:Language) WHERE t.title={title} and l.language={language} CREATE (t)-[r:In_Language]->(l) RETURN t, r, l")
    TvEpisodes createLanguageRelation(@Param("title") String title, @Param("language") String language);

    //query to create TvEpisodes and category relation
    @Query("MATCH (t:TvEpisodes),(c:Category) WHERE t.title={title} and c.category={category} CREATE (t)-[r:Of_Category]->(c) RETURN t, r, c")
    Documentary createCategoryRelation(@Param("title") String title, @Param("category") String category);
}
