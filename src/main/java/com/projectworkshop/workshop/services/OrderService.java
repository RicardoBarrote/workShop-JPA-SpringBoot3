package com.projectworkshop.workshop.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projectworkshop.workshop.entities.Order;
import com.projectworkshop.workshop.repositories.OrderRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;
	
	
	public List<Order> findAll() {
		return orderRepository.findAll();
	}
	
	
	public Order findById (Long id) {
		Optional<Order> opt = orderRepository.findById(id);
		return opt.get();
	}
}
