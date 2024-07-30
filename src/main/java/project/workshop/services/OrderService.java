package project.workshop.services;

import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.workshop.entities.Order;
import project.workshop.repositories.OrderReposiroty;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    OrderReposiroty orderReposiroty;

    public List<Order> findAll(){
        return orderReposiroty.findAll();
    }

    public Order findById(Integer id){
        Optional<Order> order = orderReposiroty.findById(id);
        return order.get();
    }
}
