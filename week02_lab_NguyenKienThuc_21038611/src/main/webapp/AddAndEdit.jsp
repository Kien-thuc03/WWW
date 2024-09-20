<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="edu.iuh.fit.week02_lab_nguyenkienthuc_21038611.models.Employee" %>
<%@ page import="edu.iuh.fit.week02_lab_nguyenkienthuc_21038611.enums.EmployeeStatus" %>
<%@ page import="edu.iuh.fit.week02_lab_nguyenkienthuc_21038611.services.EmployeeServices" %>
<%
    String action = request.getParameter("action");
    Employee employee = null;
    if ("edit".equals(action)) {
        Long id = Long.parseLong(request.getParameter("id"));
        EmployeeServices services = new EmployeeServices();
        employee = services.getEmployeeById(id);
    }
%>
<html>
<head>
    <title><%= "edit".equals(action) ? "Edit Employee" : "Add Employee" %></title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f0f2f5;
            margin: 0;
            padding: 0;
        }
        .container {
            margin: 50px auto;
            padding: 20px;
            width: 50%;
            background-color: #fff;
            box-shadow: 0 4px 8px rgba(0,0,0,0.1);
            border-radius: 8px;
        }
        h1 {
            color: #333;
            text-align: center;
        }
        .form-group {
            margin-bottom: 15px;
        }
        .form-group label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
            color: #555;
        }
        .form-group input, .form-group select {
            width: 100%;
            padding: 10px;
            box-sizing: border-box;
            border: 1px solid #ddd;
            border-radius: 4px;
        }
        .actions {
            margin-top: 20px;
            text-align: center;
        }
        .actions button {
            margin-right: 10px;
            padding: 10px 20px;
            background-color: #4CAF50;
            color: #fff;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        .actions button:hover {
            background-color: #45a049;
        }
        .actions a button {
            background-color: #6c757d;
        }
        .actions a button:hover {
            background-color: #5a6268;
        }
    </style>
</head>
<body>
<div class="container">
    <h1><%= "edit".equals(action) ? "Edit Employee" : "Add Employee" %></h1>
    <form action="ControlServlet" method="post">
      <input type="hidden" name="action" value="save<%= action.substring(0, 1).toUpperCase() + action.substring(1) %>">
      <% if ("edit".equals(action)) { %>
      <div class="form-group">
        <label for="id">Employee ID</label>
        <input type="text" id="id" name="id" value="<%= employee != null ? employee.getId() : "" %>" readonly>
      </div>
      <% } %>
        <div class="form-group">
            <label for="fullName">Full Name</label>
            <input type="text" id="fullName" name="fullName" value="<%= employee != null ? employee.getFullName() : "" %>" required>
        </div>
        <div class="form-group">
            <label for="dob">Date of Birth</label>
            <input type="date" id="dob" name="dob" value="<%= employee != null ? employee.getDob() : "" %>" required>
        </div>
        <div class="form-group">
            <label for="email">Email</label>
            <input type="email" id="email" name="email" value="<%= employee != null ? employee.getEmail() : "" %>" required>
        </div>
        <div class="form-group">
            <label for="phone">Phone</label>
            <input type="text" id="phone" name="phone" value="<%= employee != null ? employee.getPhone() : "" %>" required>
        </div>
        <div class="form-group">
            <label for="address">Address</label>
            <input type="text" id="address" name="address" value="<%= employee != null ? employee.getAddress() : "" %>" required>
        </div>
        <div class="form-group">
            <label for="status">Status</label>
            <select id="status" name="status" required>
                <option value="ACTIVE" <%= employee != null && employee.getStatus() == EmployeeStatus.ACTIVE ? "selected" : "" %>>Active</option>
                <option value="IN_ACTIVE" <%= employee != null && employee.getStatus() == EmployeeStatus.IN_ACTIVE ? "selected" : "" %>>Inactive</option>
                <option value="TERMINATED" <%= employee != null && employee.getStatus() == EmployeeStatus.TERMINATED ? "selected" : "" %>>Terminated</option>
            </select>
        </div>
        <div class="actions">
            <button type="submit"><%= "edit".equals(action) ? "Update Employee" : "Add Employee" %></button>
            <a href="Employees.jsp"><button type="button">Cancel</button></a>
        </div>
    </form>
</div>
</body>
</html>