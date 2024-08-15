package project.workshop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.workshop.entities.Order;
import project.workshop.entities.OrderItem;
import project.workshop.entities.Product;
import project.workshop.entities.User;
import project.workshop.repositories.OrderItemRepository;
import project.workshop.repositories.OrderRepository;
import project.workshop.repositories.ProductRepository;
import project.workshop.repositories.UserRepository;
import project.workshop.requestPayLoad.OrderRequestPayLoad;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

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
        Optional<Order> order = orderRepository.findById(id);
        return order.get();
    }

    public Order createdOrder(OrderRequestPayLoad payLoad) {
        User user = userRepository.findById(payLoad.user().getId())
                .orElseThrow(() -> new IllegalStateException(""));

        Order order = new Order(payLoad, user);
        orderRepository.save(order);
        return order;
    }

    public void deleteOrder(Integer id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException(""));
        orderRepository.delete(order);
    }

    public Order addProductToOrder(Integer orderId, Integer productId, Integer quantity) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        Optional<Product> optionalProduct = productRepository.findById(productId);

        if (optionalOrder.isPresent() && optionalProduct.isPresent()) {
            Order order = optionalOrder.get();
            Product product = optionalProduct.get();

            OrderItem orderItem = new OrderItem(order, product, quantity, product.getPrice());
            order.getItems().add(orderItem);
            orderItemRepository.save(orderItem);

            return order;
        }
        throw new NoSuchElementException("");
    }
}
