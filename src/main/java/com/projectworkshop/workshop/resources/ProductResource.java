package com.projectworkshop.workshop.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projectworkshop.workshop.entities.Product;
import com.projectworkshop.workshop.services.ProductService;

@RestController
@RequestMapping(value = "/products")
public class ProductResource {

	@Autowired
	private ProductService productService;
	
	@GetMapping
	public ResponseEntity<List<Product>> findAll() {
		List<Product> listProduct = productService.findAll();
		return ResponseEntity.ok().body(listProduct);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Product> findById(Long id) {
		Product product = productService.findById(id);
		return ResponseEntity.ok().body(product);
	}
}
