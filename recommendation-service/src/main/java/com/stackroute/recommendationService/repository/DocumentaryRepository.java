package com.stackroute.recommendationService.repository;

import com.stackroute.recommendationService.domain.Documentary;
import com.stackroute.recommendationService.domain.EpisodicMedia;
import com.stackroute.recommendationService.domain.StandaloneMedia;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Collection;

@Repository
public interface DocumentaryRepository extends Neo4jRepository<Documentary, Long> {

    @Query("MATCH (d:Documentary) RETURN d")
    Collection<Documentary> getAllDocumentary();

    Documentary findDocumentaryByTitle(@Param("title") String title);

    @Query("CREATE (d:Documentary {title:{title}})")
    Documentary createDocumentaryNode(@Param("title") String title);

    @Query("MATCH (d:Documentary),(l:Language) WHERE d.title={title} and l.language={language} CREATE (d)-[r:In_Language]->(l) RETURN d, r, l")
    Documentary createLanguageRelation(@Param("title") String title, @Param("language") String language);

    @Query("MATCH (d:Documentary),(g:Genre) WHERE d.title={title} and g.genre={genre} CREATE (d)-[r:Of_Genre]->(g) RETURN d, r, g")
    Documentary createGenreRelation(@Param("title") String title, @Param("genre") String genre);

    @Query("MATCH (d:Documentary),(c:Category) WHERE d.title={title} and c.category={category} CREATE (d)-[r:Of_Category]->(c) RETURN d, r, c")
    Documentary createCategoryRelation(@Param("title") String title, @Param("category") String category);

}
