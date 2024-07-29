package project.workshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import project.workshop.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {
}
