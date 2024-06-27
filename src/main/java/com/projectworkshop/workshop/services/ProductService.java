package com.projectworkshop.workshop.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projectworkshop.workshop.entities.Product;
import com.projectworkshop.workshop.repositories.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	public List<Product> findAll() {
		return productRepository.findAll();
	}

	public Product findById(Long id) {
		Optional<Product> opt = productRepository.findById(id);
		return opt.get();
	}
}
