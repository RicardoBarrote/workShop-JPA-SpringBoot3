package project.workshop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.workshop.entities.Category;
import project.workshop.entities.Product;
import project.workshop.repositories.CategoryRepository;
import project.workshop.repositories.ProductRepository;
import project.workshop.requestPayLoad.ProductRequestPayload;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

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
        Optional<Product> productId = productRepository.findById(id);
        return productId.get();
    }

    public Product createdProduct(ProductRequestPayload payload) {
        Product product = new Product(payload);
        productRepository.save(product);
        return product;
    }

    public Product addCategory(Integer idProduct, Integer idCategory) {
        Optional<Product> optionalProduct = productRepository.findById(idProduct);
        Optional<Category> optionalCategory = categoryRepository.findById(idCategory);

        if (optionalProduct.isPresent() && optionalCategory.isPresent()) {
            Product product = optionalProduct.get();
            Category category = optionalCategory.get();

            product.getCategories().add(category);
            productRepository.save(product);
            return product;
        }
        throw new NoSuchElementException("");
    }
}
