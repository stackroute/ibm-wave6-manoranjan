package com.stackroute.recommendationService.repository;

import com.stackroute.recommendationService.domain.Viewer;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Collection;

@Repository
public interface ViewerRepository extends Neo4jRepository<Viewer, Long> {

    @Query("MATCH (v:Viewer) RETURN v")
    Collection<Viewer> getAllViewers();

    Viewer findByEmailId(@Param("emailId") String emailId);

    @Query("CREATE (v:Viewer {name:{name}, emailId:{emailId}})")
    Viewer createViewer(@Param("name") String name, @Param("emailId") String emailId);

    //creating viewer and genre relation
    @Query("MATCH (v:Viewer),(g:Genre) WHERE v.emailId={emailId} and g.genre={genre} CREATE (v)-[r:Interested_In]->(g) RETURN v")
    public Viewer createGenreRelation(@Param("emailId") String emailId, @Param("genre") String genre);

    @Query("MATCH (v:Viewer),(s:StandaloneMedia) WHERE v.emailId={emailId} and s.title={title} CREATE (v)-[r:Watches]->(s) RETURN r")
    public Viewer createStandaloneMediaRelation(@Param("emailId") String emailId, @Param("title") String title);

    @Query("MATCH (v:Viewer),(e:EpisodicMedia) WHERE v.emailId={emailId} and e.title={title} CREATE (v)-[r:Watches]->(e) RETURN r")
    public Viewer createEpisodicMediaRelation(@Param("emailId") String emailId, @Param("title") String title);

}
