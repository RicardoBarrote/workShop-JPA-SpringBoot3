package com.projectworkshop.workshop.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projectworkshop.workshop.entities.Category;
import com.projectworkshop.workshop.repositories.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	public List<Category> findAll() {
		return categoryRepository.findAll();
	}
	
	public Category findById (Long id) {
		Optional<Category> opt = categoryRepository.findById(id);
		return opt.get();
	}
}
