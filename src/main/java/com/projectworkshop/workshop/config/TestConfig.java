package com.projectworkshop.workshop.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.projectworkshop.workshop.entities.Category;
import com.projectworkshop.workshop.entities.Order;
import com.projectworkshop.workshop.entities.User;
import com.projectworkshop.workshop.entities.enums.OrderStatus;
import com.projectworkshop.workshop.repositories.CategoryRepository;
import com.projectworkshop.workshop.repositories.OrderRepository;
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

	@Override
	public void run(String... args) throws Exception {

		Category cat1 = new Category(null, "Eletronics");
		Category cat2 = new Category(null, "Books");
		Category cat3 = new Category(null, "Computers");
		
		categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3));
		
		User user1 = new User(null, "Roberto Marinho", "Roberto@gmail.com", "99999999999", "12345");
		User user2 = new User(null, "Marina Lima", "Marina@gmail.com", "99999999999", "12345");
		
		userRepository.saveAll(Arrays.asList(user1, user2));

		Order order1 = new Order(null, Instant.parse("2024-06-20T19:01:07Z"), OrderStatus.PAID, user1);
		Order order2 = new Order(null, Instant.parse("2024-08-17T20:40:25Z"), OrderStatus.WAITING_PAYMENT, user2);
		Order order3 = new Order(null, Instant.parse("2024-03-19T18:37:29Z"), OrderStatus.PAID, user1);

		
		orderRepository.saveAll(Arrays.asList(order1, order2, order3));
	}

}
