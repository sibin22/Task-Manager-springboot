package com.taskmanager.taskmanager.service;

import com.taskmanager.taskmanager.domain.*;
import com.taskmanager.taskmanager.dto.Result;
import com.taskmanager.taskmanager.respository.ManagementRepository;
import com.taskmanager.taskmanager.respository.ProjectRepository;
import com.taskmanager.taskmanager.respository.TaskRepository;
import com.taskmanager.taskmanager.respository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskService {
    @Autowired
    TaskRepository taskRepository;
    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ManagementRepository managementRepository;

    @Autowired
    private MongoTemplate mongoTemplate;
    public Task addTask(String projectId, Task task) {
        Optional<Project> project = projectRepository.findById(projectId);

        Task task1 = new Task();
        task1.setTitle(task.getTitle());
        task1.setDescription(task.getDescription());
        task1.setDueDate(task.getDueDate());
        task1.setProject(project.get());

        return taskRepository.save(task1);
    }

    public List<Task> getTask() {
        return  taskRepository.findAll();
    }}

