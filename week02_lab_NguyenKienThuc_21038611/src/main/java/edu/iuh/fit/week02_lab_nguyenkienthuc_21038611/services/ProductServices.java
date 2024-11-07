package edu.iuh.fit.week02_lab_nguyenkienthuc_21038611.services;


import edu.iuh.fit.week02_lab_nguyenkienthuc_21038611.models.Product;
import edu.iuh.fit.week02_lab_nguyenkienthuc_21038611.repositories.ProductRepository;

import java.util.Optional;

public class ProductServices {
    private final ProductRepository repository;

    public ProductServices() {
        repository = new ProductRepository();
    }

    public Optional<Product> findById(long id) {
        return repository.findbyId(id);
    }

}
