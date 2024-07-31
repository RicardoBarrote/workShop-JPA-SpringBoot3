package project.workshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.workshop.entities.Category;
import project.workshop.services.CategoryService;

import java.util.List;

@RestController
@RequestMapping(value = "/categories")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping
    ResponseEntity<List<Category>> findAll() {
        List<Category> categoryList = categoryService.findAll();
        return ResponseEntity.ok().body(categoryList);
    }

    @GetMapping(value = "/{id}")
    ResponseEntity<Category> findById(@PathVariable Integer id) {
        Category category = categoryService.findById(id);
        return ResponseEntity.ok().body(category);
    }

}
