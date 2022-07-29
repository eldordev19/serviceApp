package com.company.serviceapp.controller;

import com.company.serviceapp.dto.AnswerDto;
import com.company.serviceapp.dto.TaskDto;
import com.company.serviceapp.model.Status;
import com.company.serviceapp.model.Task;
import com.company.serviceapp.repository.StatusRepository;
import com.company.serviceapp.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    TaskService taskService;

    @Autowired
    StatusRepository statusRepository;

    @GetMapping("/")
    public String homePage(Model model) {

        first(model);

        List<Status> statusList = statusRepository.findAll();

        model.addAttribute("status1", statusList.get(0).getName());
        model.addAttribute("status2", statusList.get(1).getName());


        return "index";
    }

    private void first(Model model) throws NullPointerException{
        List<Task> dailyTasks = taskService.getDailyTasks();

        List<Task> finishedDailyTasks = taskService.getDailyFinishedTasks();

        List<Task> unFinishedDailyTasks = taskService.getDailyUnFinishedTasks();

        List<Task> cannotFinishTasks = taskService.getDailyCannotFinishTasks();

        List<Task> tasksForShow = getTasksForShow(dailyTasks);

        if (dailyTasks.size() == 0) {
            model.addAttribute("countAllTasks", 1);
            model.addAttribute("finishedPercent", 0);
            model.addAttribute("unFinishedPercent", 0);
            model.addAttribute("cannotFinishPercent", 0);
        } else {
            double i = (finishedDailyTasks.size() / dailyTasks.size()) * 100;
            model.addAttribute("countAllTasks", dailyTasks.size());
            model.addAttribute("finishedPercent", i);
            double x = unFinishedDailyTasks.size()/dailyTasks.size() * 100;
            model.addAttribute("unFinishedPercent", x);
            model.addAttribute("cannotFinishPercent", (cannotFinishTasks.size()/dailyTasks.size() * 100));
        }

        model.addAttribute("dailyTasks", dailyTasks);
        model.addAttribute("countFinishedTasks", finishedDailyTasks.size());
        model.addAttribute("countUnFinishedTasks", unFinishedDailyTasks.size());
        model.addAttribute("countCannotFinishTasks", cannotFinishTasks.size());

        model.addAttribute("tasksForShow", tasksForShow);

        model.addAttribute("day", LocalDate.now());
    }

    private List<Task> getTasksForShow(List<Task> dailyTasks) {

        if (dailyTasks.size() < 5) {
            return dailyTasks;
        }

        List<Task> tasksForShow = new ArrayList<>();

        tasksForShow.add(dailyTasks.get(0));

        tasksForShow.add(dailyTasks.get(1));

        tasksForShow.add(dailyTasks.get(2));

        tasksForShow.add(dailyTasks.get(3));

        tasksForShow.add(dailyTasks.get(4));

        return tasksForShow;
    }

    @GetMapping("/home")
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
            redirectAttrs.addFlashAttribute("finishedPercent", 0);
            redirectAttrs.addFlashAttribute("unFinishedPercent", 0);
            redirectAttrs.addFlashAttribute("cannotFinishPercent", 0);
            redirectAttrs.addFlashAttribute("countAllTasks", 1);
        } else {
            redirectAttrs.addFlashAttribute("finishedPercent", (finishedDailyTasks.size()/tasks.size()) * 100);
            redirectAttrs.addFlashAttribute("unFinishedPercent", (unFinishedDailyTasks.size()/tasks.size()) * 100);
            redirectAttrs.addFlashAttribute("cannotFinishPercent", (cannotFinishTasks.size()/tasks.size() * 100));
            redirectAttrs.addFlashAttribute("countAllTasks", tasks.size());
        }

        redirectAttrs.addFlashAttribute("dailyTasks", tasks);
        redirectAttrs.addFlashAttribute("countAllTasks", tasks.size());
        redirectAttrs.addFlashAttribute("countFinishedTasks", finishedDailyTasks.size());
        redirectAttrs.addFlashAttribute("countUnFinishedTasks", unFinishedDailyTasks.size());
        redirectAttrs.addFlashAttribute("countCannotFinishTasks", cannotFinishTasks.size());

            redirectAttrs.addFlashAttribute("day", LocalDate.now().minusDays(day));

        List<Task> dailyTasks = taskService.getDailyTasks();

        List<Task> tasksForShow = getTasksForShow(dailyTasks);

        redirectAttrs.addFlashAttribute("tasksForShow", tasksForShow);

        return "redirect:/home";
    }
}
