package com.taskmanager.taskmanager.service;

import com.taskmanager.taskmanager.domain.*;
import com.taskmanager.taskmanager.dto.Result;
import com.taskmanager.taskmanager.respository.ManagementRepository;
import com.taskmanager.taskmanager.respository.ProjectRepository;
import com.taskmanager.taskmanager.respository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import java.util.*;

import static org.springframework.data.mongodb.core.aggregation.ArrayOperators.Filter.filter;
import static org.springframework.data.mongodb.core.aggregation.ComparisonOperators.Eq.valueOf;

@Service
public class ProjectService {
@Autowired
    ProjectRepository projectRepository;
@Autowired
    UserRepository userRepository;
@Autowired
    ManagementRepository managementRepository;
@Autowired
    MongoTemplate mongoTemplate;

    public List<Project> getProject() {
        return projectRepository.findAll();
    }


    public String addProject( Project project) {
        List<Project> project1= projectRepository.findNameExist(project.getName());
        boolean nameExists = project1.stream()
                .anyMatch(obj -> obj.getName() != null && obj.getName()
                        .equals(project.getName()));

     if(nameExists){
         return "Project Name already exist";
     }
      else{
        projectRepository.save(project);
        return "Project Added successful";}

    }



      //    List<Management> allTasks = managementRepository.findTaskByProjectId(id);
        //  System.out.println(allTasks);


   //     long totalTasks = allTasks.stream().count();
//        long filteredTodoTasks = allTasks.stream()
//                .filter(task -> task.getTask().getStatus() == Status.TODO).count();
//        long filteredInProgressTasks = allTasks.stream()
//                .filter(task ->  task.getTask().getStatus() == Status.IN_PROGRESS).count();
//        long filteredCompletedTasks = allTasks.stream()
//                .filter(task ->  task.getTask().getStatus() == Status.COMPLETED).count();

  //      Result result=new Result();
//        result.setTotal_task(totalTasks);
//        result.setTotal_completed_task(filteredCompletedTasks);
//        result.setTotal_todo_task(filteredTodoTasks);
//        result.setTotal_in_progress_task(filteredInProgressTasks);
  //      return ResponseEntity.ok(result);}

}

