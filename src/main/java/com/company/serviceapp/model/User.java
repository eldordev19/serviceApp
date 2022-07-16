package com.company.serviceapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.PackagePrivate;

import javax.persistence.*;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "users")
@PackagePrivate
public class User {

    @Id
    @GeneratedValue
    UUID id;

    String firstName;

    String lastName;

    String username;

    String password;

    @ManyToOne
    Department department;
}
