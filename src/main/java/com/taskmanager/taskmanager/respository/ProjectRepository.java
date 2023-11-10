package com.taskmanager.taskmanager.respository;

import com.taskmanager.taskmanager.domain.Project;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends MongoRepository<Project,String> {

    @Query(value = "{ 'name': ?0 }")
    List<Project> findNameExist(String name);

}
