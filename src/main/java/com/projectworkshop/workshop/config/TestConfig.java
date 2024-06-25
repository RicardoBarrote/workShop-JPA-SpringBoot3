package com.projectworkshop.workshop.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.projectworkshop.workshop.entities.Order;
import com.projectworkshop.workshop.entities.User;
import com.projectworkshop.workshop.repositories.OrderRepository;
import com.projectworkshop.workshop.repositories.UserRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Override
	public void run(String... args) throws Exception {

		User user1 = new User(null, "Roberto Marinho", "Roberto@gmail.com", "99999999999", "12345");
		User user2 = new User(null, "Marina Lima", "Marina@gmail.com", "99999999999", "12345");

		Order order1 = new Order(null, Instant.parse("2024-06-20T19:01:07Z"), user1);
		Order order2 = new Order(null, Instant.parse("2024-08-17T20:40:25Z"), user2);
		Order order3 = new Order(null, Instant.parse("2024-03-19T18:37:29Z"), user1);

		userRepository.saveAll(Arrays.asList(user1, user2));
		orderRepository.saveAll(Arrays.asList(order1, order2, order3));
	}

}
