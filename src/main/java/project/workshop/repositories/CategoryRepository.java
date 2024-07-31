package project.workshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import project.workshop.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
