package project.workshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import project.workshop.entities.Order;
import project.workshop.requestPayLoad.OrderItemRequestPayLoad;
import project.workshop.requestPayLoad.OrderRequestPayLoad;
import project.workshop.services.OrderService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {

    @Autowired
    OrderService orderService;

    @GetMapping
    ResponseEntity<List<Order>> getAllOrder() {
        List<Order> listOrder = orderService.findAll();
        return ResponseEntity.ok().body(listOrder);
    }

    @GetMapping(value = "/{id}")
    ResponseEntity<Order> findById(@PathVariable Integer id) {
        Order order = orderService.findById(id);
        return ResponseEntity.ok().body(order);
    }

    @PostMapping
    public ResponseEntity<Order> createdOrder(@RequestBody OrderRequestPayLoad payLoad) {
        Order order = orderService.createdOrder(payLoad);

        URI newUri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(order.getId())
                .toUri();

        return ResponseEntity.created(newUri).body(order);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Order> deleteOrder(@PathVariable Integer id) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/{id}/products")
    public ResponseEntity<Order> addProductToOrder(@PathVariable Integer id, @RequestBody OrderItemRequestPayLoad payLoad) {
        Order order = orderService.addProductToOrder(id, payLoad.id(), payLoad.quantity());
        return ResponseEntity.ok().body(order);
    }
}
