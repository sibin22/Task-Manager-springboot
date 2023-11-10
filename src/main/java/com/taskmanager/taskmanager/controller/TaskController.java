package com.taskmanager.taskmanager.controller;

import com.taskmanager.taskmanager.domain.Task;
import com.taskmanager.taskmanager.dto.Result;
import com.taskmanager.taskmanager.service.TaskService;
import com.taskmanager.taskmanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/task")
public class TaskController {
    @Autowired
    TaskService taskService;
    @PostMapping("/add/{projectId}")
    public ResponseEntity<Task> addTask(@PathVariable String projectId,
                                        @RequestBody Task task){
        return ResponseEntity.ok(taskService.addTask(projectId,task));
    }
    @GetMapping("")
    public ResponseEntity<List<Task>> getAllTask(){
        return ResponseEntity.ok(taskService.getTask());
    }

}
