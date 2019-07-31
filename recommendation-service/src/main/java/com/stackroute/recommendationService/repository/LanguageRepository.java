package com.stackroute.recommendationService.repository;

import com.stackroute.recommendationService.domain.Language;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface LanguageRepository extends Neo4jRepository<Language, Long> {
    @Query("MATCH (l:Language) RETURN l")
    Collection<Language> getAllLanguages();

    @Query("MATCH (l:Language) WHERE l.language={language} RETURN l")
    Language findLanguageByName(@Param("language") String language);
}
