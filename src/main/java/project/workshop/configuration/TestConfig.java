package project.workshop.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import project.workshop.entities.Category;
import project.workshop.entities.Order;
import project.workshop.entities.Product;
import project.workshop.entities.User;
import project.workshop.enums.OrderStatus;
import project.workshop.repositories.CategoryRepository;
import project.workshop.repositories.OrderRepository;
import project.workshop.repositories.ProductRepository;
import project.workshop.repositories.UserRepository;

import java.time.LocalDateTime;
import java.util.Arrays;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ProductRepository productRepository;

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

    }
}
