package com.projectworkshop.workshop.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.projectworkshop.workshop.entities.Category;
import com.projectworkshop.workshop.entities.Order;
import com.projectworkshop.workshop.entities.OrderItem;
import com.projectworkshop.workshop.entities.Product;
import com.projectworkshop.workshop.entities.User;
import com.projectworkshop.workshop.entities.enums.OrderStatus;
import com.projectworkshop.workshop.repositories.CategoryRepository;
import com.projectworkshop.workshop.repositories.OrderItemRepository;
import com.projectworkshop.workshop.repositories.OrderRepository;
import com.projectworkshop.workshop.repositories.ProductRepository;
import com.projectworkshop.workshop.repositories.UserRepository;

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

		Category cat1 = new Category(null, "Eletronics");
		Category cat2 = new Category(null, "Books");
		Category cat3 = new Category(null, "Computers");
		
		Product p1 = new Product(null, "Arquitetura de Software", "Descricao: Este e um livro sobre arquitetura", 90.5, "");
		Product p2 = new Product(null, "Smart Tv", "HD FULL", 2190.0, "");
		Product p3 = new Product(null, "Inspirion 15", "Notebook Dell", 2300.0, "");
		Product p4 = new Product(null, "PC Gamer", "Computador voltado para jogos", 1200.0, "");
		Product p5 = new Product(null, "The Lord of the Rings", "Descricao: Este e um livro sobre o senhor dos aneis", 100.99, "");
		
		categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3));
		productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));
		
		p1.getSetCategories().add(cat2);
		p2.getSetCategories().add(cat1);
		p2.getSetCategories().add(cat3);
		p3.getSetCategories().add(cat3);
		p4.getSetCategories().add(cat3);
		p5.getSetCategories().add(cat2);
		
		productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));
		
		User user1 = new User(null, "Roberto Marinho", "Roberto@gmail.com", "99999999999", "12345");
		User user2 = new User(null, "Marina Lima", "Marina@gmail.com", "99999999999", "12345");
		

		Order order1 = new Order(null, Instant.parse("2024-06-20T19:01:07Z"), OrderStatus.PAID, user1);
		Order order2 = new Order(null, Instant.parse("2024-08-17T20:40:25Z"), OrderStatus.WAITING_PAYMENT, user2);
		Order order3 = new Order(null, Instant.parse("2024-03-19T18:37:29Z"), OrderStatus.PAID, user1);

		userRepository.saveAll(Arrays.asList(user1, user2));
		orderRepository.saveAll(Arrays.asList(order1, order2, order3));
		
		OrderItem oi1 = new OrderItem(order1, p1, 2, p1.getPrice());
		OrderItem oi2 = new OrderItem(order1, p3, 1, p3.getPrice());
		OrderItem oi3 = new OrderItem(order2, p3, 2, p3.getPrice());
		OrderItem oi4 = new OrderItem(order3, p5, 2, p5.getPrice());
		
		orderItemRepository.saveAll(Arrays.asList(oi1, oi2, oi3, oi4));

		
	}

}
