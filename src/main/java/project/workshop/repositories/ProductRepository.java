package project.workshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import project.workshop.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
