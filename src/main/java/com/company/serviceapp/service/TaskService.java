package com.company.serviceapp.service;

import com.company.serviceapp.dto.TaskDto;
import com.company.serviceapp.model.Status;
import com.company.serviceapp.model.Task;
import com.company.serviceapp.model.User;
import com.company.serviceapp.repository.DepartmentRepository;
import com.company.serviceapp.repository.StatusRepository;
import com.company.serviceapp.repository.TaskRepository;
import com.company.serviceapp.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    private final UUID userId = UUID.fromString("ef7db7f3-d0de-4e62-a78c-52ef969ec81b");

    public void sendTask(TaskDto taskDto) {

        User user = userRepository.findById(userId).get();

        Task task = new Task();

        Status status = statusRepository.findById(UUID.fromString(taskDto.getStatus())).get();

        task.setTitle(taskDto.getTitle());
        task.setBody(taskDto.getBody());
        task.setStatus(status);
        task.setDepartment(user.getDepartment());

        taskRepository.save(task);
    }
}

