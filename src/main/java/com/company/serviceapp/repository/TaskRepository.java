package com.company.serviceapp.repository;

import com.company.serviceapp.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface TaskRepository extends JpaRepository<Task, UUID> {

    @Query(nativeQuery = true, value = "select * from tasks where department_id = :id and is_finished = false")
    List<Task> findByDepartmentId(UUID id);
    @Query(nativeQuery = true, value = "select * from tasks where department_id = :id and is_finished = true")
    List<Task> findByDepartmentIdFinishedTasks(UUID id);

    @Query(nativeQuery = true, value = "select * from tasks where (select  TO_DATE((select\n" +
            "                     concat(EXTRACT(day FROM start_time), '/',\n" +
            "                            EXTRACT(MONTH FROM start_time), '/',\n" +
            "                            EXTRACT(year FROM start_time)) from tasks), 'DD-MM-YYYY')) = :day")
    List<Task> getLastTasks(Date day);

    @Query(nativeQuery = true, value = "select * from tasks where is_finished = true and (select  TO_DATE((select\n" +
            "                                 concat(EXTRACT(day FROM start_time), '/',\n" +
            "                                        EXTRACT(MONTH FROM start_time), '/',\n" +
            "                                       EXTRACT(year FROM start_time)) from tasks), 'DD-MM-YYYY')) = :localDate")
    List<Task> getDailyFinishedTasks(LocalDate localDate);

    @Query(nativeQuery = true, value = "select * from tasks where is_finished = false and (select  TO_DATE((select\n" +
            "                                 concat(EXTRACT(day FROM start_time), '/',\n" +
            "                                        EXTRACT(MONTH FROM start_time), '/',\n" +
            "                                       EXTRACT(year FROM start_time)) from tasks), 'DD-MM-YYYY')) = :localDate")
    List<Task> getDailyUnFinishedTasks(LocalDate localDate);

    @Query(nativeQuery = true, value = "select * from tasks join answers a on a.id = tasks.answer_id\n" +
            "         where\n" +
            "             a.id = '204b65cc-4c99-4d58-aed3-cb58e926e534' and\n" +
            "             is_finished = false and (select  TO_DATE((select\n" +
            "                                           concat(EXTRACT(day FROM start_time), '/',\n" +
            "                                                   EXTRACT(MONTH FROM start_time), '/',\n" +
            "                                                EXTRACT(year FROM start_time)) from tasks), 'DD-MM-YYYY')) = :localDate")
    List<Task> getDailyCannotFinishTasks(LocalDate localDate);
    @Query(nativeQuery = true, value = "select * from tasks where (select  TO_DATE((select\n" +
            "                                 concat(EXTRACT(day FROM start_time), '/',\n" +
            "                                        EXTRACT(MONTH FROM start_time), '/',\n" +
            "                                       EXTRACT(year FROM start_time)) from tasks), 'DD-MM-YYYY')) = :localDate")
    List<Task> getAllDailyTasks(LocalDate localDate);
}
