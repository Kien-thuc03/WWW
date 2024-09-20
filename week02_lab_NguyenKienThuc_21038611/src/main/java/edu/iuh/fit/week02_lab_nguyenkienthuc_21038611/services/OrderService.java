package edu.iuh.fit.week02_lab_nguyenkienthuc_21038611.services;

import edu.iuh.fit.week02_lab_nguyenkienthuc_21038611.models.Order;
import edu.iuh.fit.week02_lab_nguyenkienthuc_21038611.repositories.OrderRepository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class OrderService {
    private final OrderRepository orderRepository = new OrderRepository();

    public void insertOrder(Order order) {
        orderRepository.insertOrder(order);
    }

    public boolean updateOrder(Order order) {
        return orderRepository.updateOrder(order);
    }

    public boolean deleteOrder(long id) {
        return orderRepository.deleteOrder(id);
    }

    public Optional<Order> findById(long id) {
        return orderRepository.findById(id);
    }

    public List<Order> getAll() {
        return orderRepository.getAllOrders();
    }

    public List<Order> getOrdersByDate(String date) {
        return orderRepository.getOrdersByDate(date);
    }

    public List<Order> getOrdersByDateRange(String startDate, String endDate) {
        return orderRepository.getOrdersByDateRange(startDate, endDate);
    }

    public List<Order> getOrdersByEmployee(long empId, String startDate, String endDate) {
        return orderRepository.getOrdersByEmployee(empId, startDate, endDate);
    }
}
