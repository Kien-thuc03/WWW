<%--
  Created by IntelliJ IDEA.
  User: nktng
  Date: 20/09/2024
  Time: 1:40 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="edu.iuh.fit.week02_lab_nguyenkienthuc_21038611.services.EmployeeServices" %>
<%@ page import="edu.iuh.fit.week02_lab_nguyenkienthuc_21038611.models.Employee" %>
<html>
<head>
  <title>Employee List</title>
  <script>
    function addEmployee() {
      window.location.href = 'AddAndEdit.jsp?action=add';
    }

    function editEmployee(id) {
      window.location.href = 'AddAndEdit.jsp?action=edit&id=' + id;
    }

    function deleteEmployee(id) {
        if (confirm('Are you sure you want to delete this employee?')) {
            window.location.href = 'ControlServlet?action=delete&id=' + id;
        }
    }
  </script>
</head>
<body>
<h1>Employee List</h1>
<button onclick="addEmployee()">Add Employee</button>
<table border="1">
  <thead>
  <tr>
    <th>ID</th>
    <th>Name</th>
    <th>DateOfBirth</th>
    <th>Email</th>
    <th>Phone</th>
    <th>Address</th>
    <th>Status</th>
    <th>Actions</th>
  </tr>
  </thead>
  <tbody>
    <% EmployeeServices services = new EmployeeServices(); %> <!-- Create a new Services object -->
    <% for (Employee employee : services.getAll()) { %> <!-- Loop through all employees -->
    <tr>
      <td><%= employee.getId() %></td>
      <td><%= employee.getFullName() %></td>
      <td><%= employee.getDob() %></td>
      <td><%= employee.getEmail() %></td>
      <td><%= employee.getPhone() %></td>
      <td><%= employee.getAddress() %></td>
      <td><%= employee.getStatus() %></td>
      <td>
        <button onclick="editEmployee(<%= employee.getId() %>)">Edit</button>
        <button onclick="deleteEmployee(<%= employee.getId() %>)">Delete</button>
      </td>
    </tr>
    <% } %>
  </tbody>
</table>
</body>
</html>