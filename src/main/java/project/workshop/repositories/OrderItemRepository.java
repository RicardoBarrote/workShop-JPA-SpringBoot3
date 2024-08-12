package project.workshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import project.workshop.entities.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
}
