package com.stackroute.recommendationService.repository;

import com.stackroute.recommendationService.domain.Documentary;
import com.stackroute.recommendationService.domain.EpisodicMedia;
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
    WebSeries findWebSeriesByTitle(@Param("title") String title);

    //query to create WebSeries node
    @Query("CREATE (w:WebSeries {title:{title}})")
    WebSeries createWebSeriesNode(@Param("title") String title);

    //query to create WebSeries and language relation
    @Query("MATCH (w:WebSeries),(l:Language) WHERE w.title={title} and l.language={language} CREATE (w)-[r:In_Language]->(l) RETURN w, r, l")
    WebSeries createLanguageRelation(@Param("title") String title, @Param("language") String language);

    //query to create WebSeries and category relation
    @Query("MATCH (w:WebSeries),(c:Category) WHERE w.title={title} and c.category={category} CREATE (w)-[r:Of_Category]->(c) RETURN w, r, c")
    Documentary createCategoryRelation(@Param("title") String title, @Param("category") String category);

}
