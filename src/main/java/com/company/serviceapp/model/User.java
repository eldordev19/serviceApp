package com.company.serviceapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.PackagePrivate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "users")
@PackagePrivate
public class User {

    @Id
    @Type(type = "org.hibernate.type.PostgresUUIDType")
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    UUID id;

    String firstName;

    String lastName;

    String username;

    String password;

    @ManyToOne
    Department department;

    public User(String firstName, String lastName, String username, String password, Department department) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.department = department;
    }
}
