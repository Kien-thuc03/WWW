package edu.iuh.fit.week01_lab_nguyenkienthuc_21038611.repositories;

import edu.iuh.fit.week01_lab_nguyenkienthuc_21038611.entities.Log;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.Instant;

public class LogRepository {
    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("MariaDB");

    public Long logLogin(String accountId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Log log = new Log();
        Long id = entityManager.createNamedQuery("Log.count", Long.class)
                .getSingleResult() + 1;
        log.setId((id));
        log.setAccountId(accountId);
        log.setLoginTime(Instant.now());
        log.setLogoutTime(Instant.now());
        log.setNotes("Login successfully");
        entityManager.getTransaction().begin();
        entityManager.persist(log);
        entityManager.getTransaction().commit();
        entityManager.close();
        return id;
    }

    public void logLogout(Long logId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Log log = entityManager.createNamedQuery("Log.findById", Log.class)
                .setParameter("id", logId)
                .getSingleResult();
        log.setLogoutTime(Instant.now());
        entityManager.getTransaction().begin();
        entityManager.merge(log);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
