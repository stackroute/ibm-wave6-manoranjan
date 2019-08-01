package com.stackroute.standalonemediaservice.repository;

import com.stackroute.standalonemediaservice.domain.StandaloneMedia;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StandaloneRepository extends MongoRepository<StandaloneMedia, String> {
}
