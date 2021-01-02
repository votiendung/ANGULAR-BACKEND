package com.example.ketnoibackend.service.product;

import com.example.ketnoibackend.model.Product;
import com.example.ketnoibackend.repo.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class ProductServiceImpl implements IProductService{
    @Autowired
    private IProductRepository iProductRepository;

    @Override
    public Iterable<Product> getAll() {
        return iProductRepository.findAll();
    }

    @Override
    public Product save(Product product) {
        return iProductRepository.save(product);
    }

    @Override
    public Optional<Product> findById(Long id) {
        return iProductRepository.findById(id);
    }

    @Override
    public void remove(Long id) {
        iProductRepository.deleteById(id);
    }
}
