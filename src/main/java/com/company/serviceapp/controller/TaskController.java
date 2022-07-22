package com.company.serviceapp.controller;

import com.company.serviceapp.dto.AnswerDto;
import com.company.serviceapp.dto.TaskDto;
import com.company.serviceapp.model.Task;
import com.company.serviceapp.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.ClientInfoStatus;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class TaskController {

    @Autowired
    TaskService taskService;

    @GetMapping("/home")
    public String homePage(Model model) {

        List<Task> dailyTasks = taskService.getDailyTasks();

        List<Task> finishedDailyTasks = taskService.getDailyFinishedTasks();

        List<Task> unFinishedDailyTasks = taskService.getDailyUnFinishedTasks();

        List<Task> cannotFinishTasks = taskService.getDailyCannotFinishTasks();

        if (dailyTasks.size() == 0) {
            model.addAttribute("countAllTasks", 1);
        } else {
            model.addAttribute("countAllTasks", dailyTasks.size());
        }

        model.addAttribute("dailyTasks", dailyTasks);
        model.addAttribute("countFinishedTasks", finishedDailyTasks.size());
        model.addAttribute("countUnFinishedTasks", unFinishedDailyTasks.size());
        model.addAttribute("countCannotFinishTasks", cannotFinishTasks.size());

        return "index";
    }

    @GetMapping
    public String getIndex(Model model) {
        return "index";
    }

    @PostMapping(value = "/send-task")
    public HttpEntity sendTask(@RequestBody TaskDto taskDto) {
        taskService.sendTask(taskDto);
        return ResponseEntity.ok("success");
    }

    @PostMapping(value = "answer-task")
    public HttpEntity answerTask(@RequestBody AnswerDto answerDto) {
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

    @GetMapping("/monthly/{month}")
    public String getMonthlyTasks(@PathVariable Integer month) {
        taskService.getAllMonthlyTasks(month);
        return "index";
    }

    @GetMapping("/last/{day}")
    public String getLastTasks(@PathVariable int day, RedirectAttributes redirectAttrs) {

        List<Task> tasks = taskService.getLastTask(day);

        System.out.println(day);

        for (Task task : tasks) {
            System.out.println(task);
        }

        List<Task> finishedDailyTasks = taskService.getDailyFinishedTasks(day);

        List<Task> unFinishedDailyTasks = taskService.getDailyUnFinishedTasks(day);

        List<Task> cannotFinishTasks = taskService.getDailyCannotFinishTasks(day);

        if (tasks.size() == 0) {
            redirectAttrs.addFlashAttribute("finished", 0);
            redirectAttrs.addFlashAttribute("unFinished", 0);
            redirectAttrs.addFlashAttribute("cannotFinish", 0);
            redirectAttrs.addFlashAttribute("countAllTasks", 1);
        } else {
            redirectAttrs.addFlashAttribute("finished", finishedDailyTasks.size()/tasks.size());
            redirectAttrs.addFlashAttribute("unFinished", unFinishedDailyTasks.size()/tasks.size());
            redirectAttrs.addFlashAttribute("cannotFinish", cannotFinishTasks.size()/tasks.size());
            redirectAttrs.addFlashAttribute("countAllTasks", tasks.size());
        }

        redirectAttrs.addFlashAttribute("dailyTasks", tasks);
        redirectAttrs.addFlashAttribute("countAllTasks", tasks.size());
        redirectAttrs.addFlashAttribute("countFinishedTasks", finishedDailyTasks.size());
        redirectAttrs.addFlashAttribute("countUnFinishedTasks", unFinishedDailyTasks.size());
        redirectAttrs.addFlashAttribute("countCannotFinishTasks", cannotFinishTasks.size());

        return "redirect:/";
    }
}
