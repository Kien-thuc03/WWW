package edu.iuh.fit.week02_lab_nguyenkienthuc_21038611.repositories;

import edu.iuh.fit.week02_lab_nguyenkienthuc_21038611.models.Order;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.Optional;

public class OrderRepository {

    private EntityManager em;
    private EntityTransaction trans;

    public OrderRepository() {
        em = Persistence.createEntityManagerFactory("lab_week_2").createEntityManager();
        trans = em.getTransaction();
    }

    public void insertOrder(Order order) {
        try {
            trans.begin();
            em.persist(order);
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
            e.printStackTrace();
        }
    }

    public boolean updateOrder(Order order) {
        try {
            trans.begin();
            em.merge(order);
            trans.commit();
            return true;
        } catch (Exception e) {
            trans.rollback();
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteOrder(long id) {
        Optional<Order> order = findById(id);
        if (order.isPresent()) {
            try {
                trans.begin();
                em.remove(order.get());
                trans.commit();
                return true;
            } catch (Exception e) {
                trans.rollback();
                e.printStackTrace();
            }
        }
        return false;
    }

    public Optional<Order> findById(long id) {
        return Optional.ofNullable(em.find(Order.class, id));
    }

    public List<Order> getAllOrders() {
        return em.createQuery("SELECT o FROM Order o", Order.class).getResultList();
    }

    public List<Order> getOrdersByDate(String date) {
        TypedQuery<Order> query = em.createQuery("SELECT o FROM Order o WHERE o.orderDate = :date", Order.class);
        query.setParameter("date", java.sql.Date.valueOf(date));
        return query.getResultList();
    }

    public List<Order> getOrdersByDateRange(String startDate, String endDate) {
        TypedQuery<Order> query = em.createQuery("SELECT o FROM Order o WHERE o.orderDate BETWEEN :start AND :end", Order.class);
        query.setParameter("start", java.sql.Date.valueOf(startDate));
        query.setParameter("end", java.sql.Date.valueOf(endDate));
        return query.getResultList();
    }

    public List<Order> getOrdersByEmployee(long empId, String startDate, String endDate) {
        TypedQuery<Order> query = em.createQuery("SELECT o FROM Order o WHERE o.emp.id = :empId AND o.orderDate BETWEEN :start AND :end", Order.class);
        query.setParameter("empId", empId);
        query.setParameter("start", java.sql.Date.valueOf(startDate));
        query.setParameter("end", java.sql.Date.valueOf(endDate));
        return query.getResultList();
    }
}
