package com.company.serviceapp.controller;

import com.company.serviceapp.dto.TaskDto;
import com.company.serviceapp.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Controller(value = "/")
public class TaskController {

    @Autowired
    TaskService taskService;

    @GetMapping
    public HttpEntity showAllTasks() {
        return ResponseEntity.ok("success");
    }

    @PostMapping
    public HttpEntity sendTask (@RequestBody TaskDto taskDto) {
        taskService.sendTask(taskDto);
        return ResponseEntity.ok("success");
    }
}
