package project.workshop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.workshop.entities.Category;
import project.workshop.repositories.CategoryRepository;
import project.workshop.requestPayLoad.CategoryRequestPayLoad;
import project.workshop.services.exceptions.PropertyNull;
import project.workshop.services.exceptions.ResourcerNotFound;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Category findById(Integer id) {
        return categoryRepository.findById(id).orElseThrow(() -> new ResourcerNotFound(id));
    }

    public Category createdCategory(CategoryRequestPayLoad payLoad) {
        Category category = new Category(payLoad);
        if (category.getName() == null){
            throw new PropertyNull("Null field");
        }
        return categoryRepository.save(category);
    }

    public void deleteCategory(Integer id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new NoSuchElementException(""));
        categoryRepository.delete(category);
    }

    public Category updateCategory(Integer id, CategoryRequestPayLoad payLoad) {
        return categoryRepository.findById(id)
                .map(category -> {
                    category.setName(payLoad.name());
                    return categoryRepository.save(category);
                })
                .orElseThrow(() -> new ResourcerNotFound(id));
    }
}
