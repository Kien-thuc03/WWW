package edu.iuh.fit.week02_lab_nguyenkienthuc_21038611.services;


import edu.iuh.fit.week02_lab_nguyenkienthuc_21038611.enums.EmployeeStatus;
import edu.iuh.fit.week02_lab_nguyenkienthuc_21038611.models.Employee;
import edu.iuh.fit.week02_lab_nguyenkienthuc_21038611.models.Order;
import edu.iuh.fit.week02_lab_nguyenkienthuc_21038611.repositories.EmployeeRepository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public class EmployeeServices {
    private final EmployeeRepository repository;

    public EmployeeServices() {
        repository = new EmployeeRepository();
    }

    public boolean updateStatus(long id) {
        Optional<Employee> op = findById(id);
        if (op.isPresent()) {
            Employee employee = op.get();
            repository.setStatus(employee, EmployeeStatus.ACTIVE);
            return true;
        }
        return false;
    }

    public boolean restEmployee(long id) {
        Optional<Employee> op = findById(id);
        if (op.isPresent()) {
            Employee employee = op.get();
            repository.setStatus(employee, EmployeeStatus.IN_ACTIVE);
            return true;
        }
        return false;
    }
    public List<Employee> getAll() {
        return repository.getAllEmp();
    }
    public void insertEmployee(Employee employee) {
        repository.insertEmp(employee);
    }

    public boolean updateEmployee(Employee employee) {
        return repository.update(employee);
    }

    public boolean deleteEmployee(long id) {
        Optional<Employee> op = findById(id);
        if (op.isPresent()) {
            Employee employee = op.get();
            repository.setStatus(employee, EmployeeStatus.TERMINATED);
            return true;
        }
        return false;
    }
    public Optional<Employee> findById(long id) {
        return repository.findbyId(id);
    }

    public void updateStatus(Long id, EmployeeStatus status) {
        repository.updateStatus(id, status);
    }

    public Employee getEmployeeById(long id) {
        return repository.getEmployeeById(id);
    }
}

