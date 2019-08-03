package com.stackroute.episodicmediaservice.repository;

import com.stackroute.episodicmediaservice.domain.EpisodicMedia;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EpisodicRepository extends MongoRepository<EpisodicMedia, String> {
}
