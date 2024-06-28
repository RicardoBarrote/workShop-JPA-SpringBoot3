package com.projectworkshop.workshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projectworkshop.workshop.entities.OrderItem;
import com.projectworkshop.workshop.entities.pk.OrderItemPK;

public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPK>{

}
