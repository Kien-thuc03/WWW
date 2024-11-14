package edu.iuh.fit.labweek_06.backend.repositories;

import edu.iuh.fit.labweek_06.backend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
}