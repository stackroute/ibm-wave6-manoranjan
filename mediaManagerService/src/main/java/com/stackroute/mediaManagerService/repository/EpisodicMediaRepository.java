package com.stackroute.mediaManagerService.repository;

import com.stackroute.mediaManagerService.domain.EpisodicMedia;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EpisodicMediaRepository extends MongoRepository<EpisodicMedia,String> {
}
