package com.company.serviceapp.command;

import com.company.serviceapp.model.*;
import com.company.serviceapp.repository.*;
import org.apache.tomcat.util.buf.Asn1Parser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    StatusRepository statusRepository;

    @Autowired
    AnswerRepository answerRepository;

    @Autowired
    TaskRepository taskRepository;

    @Override
    public void run(String... args) throws Exception {

//        Department department = new Department("Shartnomalar");
//
//        departmentRepository.save(department);
//
//        userRepository.save(new User("Alex", "Telles", "alex", "1", department));


//        List<Answer> answers = new ArrayList<>(Arrays.asList(
//                new Answer(null, "Bajarildi"),
//                new Answer(null, "Bizni qo'limizdan kelmaydi"),
//                new Answer(null, "Bajarib bo'lmaydi")
//        ));
//
//        answerRepository.saveAll(answers);

//        Status shoshilinch = statusRepository.save(new Status(null, "Shoshilinch"));
//
//        Department department = new Department("Department2");
//
//        departmentRepository.save(department);
//
//        List<Task> tasks = new ArrayList<>(Arrays.asList(
//                new Task(null, "Task5", "Body5", department, shoshilinch, LocalTime.now(), LocalDate.now(), null, false, null),
//                new Task(null, "Task6", "Body6", department, shoshilinch, LocalTime.now(), LocalDate.now(), null, false, null),
//                new Task(null, "Task7", "Body7", department, shoshilinch, LocalTime.now(), LocalDate.now(), null, false, null),
//                new Task(null, "Task8", "Body8", department, shoshilinch, LocalTime.now(), LocalDate.now(), null, false, null)
//        ));
//
//
//        taskRepository.saveAll(tasks);
    }
}
