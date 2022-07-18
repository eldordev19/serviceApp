package com.company.serviceapp.repository;

import com.company.serviceapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}
