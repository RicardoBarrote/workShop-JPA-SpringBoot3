package project.workshop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.workshop.entities.Category;
import project.workshop.entities.Product;
import project.workshop.repositories.CategoryRepository;
import project.workshop.repositories.ProductRepository;
import project.workshop.requestPayLoad.ProductRequestPayload;
import project.workshop.services.exceptions.ResourcerNotFound;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findById(Integer id) {
        return productRepository.findById(id).orElseThrow(() -> new ResourcerNotFound(id));
    }

    public Product createdProduct(ProductRequestPayload payload) {
        Product product = new Product(payload);
        return productRepository.save(product);
    }

    public Product addCategory(Integer idProduct, Integer idCategory) {
        Product product = productRepository.findById(idProduct).orElseThrow(() -> new ResourcerNotFound(idProduct));
        Category category = categoryRepository.findById(idCategory).orElseThrow(() -> new ResourcerNotFound(idCategory));

        product.getCategories().add(category);
        return productRepository.save(product);
    }

    public void deleteCategoryToProduct(Integer idProduct, Integer idCategory) {
        Product product = productRepository.findById(idProduct).orElseThrow(() -> new ResourcerNotFound(idProduct));
        Category category = categoryRepository.findById(idCategory).orElseThrow(() -> new ResourcerNotFound(idCategory));

        boolean removed = product.getCategories()
                .stream()
                .filter(c -> c.getId().equals(idCategory))
                .findFirst()
                .map(c -> product.getCategories().remove(c))
                .orElseThrow(() -> new ResourcerNotFound(idCategory));

        if (removed){
            productRepository.save(product);
        }
    }


}
