package com.projectworkshop.workshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projectworkshop.workshop.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
