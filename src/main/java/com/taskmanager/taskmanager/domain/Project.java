package com.taskmanager.taskmanager.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("project")
public class Project {
    @Id
    private String id;
    private String name;

//    @DocumentReference(lazy = false)
//    private User user;

//    @DocumentReference(lazy = false)
//    private Task task;

}
