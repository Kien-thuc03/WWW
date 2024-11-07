package edu.iuh.fit.th05.backend.repositories;

import edu.iuh.fit.th05.backend.entities.Product;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

import java.util.Optional;

public class ProductRepository {
    @Inject
    private EntityManager em;


    public ProductRepository() {
        em = Persistence
                .createEntityManagerFactory("th05")
                .createEntityManager();
    }

    public Optional<Product> findAll() {
        return em.createNamedQuery("Product.findAll", Product.class).getResultList().stream().findFirst();
    }
}
