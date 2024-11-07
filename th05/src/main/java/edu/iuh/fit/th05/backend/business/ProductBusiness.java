package edu.iuh.fit.th05.backend.business;

import edu.iuh.fit.th05.backend.repositories.ProductRepository;

public class ProductBusiness {
    private final ProductRepository productRepository;

    public ProductBusiness(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
}
