package com.taskmanager.taskmanager.controller;

import com.taskmanager.taskmanager.domain.Project;
import com.taskmanager.taskmanager.dto.Result;
import com.taskmanager.taskmanager.service.ProjectService;
import com.taskmanager.taskmanager.service.TaskService;
import com.taskmanager.taskmanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/project")
public class ProjectController {
  @Autowired
  ProjectService projectService;


    @PostMapping("/add")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> addProject(@RequestBody Project project){
        return ResponseEntity.ok(projectService.addProject(project));
    }

     @GetMapping("")
     public List<Project> getProject(){
    return projectService.getProject();
  }

     }