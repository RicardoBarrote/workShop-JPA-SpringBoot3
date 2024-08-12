package project.workshop.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import project.workshop.entities.*;
import project.workshop.enums.OrderStatus;
import project.workshop.repositories.*;

import java.time.LocalDateTime;
import java.util.Arrays;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Override
    public void run(String... args) throws Exception {

        User u1 = new User(null, "Ricardo Barrote", "ricardo@gmail.com", "159753", "81985748758");
        User u2 = new User(null, "Ademilton", "ademilton@gmail.com", "5858585", "81985421578");
        userRepository.saveAll(Arrays.asList(u1, u2));

        Order o1 = new Order(null, LocalDateTime.of(2024, 05, 15, 20, 50, 33), OrderStatus.PAID, u1);
        Order o2 = new Order(null, LocalDateTime.of(2024, 02, 10, 15, 20, 48), OrderStatus.WAITING_PAYMENT, u2);
        orderRepository.saveAll(Arrays.asList(o1, o2));

        Category c1 = new Category(null, "ELetronic");
        Category c2 = new Category(null, "Books");
        Category c3 = new Category(null, "SmarthPhone");
        categoryRepository.saveAll(Arrays.asList(c1, c2, c3));

        Product p1 = new Product(null, "Inspirion 15", "Notebook Dell", " ", 2500.0);
        Product p2 = new Product(null, "Dragons", "Book", " ", 150.0);
        Product p3 = new Product(null, "Iphone X", "Apple", " ", 2000.0);
        productRepository.saveAll(Arrays.asList(p1, p2, p3));

        p1.getCategories().add(c1);
        p2.getCategories().add(c2);
        p3.getCategories().add(c3);
        productRepository.saveAll(Arrays.asList(p1, p2, p3));

        OrderItem oi1 = new OrderItem(o1, p1, 2, p1.getPrice());
        OrderItem oi2 = new OrderItem(o1, p3, 1, p3.getPrice());
        OrderItem oi3 = new OrderItem(o2, p2, 4, p2.getPrice());
        orderItemRepository.saveAll(Arrays.asList(oi1, oi2, oi3));

        Payment pay1 = new Payment(null, LocalDateTime.of(2024, 05, 15, 20, 58, 16), o1);
        o1.setPayment(pay1);
        orderRepository.saveAll(Arrays.asList(o1));
    }
}
