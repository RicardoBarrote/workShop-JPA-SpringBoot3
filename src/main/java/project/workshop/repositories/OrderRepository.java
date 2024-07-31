package project.workshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import project.workshop.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
