package edu.iuh.fit.week02_lab_nguyenkienthuc_21038611.controler;

import edu.iuh.fit.week02_lab_nguyenkienthuc_21038611.enums.EmployeeStatus;
import edu.iuh.fit.week02_lab_nguyenkienthuc_21038611.models.Employee;
import edu.iuh.fit.week02_lab_nguyenkienthuc_21038611.services.EmployeeServices;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/ControlServlet")
public class ControlServlet extends HttpServlet {
    private EmployeeServices employeeServices = new EmployeeServices();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action != null) {
            switch (action) {
                case "saveAdd":
                    saveEmployee(request, response, false);
                    break;
                case "saveEdit":
                    saveEmployee(request, response, true);
                    break;
                default:
                    response.sendError(HttpServletResponse.SC_NOT_FOUND);
                    break;
            }
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("delete".equals(action)) {
            deleteEmployee(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }


    private void saveEmployee(HttpServletRequest request, HttpServletResponse response, boolean isEdit) throws IOException {
        Long id = isEdit ? Long.parseLong(request.getParameter("id")) : null;
        String fullName = request.getParameter("fullName");
        LocalDate dob = LocalDate.parse(request.getParameter("dob"));
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        EmployeeStatus status = EmployeeStatus.valueOf(request.getParameter("status"));

        Employee employee = new Employee(id, fullName, dob, email, phone, address, status);

        if (isEdit) {
            employeeServices.updateEmployee(employee);
        } else {
            employeeServices.insertEmployee(employee);
        }

        response.sendRedirect("Employees.jsp");
    }

    private void deleteEmployee(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        employeeServices.updateStatus(id, EmployeeStatus.TERMINATED);
        response.sendRedirect("Employees.jsp");
    }
}
