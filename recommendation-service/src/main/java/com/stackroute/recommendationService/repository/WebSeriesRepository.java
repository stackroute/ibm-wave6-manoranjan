package com.stackroute.recommendationService.repository;

import com.stackroute.recommendationService.domain.Documentary;
import com.stackroute.recommendationService.domain.WebSeries;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Collection;

@Repository
public interface WebSeriesRepository extends Neo4jRepository<WebSeries, Long> {

    //query to get all WebSeries
    @Query("MATCH (w:WebSeries) RETURN w")
    Collection<WebSeries> getAllWebSeries();

    //query to find WebSeries by title
    WebSeries findWebSeriesByEpisodeTitle(@Param("episodeTitle") String episodeTitle);

    //query to create WebSeries node
    @Query("CREATE (w:WebSeries {episodeTitle:{episodeTitle}})")
    WebSeries createWebSeriesNode(@Param("episodeTitle") String episodeTitle);

    //query to create WebSeries and language relation
    @Query("MATCH (w:WebSeries),(l:Language) WHERE w.episodeTitle={episodeTitle} and l.language={language} CREATE (w)-[r:In_Language]->(l) RETURN w, r, l")
    WebSeries createLanguageRelation(@Param("episodeTitle") String episodeTitle, @Param("language") String language);

    //query to create WebSeries and category relation
    @Query("MATCH (w:WebSeries),(c:Category) WHERE w.episodeTitle={episodeTitle} and c.category={category} CREATE (w)-[r:Of_Category]->(c) RETURN w, r, c")
    Documentary createCategoryRelation(@Param("episodeTitle") String episodeTitle, @Param("category") String category);

}
