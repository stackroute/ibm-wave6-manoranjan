package com.stackroute.recommendationService.repository;

import com.stackroute.recommendationService.domain.Viewer;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Collection;

@Repository
public interface ViewerRepository extends Neo4jRepository<Viewer, Long> {

    //Get all viewers
    @Query("MATCH (v:Viewer) RETURN v")
    Collection<Viewer> getAllViewers();

    //find a viewer by its email id
    Viewer findByEmailId(@Param("emailId") String emailId);

    //
    @Query("CREATE (v:Viewer {name:{name}, emailId:{emailId}})")
    Viewer createViewer(@Param("name") String name, @Param("emailId") String emailId);

    //creating viewer and genre relation
    @Query("MATCH (v:Viewer),(g:Genre) WHERE v.emailId={emailId} and g.genre={genre} CREATE (v)-[r:Interested_In]->(g) RETURN v")
    public Viewer createGenreRelation(@Param("emailId") String emailId, @Param("genre") String genre);

    @Query("MATCH (v:Viewer),(d:Documentary) WHERE v.emailId={emailId} and d.title={title} CREATE (v)-[r:Watches]->(d) RETURN r")
    public Viewer createDocumentaryRelation(@Param("emailId") String emailId, @Param("title") String title);

    @Query("MATCH (v:Viewer),(m:Movie) WHERE v.emailId={emailId} and m.title={title} CREATE (v)-[r:Watches]->(m) RETURN r")
    public Viewer createMovieRelation(@Param("emailId") String emailId, @Param("title") String title);

    @Query("MATCH (v:Viewer),(t:TvEpisodes) WHERE v.emailId={emailId} and t.title={title} CREATE (v)-[r:Watches]->(t) RETURN r")
    public Viewer createTvEpisodesRelation(@Param("emailId") String emailId, @Param("title") String title);

    @Query("MATCH (v:Viewer),(w:WebSeries) WHERE v.emailId={emailId} and w.title={title} CREATE (v)-[r:Watches]->(w) RETURN r")
    public Viewer createWebSeriesRelation(@Param("emailId") String emailId, @Param("title") String title);

}
