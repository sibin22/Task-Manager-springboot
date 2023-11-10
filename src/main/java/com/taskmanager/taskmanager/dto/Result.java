package com.taskmanager.taskmanager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {
    @Id
    private String id;
    private long total_task;
    private long total_todo_task;
    private long total_in_progress_task;
    private long total_completed_task;

    }

