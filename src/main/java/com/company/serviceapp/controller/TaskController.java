package com.company.serviceapp.controller;

import com.company.serviceapp.model.Status;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/task/")
public class TaskController {

    @GetMapping("/workplace")
    public String homePage(Model model) {

        return "workplace";
    }
}
