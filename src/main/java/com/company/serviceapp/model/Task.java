package com.company.serviceapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.PackagePrivate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "users")
@PackagePrivate
public class Task {

    @Id
    @GeneratedValue
    UUID id;

    String title;

    String body;

    @ManyToOne
    User user;

    @ManyToOne
    Answer answer;

    @ManyToOne
    Status status;

    Timestamp start_time;

    Timestamp end_time;

}
