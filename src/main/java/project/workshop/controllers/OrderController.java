package project.workshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.workshop.entities.Order;
import project.workshop.services.OrderService;

import java.util.List;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {

    @Autowired
    OrderService orderService;

    @GetMapping
    ResponseEntity<List<Order>> findAll() {
        List<Order> listOrder = orderService.findAll();
        return ResponseEntity.ok().body(listOrder);
    }

    @GetMapping(value = "/{id}")
    ResponseEntity<Order> findById(@PathVariable Integer id) {
        Order order = orderService.findById(id);
        return ResponseEntity.ok().body(order);
    }
}
