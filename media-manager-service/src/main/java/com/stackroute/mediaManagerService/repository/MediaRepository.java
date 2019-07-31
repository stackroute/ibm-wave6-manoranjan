package com.stackroute.mediaManagerService.repository;

import com.stackroute.mediaManagerService.domain.Media;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MediaRepository extends MongoRepository<Media, String> {
}
