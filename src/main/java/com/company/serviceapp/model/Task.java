package com.company.serviceapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.PackagePrivate;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "tasks")
@PackagePrivate
public class Task {

    @Id
    @Type(type = "org.hibernate.type.PostgresUUIDType")
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    UUID id;

    String title;

    String body;

    @ManyToOne
    Department department;

    @ManyToOne
    Status status;

    @OrderBy
    @Column(nullable = false, updatable = false)
    LocalTime start_time;

    @OrderBy
    @Column(nullable = false, updatable = false)
    LocalDate date;

    LocalDateTime end_time;

    Boolean is_finished;

    @ManyToOne
    Answer answer;
}
