package com.stackroute.userpackage.repository;

import com.stackroute.userpackage.domain.UserPackage;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserPackageRepository extends MongoRepository<UserPackage,String> {
}
