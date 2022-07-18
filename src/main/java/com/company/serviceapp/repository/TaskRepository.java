package com.company.serviceapp.repository;

import com.company.serviceapp.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.UUID;

public interface TaskRepository extends JpaRepository<Task, UUID> {

    @Query(nativeQuery = true, value = "select * from tasks where department_id = :id and is_finished = false")
    List<Task> findByDepartmentId(UUID id);
    @Query(nativeQuery = true, value = "select * from tasks where department_id = :id and is_finished = true")
    List<Task> findByDepartmentIdFinishedTasks(UUID id);
}
