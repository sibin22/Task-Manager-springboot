package com.taskmanager.taskmanager.controller;

import com.taskmanager.taskmanager.domain.Management;
import com.taskmanager.taskmanager.domain.Status;
import com.taskmanager.taskmanager.domain.Task;
import com.taskmanager.taskmanager.dto.Result;
import com.taskmanager.taskmanager.service.ManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/assign")
public class ManagementController {
@Autowired
    ManagementService managementService;
     @GetMapping("")
     public ResponseEntity<List<Management>> getTaskDetails(){
     return ResponseEntity.ok(managementService.getAllTaskDetails());
}
    @PostMapping("/add/{userId}/{taskId}")
    public ResponseEntity<Management> assignTask(@PathVariable String userId,
                                        @PathVariable String taskId){
        return ResponseEntity.ok(managementService.assignTask(userId,taskId));}
    @PutMapping("/status/update/{taskId}")
    public ResponseEntity<Management> assignTask(@PathVariable String taskId,
                                                 @RequestBody Management status){
        return ResponseEntity.ok(managementService.statusUpdate(taskId,status));}
    @GetMapping("/count")
    public ResponseEntity<List<Result>> getTaskCount(){
        return ResponseEntity.ok(managementService.getAllTaskCount());}
    @GetMapping("/count/project/{id}")
    public ResponseEntity<List<Result>> getTaskCountByProjectId(@PathVariable (value="id") String id){
        return ResponseEntity.ok(managementService.getTaskCountByProjectId(id));}

    @GetMapping("/count/user/{id}")
    public ResponseEntity<List<Result>> getTaskCountByUserId(@PathVariable (value="id") String id){
        return ResponseEntity.ok(managementService.getTaskCountByUserId(id));}
}
