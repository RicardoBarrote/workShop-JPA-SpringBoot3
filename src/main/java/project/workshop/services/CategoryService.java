package project.workshop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.workshop.entities.Category;
import project.workshop.repositories.CategoryRepository;
import project.workshop.requestPayLoad.CategoryRequestPayLoad;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Category findById(Integer id) {
        Optional<Category> userId = categoryRepository.findById(id);
        return userId.get();
    }

    public Category createdCategory(CategoryRequestPayLoad payLoad) {
        Category category = new Category(payLoad);
        categoryRepository.save(category);
        return category;
    }

    public void deleteCategory(Integer id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new NoSuchElementException(""));
        categoryRepository.delete(category);
    }

    public Category updateCategory(Integer id, CategoryRequestPayLoad payLoad) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);

        if (optionalCategory.isPresent()) {
            Category category = optionalCategory.get();

            category.setName(payLoad.name());
            categoryRepository.save(category);
            return category;
        }

        throw new NoSuchElementException("");
    }
}
