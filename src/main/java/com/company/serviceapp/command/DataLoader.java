package com.company.serviceapp.command;

import com.company.serviceapp.model.Answer;
import com.company.serviceapp.model.Department;
import com.company.serviceapp.model.Status;
import com.company.serviceapp.model.User;
import com.company.serviceapp.repository.AnswerRepository;
import com.company.serviceapp.repository.DepartmentRepository;
import com.company.serviceapp.repository.StatusRepository;
import com.company.serviceapp.repository.UserRepository;
import org.apache.tomcat.util.buf.Asn1Parser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

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

    @Override
    public void run(String... args) throws Exception {

//        Department department = new Department("Shartnomalar");
//
//        departmentRepository.save(department);
//
//        userRepository.save(new User("Alex", "Telles", "alex", "1", department));

       // statusRepository.save(new Status(null, "Shoshilinch"));

        List<Answer> answers = new ArrayList<>(Arrays.asList(
                new Answer(null, "Bajarildi"),
                new Answer(null, "Bizni qo'limizdan kelmaydi"),
                new Answer(null, "Bajarib bo'lmaydi")
        ));

        answerRepository.saveAll(answers);

    }
}
