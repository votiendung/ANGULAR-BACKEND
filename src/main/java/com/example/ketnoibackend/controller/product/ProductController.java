package com.example.ketnoibackend.controller.product;

import com.example.ketnoibackend.model.Product;
import com.example.ketnoibackend.service.product.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private IProductService iProductService;

    @GetMapping
    public ResponseEntity<Iterable<Product>> getAll() {
        return new ResponseEntity<>(iProductService.getAll(), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Product> createNewProduct(@RequestBody Product product) {
        return new ResponseEntity<>(iProductService.save(product), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Optional<Product> productOptional = iProductService.findById(id);
        return productOptional.map(product -> new ResponseEntity<>(product,HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        Optional<Product> productOptional = iProductService.findById(id);
        return productOptional.map(product1 -> {
            product.setId(product1.getId());
            if (product.getName().equalsIgnoreCase("")){
                product.setName(product1.getName());
            }
            return new ResponseEntity<>(iProductService.save(product),HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable Long id) {
        Optional<Product> productOptional = iProductService.findById(id);
        return productOptional.map(product -> {
            iProductService.remove(id);
            return new ResponseEntity<Product>(HttpStatus.NO_CONTENT);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}
