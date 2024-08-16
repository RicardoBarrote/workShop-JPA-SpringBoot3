package project.workshop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.workshop.entities.*;
import project.workshop.enums.OrderStatus;
import project.workshop.repositories.OrderItemRepository;
import project.workshop.repositories.OrderRepository;
import project.workshop.repositories.ProductRepository;
import project.workshop.repositories.UserRepository;
import project.workshop.requestPayLoad.OrderRequestPayLoad;
import project.workshop.requestPayLoad.PaymentRequestPayLoad;
import project.workshop.services.exceptions.ResourcerNotFound;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    OrderItemRepository orderItemRepository;

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public Order findById(Integer id) {
        return orderRepository.findById(id).orElseThrow(() -> new ResourcerNotFound(id));
    }

    public Order createdOrder(OrderRequestPayLoad payLoad) {
        User user = userRepository.findById(payLoad.user().getId()).orElseThrow(() -> new ResourcerNotFound(payLoad.user().getId()));
        Order order = new Order(payLoad, user);
        return orderRepository.save(order);
    }

    public void deleteOrder(Integer id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new ResourcerNotFound(id));
        orderRepository.delete(order);
    }

    public Order addProductToOrder(Integer orderId, Integer productId, Integer quantity) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new ResourcerNotFound(orderId));
        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourcerNotFound(productId));

        OrderItem orderItem = new OrderItem(order, product, quantity, product.getPrice());
        order.getItems().add(orderItem);
        orderItemRepository.save(orderItem);
        return order;
    }

    public void deleteProductToOrder(Integer orderId, Integer productId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new ResourcerNotFound(orderId));
        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourcerNotFound(productId));

        OrderItem orderItem = order.getItems()
                .stream()
                .filter(item -> item.getProduct().equals(product))
                .findFirst().orElseThrow(() -> new NoSuchElementException(""));

        order.getItems().remove(orderItem);
        orderItemRepository.delete(orderItem);
        orderRepository.save(order);
    }

    public Order paymentOrder(Integer id, PaymentRequestPayLoad payLoad) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new ResourcerNotFound(id));
        Payment payment = new Payment(payLoad, order);

        order.setOrderStatus(OrderStatus.PAID);
        order.setPayment(payment);
        return orderRepository.save(order);
    }
}

