package edu.iuh.fit.week02_lab_nguyenkienthuc_21038611.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import edu.iuh.fit.week02_lab_nguyenkienthuc_21038611.enums.EmployeeStatus;
import edu.iuh.fit.week02_lab_nguyenkienthuc_21038611.models.Employee;

import java.util.List;
import java.util.Optional;

public class EmployeeRepository {
    private EntityManager em;
    private EntityTransaction trans;

    private final Logger logger =
            LoggerFactory.getLogger(this.getClass().getName());

    public EmployeeRepository() {
        em = Persistence
                .createEntityManagerFactory("lab_week_2")
                .createEntityManager();
        trans = em.getTransaction();
    }

    public void insertEmp(Employee employee) {
        try {
            em.getTransaction().begin();
            em.persist(employee);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            logger.error("Error inserting employee: " + e.getMessage());
            throw e;
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }


    public void setStatus(Employee employee, EmployeeStatus status) {
        employee.setStatus(status);
    }

    public boolean update(Employee employee) {
        try {
            trans.begin();
            em.merge(employee);
            trans.commit();
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage());
            trans.rollback();
            return false;
        }
    }

    public void updateStatus(Long id, EmployeeStatus status) {
        TypedQuery<Employee> query = em.createNamedQuery("Employee.findById", Employee.class)
                .setParameter("id", id);
        Employee employee = query.getSingleResult();
        employee.setStatus(status);
        try {
            trans.begin();
            em.merge(employee);
            trans.commit();
        } catch (Exception e) {
            logger.error(e.getMessage());
            trans.rollback();
        }
    }

    public Optional<Employee> findbyId(long id) {
        TypedQuery<Employee> query = em.createQuery("select e from Employee e where e.id=:id", Employee.class);
        query.setParameter("id", id);
        Employee emp = query.getSingleResult();
        return emp == null ? Optional.empty() : Optional.of(emp);
    }

    public Employee getEmployeeById(long id) {
        return em.find(Employee.class, id);
    }

    public List<Employee> getAllEmp() {
        return em.createNamedQuery("Employee.findAll", Employee.class)
                .getResultList();
    }
    public List<Employee> getAllEmpByStatus() {
        return em.createNamedQuery("Employee.findByStatus", Employee.class)
                .setParameter("status", EmployeeStatus.ACTIVE)
                .getResultList();
    }
}

