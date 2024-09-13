<%--
  Created by IntelliJ IDEA.
  User: nktng
  Date: 09/09/2024
  Time: 2:25 CH
  To change this template use File | Settings | File Templates.
--%>
<!-- addAndEdit.jsp -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="edu.iuh.fit.week01_lab_nguyenkienthuc_21038611.entities.Account" %>
<%@ page import="edu.iuh.fit.week01_lab_nguyenkienthuc_21038611.entities.Role" %>
<%@ page import="java.util.List" %>
<html>
<head>
    <title>Add/Edit Account</title>
    <style>
        /* Existing styles */
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
        .form-group input {
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
    <script>
        function validateForm() {
            var checkboxes = document.querySelectorAll('input[name="roles"]:checked');
            if (checkboxes.length === 0) {
                alert("Please select at least one role.");
                return false;
            }
            return true;
        }
    </script>
</head>
<body>
<div class="container">
    <h1><%= request.getParameter("action").equals("edit") ? "Edit Account" : "Add Account" %></h1>
    <form action="ControlServlet" method="post" onsubmit="return validateForm()">
        <input type="hidden" name="action" value="save<%= request.getParameter("action").substring(0, 1).toUpperCase() + request.getParameter("action").substring(1) %>">

        <div class="form-group">
            <label for="accountId">Account ID</label>
            <input type="text" id="accountId" name="accountId" value="<%= request.getAttribute("account") != null ? ((Account) request.getAttribute("account")).getAccountId() : "" %>" <%= request.getParameter("action").equals("edit") ? "readonly" : "" %> required>
        </div>
        <div class="form-group">
            <label for="fullName">Full Name</label>
            <input type="text" id="fullName" name="fullName" value="<%= request.getAttribute("account") != null ? ((Account) request.getAttribute("account")).getFullName() : "" %>" required>
        </div>
        <div class="form-group">
            <label for="password">Password</label>
            <input type="password" id="password" name="password" value="<%= request.getAttribute("account") != null ? ((Account) request.getAttribute("account")).getPassword() : "" %>" required>
        </div>
        <div class="form-group">
            <label for="email">Email</label>
            <input type="email" id="email" name="email" value="<%= request.getAttribute("account") != null ? ((Account) request.getAttribute("account")).getEmail() : "" %>" required>
        </div>
        <div class="form-group">
            <label for="phone">Phone</label>
            <input type="text" id="phone" name="phone" value="<%= request.getAttribute("account") != null ? ((Account) request.getAttribute("account")).getPhone() : "" %>" required>
        </div>
        <div class="form-group">
            <label for="status">Status</label>
            <input type="text" id="status" name="status" value="<%= request.getAttribute("account") != null ? ((Account) request.getAttribute("account")).getStatus() : "" %>" required>
        </div>
        <div class="form-group">
            <label>Roles</label>
            <input type="checkbox" id="roleUser" name="roles" value="user" <%= request.getAttribute("account") != null && ((Account) request.getAttribute("account")).getRoleNames().contains("user") ? "checked" : "" %>> User
            <input type="checkbox" id="roleAdmin" name="roles" value="administrator" <%= request.getAttribute("account") != null && ((Account) request.getAttribute("account")).getRoleNames().contains("administrator") ? "checked" : "" %>> Administrator
        </div>
        <div class="actions">
            <button type="submit"><%= request.getParameter("action").equals("edit") ? "Update Account" : "Add Account" %></button>
            <a href="ControlServlet?action=adminDashboard"><button type="button">Cancel</button></a>
        </div>
    </form>
</div>
</body>
</html>