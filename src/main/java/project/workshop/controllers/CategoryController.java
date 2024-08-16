package project.workshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import project.workshop.entities.Category;
import project.workshop.requestPayLoad.CategoryRequestPayLoad;
import project.workshop.services.CategoryService;

import java.net.URI;
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

    @PostMapping
    ResponseEntity<Category> createdCategory(@RequestBody CategoryRequestPayLoad payLoad) {
        Category category = categoryService.createdCategory(payLoad);

        URI newURI = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(category.getId())
                .toUri();

        return ResponseEntity.created(newURI).body(category);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Category> deleteCategory(@PathVariable Integer id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Integer id, @RequestBody CategoryRequestPayLoad payLoad) {
        Category category = categoryService.updateCategory(id, payLoad);
        return ResponseEntity.ok().body(category);
    }
}
