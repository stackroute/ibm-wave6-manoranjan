package com.stackroute.repository;

import com.stackroute.domain.UserPackage;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserPackageRepository extends MongoRepository<UserPackage,String> {
}
