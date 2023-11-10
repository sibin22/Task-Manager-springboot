package com.taskmanager.taskmanager.respository;

import com.taskmanager.taskmanager.domain.Management;
import com.taskmanager.taskmanager.domain.Task;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends MongoRepository<Task,String> {
    @Query(value = "{'user.id': ?0}")
    List<Task> findTaskByUserId(String userId);
    @Query(value = "{ 'user.id': ?0 }")
    List<Task> findProjectsByUserId(String userId);

    @Query(value = "{ 'project.id': ?0 }")
    List<Task> findTaskByProjectId(String id);
}

