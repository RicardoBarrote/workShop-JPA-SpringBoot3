package com.projectworkshop.workshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projectworkshop.workshop.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
