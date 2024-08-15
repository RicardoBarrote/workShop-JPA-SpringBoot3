package project.workshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import project.workshop.entities.Product;
import project.workshop.requestPayLoad.CategoryRequestPayLoad;
import project.workshop.requestPayLoad.ProductRequestPayload;
import project.workshop.services.ProductService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping
    ResponseEntity<List<Product>> findAll() {
        List<Product> productList = productService.findAll();
        return ResponseEntity.ok().body(productList);
    }

    @GetMapping(value = "/{id}")
    ResponseEntity<Product> findById(@PathVariable Integer id) {
        Product product = productService.findById(id);
        return ResponseEntity.ok().body(product);
    }

    @PostMapping
    public ResponseEntity<Product> createdProduct(@RequestBody ProductRequestPayload payload) {
        Product product = productService.createdProduct(payload);

        URI newURI = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(product.getId())
                .toUri();

        return ResponseEntity.created(newURI).body(product);
    }

    @PostMapping(value = "/{id}")
    public ResponseEntity<Product> addCategory(@PathVariable Integer id, @RequestBody CategoryRequestPayLoad payLoad) {
        Product product = productService.addCategory(id, payLoad.id());
        return ResponseEntity.ok().body(product);
    }
}
