package com.taskmanager.taskmanager.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.*;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("task")
public class Task {
    @Id
    private String id;
    private String title;
    private String description;
    private LocalDate dueDate;

//    @Field("status")
//    private Status status;

    @DocumentReference(lazy = false)
    private Project project;

//    @DocumentReference(lazy = false)
//    private User user;
}
