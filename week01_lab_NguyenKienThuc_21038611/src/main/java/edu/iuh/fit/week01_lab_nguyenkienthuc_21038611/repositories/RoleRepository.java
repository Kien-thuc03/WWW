package edu.iuh.fit.week01_lab_nguyenkienthuc_21038611.repositories;

import edu.iuh.fit.week01_lab_nguyenkienthuc_21038611.entities.Role;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class RoleRepository {
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    // Constructor để khởi tạo EntityManager và EntityManagerFactory
    public RoleRepository() {
        entityManagerFactory = Persistence.createEntityManagerFactory("MariaDB");
        entityManager = entityManagerFactory.createEntityManager();
    }

    // Phương thức để lấy ra role theo roleId
    public Role getRoleById(String roleId) {
        return entityManager.find(Role.class, roleId);
    }
}
