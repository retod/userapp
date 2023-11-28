package org.assignment.westernacher.userapp.repositories;

import org.assignment.westernacher.userapp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}
