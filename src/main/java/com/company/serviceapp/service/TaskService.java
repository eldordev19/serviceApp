package com.company.serviceapp.service;

import com.company.serviceapp.dto.AnswerDto;
import com.company.serviceapp.dto.TaskDto;
import com.company.serviceapp.model.Answer;
import com.company.serviceapp.model.Status;
import com.company.serviceapp.model.Task;
import com.company.serviceapp.model.User;
import com.company.serviceapp.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class TaskService {

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    StatusRepository statusRepository;

    @Autowired
    AnswerRepository answerRepository;

    private final UUID userId = UUID.fromString("ef7db7f3-d0de-4e62-a78c-52ef969ec81b");

    public void sendTask(TaskDto taskDto) {

        User user = userRepository.findById(userId).get();

        Task task = new Task();

        Status status = statusRepository.findById(UUID.fromString(taskDto.getStatus())).get();

        task.setTitle(taskDto.getTitle());
        task.setDescription(taskDto.getBody());
        task.setStatus(status);
        task.setDepartment(user.getDepartment());

        taskRepository.save(task);
    }

    public void answerTask(AnswerDto answerDto) {
        Task task = taskRepository.findById(UUID.fromString(answerDto.getTaskId())).get();

        Answer answer = answerRepository.findById(UUID.fromString(answerDto.getAnswerId())).get();

        task.setAnswer(answer);

        task.setEnd_time(LocalDateTime.now());

        task.setIs_finished(true);

        taskRepository.save(task);

    }

    public List<Task> getUnfinishedTasksByDepartment() {

        User user = userRepository.findById(userId).get();

        UUID departmentId = user.getDepartment().getId();

        return taskRepository.findByDepartmentId(departmentId);
    }

    public List<Task> getFinishedTasksByDepartment() {

        User user = userRepository.findById(userId).get();

        UUID departmentId = user.getDepartment().getId();

        return taskRepository.findByDepartmentIdFinishedTasks(departmentId);
    }

    public List<Task> getAllMonthlyTasks(Integer month) {
        List<Task> tasks = new ArrayList<>();

        return tasks;
    }

    public List<Task> getDailyTasks() {

        LocalDate localDate = LocalDate.from(LocalDateTime.now());

        return taskRepository.getAllDailyTasks(localDate);
    }

    public List<Task> getLastTask(int day) {

        LocalDate localDate = LocalDate.from(LocalDateTime.now());

        return taskRepository.getLastTasks(Date.valueOf(localDate.minusDays(day)));
    }

    public List<Task> getDailyFinishedTasks() {

        LocalDate localDate = LocalDate.from(LocalDateTime.now());

        return taskRepository.getDailyFinishedTasks(localDate);

    }

    public List<Task> getDailyUnFinishedTasks() {

        LocalDate localDate = LocalDate.from(LocalDateTime.now());

        return taskRepository.getDailyUnFinishedTasks(localDate);
    }

    public List<Task> getDailyCannotFinishTasks() {

        LocalDate localDate = LocalDate.from(LocalDateTime.now());

        return taskRepository.getDailyCannotFinishTasks(localDate);
    }

    public List<Task> getDailyFinishedTasks(int day) {

        LocalDate localDate = LocalDate.from(LocalDateTime.now());

        return taskRepository.getDailyFinishedTasks(localDate.minusDays(day));

    }

    public List<Task> getDailyUnFinishedTasks(int day) {

        LocalDate localDate = LocalDate.from(LocalDateTime.now());

        return taskRepository.getDailyUnFinishedTasks(localDate.minusDays(day));
    }

    public List<Task> getDailyCannotFinishTasks(int day) {

        LocalDate localDate = LocalDate.from(LocalDateTime.now());

        return taskRepository.getDailyCannotFinishTasks(localDate.minusDays(day));
    }
}

