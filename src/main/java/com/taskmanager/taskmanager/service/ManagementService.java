package com.taskmanager.taskmanager.service;

import com.taskmanager.taskmanager.domain.*;
import com.taskmanager.taskmanager.dto.Result;
import com.taskmanager.taskmanager.respository.ManagementRepository;
import com.taskmanager.taskmanager.respository.ProjectRepository;
import com.taskmanager.taskmanager.respository.TaskRepository;
import com.taskmanager.taskmanager.respository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.ConditionalOperators;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ManagementService {
    @Autowired
    ManagementRepository managementRepository;
    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    TaskRepository taskRepository;
    @Autowired
    MongoTemplate mongoTemplate;

    public Management assignTask(String userId, String taskId) {
        Optional<Task> task = taskRepository.findById(taskId);
        Optional<User> user = userRepository.findById(userId);
        Status initialStatus = Status.TODO;

        Management task1 = new Management();
        task1.setUser(user.get());
        task1.setProject(task.get().getProject());
        task1.setTask(task.get());
        task1.setStatus(initialStatus);

        return managementRepository.save(task1);
    }

    public List<Management> getAllTaskDetails() {
        return managementRepository.findAll();
    }

    public List<Result> getAllTaskCount() {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.group()
                        .count().as("total_task")
                        .sum(
                                ConditionalOperators.when(Criteria.where("status").is("COMPLETED")).then(1).otherwise(0)
                        ).as("total_completed_task")
                        .sum(
                                ConditionalOperators.when(Criteria.where("status").is("IN_PROGRESS")).then(1).otherwise(0)
                        ).as("total_in_progress_task")
                        .sum(
                                ConditionalOperators.when(Criteria.where("status").is("TODO")).then(1).otherwise(0)
                        ).as("total_todo_task"));

        AggregationResults<Result> results = mongoTemplate.aggregate(aggregation, "task_management", Result.class);
        List<Result> resultList = results.getMappedResults();
        return  resultList;
}
    public List<Result> getTaskCountByProjectId(String id) {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(Criteria.where("project").is(new ObjectId(id))),
                Aggregation.group()
                        .count().as("total_task")
                        .sum(
                                ConditionalOperators.when(Criteria.where("status").is("COMPLETED")).then(1).otherwise(0)
                        ).as("total_completed_task")
                        .sum(
                                ConditionalOperators.when(Criteria.where("status").is("IN_PROGRESS")).then(1).otherwise(0)
                        ).as("total_in_progress_task")
                        .sum(
                                ConditionalOperators.when(Criteria.where("status").is("TODO")).then(1).otherwise(0)
                        ).as("total_todo_task"));

        AggregationResults<Result> results = mongoTemplate.aggregate(aggregation, "task_management", Result.class);
        List<Result> resultList = results.getMappedResults();
        return  resultList;
    }

    public List<Result> getTaskCountByUserId(String id) {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(Criteria.where("user").is(new ObjectId(id))),
                Aggregation.group()
                        .count().as("total_task")
                        .sum(
                                ConditionalOperators.when(Criteria.where("status").is("COMPLETED")).then(1).otherwise(0)
                        ).as("total_completed_task")
                        .sum(
                                ConditionalOperators.when(Criteria.where("status").is("IN_PROGRESS")).then(1).otherwise(0)
                        ).as("total_in_progress_task")
                        .sum(
                                ConditionalOperators.when(Criteria.where("status").is("TODO")).then(1).otherwise(0)
                        ).as("total_todo_task"));

        AggregationResults<Result> results = mongoTemplate.aggregate(aggregation, "task_management", Result.class);
        List<Result> resultList = results.getMappedResults();
        return  resultList;
    }

    public Management statusUpdate(String taskId,Management status) {
        Management management = managementRepository.findByTaskId(taskId);
        management.setStatus(status.getStatus());
        return managementRepository.save(management);
    }
}