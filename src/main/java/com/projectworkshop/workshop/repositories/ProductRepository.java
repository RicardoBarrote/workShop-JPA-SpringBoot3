package com.projectworkshop.workshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projectworkshop.workshop.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
