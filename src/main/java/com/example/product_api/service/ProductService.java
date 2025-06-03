package com.example.product_api.service;

import com.example.product_api.entity.Product;
import com.example.product_api.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository repo;

    public ProductService(ProductRepository repo) {
        this.repo = repo;
    }

    public List<Product> getAll() {
        return repo.findAll();
    }

    public Optional<Product> getById(int id) {
        return repo.findById(id);
    }

    public Product create(Product product) {
        product.setCreatedAt(new Date());
        return repo.save(product);
    }

    public Product update(int id, Product updatedProduct) {
        return repo.findById(id).map(p -> {
            p.setName(updatedProduct.getName());
            p.setDescription(updatedProduct.getDescription());
            p.setPrice(updatedProduct.getPrice());
            return repo.save(p);
        }).orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public void delete(int id) {
        repo.deleteById(id);
    }

    public List<Product> searchByName(String name) {
        return repo.findByNameContainingIgnoreCase(name);
    }

    public List<Product> searchByPriceRange(BigDecimal min, BigDecimal max) {
        return repo.findByPriceBetween(min, max);
    }
}

