package com.company.serviceapp.controller;

import com.company.serviceapp.dto.AnswerDto;
import com.company.serviceapp.dto.TaskDto;
import com.company.serviceapp.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping( "/")
public class TaskController {

    @Autowired
    TaskService taskService;

    @GetMapping
    public String showAllTasks() {
        return "index";
    }

    @PostMapping(value = "/send-task")
    public HttpEntity sendTask (@RequestBody TaskDto taskDto) {
        taskService.sendTask(taskDto);
        return ResponseEntity.ok("success");
    }

    @PostMapping(value = "answer-task")
    public HttpEntity answerTask (@RequestBody AnswerDto answerDto) {
        taskService.answerTask(answerDto);
        return ResponseEntity.ok("success");
    }

    @GetMapping(value = "/unfinished-tasks")
    public HttpEntity getUnfinishedTasksByDepartment() {
        return ResponseEntity.ok(taskService.getUnfinishedTasksByDepartment());
    }

    @GetMapping(value = "/finished-tasks")
    public HttpEntity getFinishedTasksByDepartment() {
        return ResponseEntity.ok(taskService.getFinishedTasksByDepartment());
    }
}
