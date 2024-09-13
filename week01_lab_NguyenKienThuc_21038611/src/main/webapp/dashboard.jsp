<%--
  Created by IntelliJ IDEA.
  User: nktng
  Date: 07/09/2024
  Time: 8:20 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="edu.iuh.fit.week01_lab_nguyenkienthuc_21038611.entities.Account" %>
<%@ page import="java.util.List" %>

<html>
<head>
    <title>Dashboard</title>
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
            width: 80%;
            background-color: #fff;
            box-shadow: 0 4px 8px rgba(0,0,0,0.1);
            border-radius: 8px;
        }
        h1 {
            color: #333;
            text-align: center;
            margin-bottom: 20px;
        }
        h2 {
            color: #333;
            border-bottom: 2px solid #4CAF50;
            padding-bottom: 10px;
            margin-bottom: 20px;
        }
        table {
            width: 100%;
            margin-top: 20px;
            border-collapse: collapse;
        }
        table, th, td {
            border: 1px solid #ddd;
        }
        th, td {
            padding: 12px;
            text-align: left;
        }
        th {
            background-color: #1c9362;
            color: white;
        }
        tr:nth-child(even) {
            background-color: #f9f9f9;
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
            transition: background-color 0.3s ease;
        }
        .actions button:hover {
            background-color: #45a049;
        }
        .form-inline {
            display: inline;
        }
        .btn-edit, .btn-delete {
            width: 100px;
            padding: 12px 24px;
            border-radius: 8px;
        }
        .btn-edit {
            background-color: #cebf64;
            border: none;
        }
        .btn-edit:hover {
            background-color: #9b934b;
        }
        .btn-delete {
            background-color: #dc3545;
            border: none;
        }
        .btn-delete:hover {
            background-color: #c82333;
        }
        .action-buttons {
            display: flex;
            justify-content: space-between;
        }
        .roles-section {
            margin-top: 30px;
        }
        .role-filter {
            margin-bottom: 20px;
        }
        .role-filter label {
            margin-right: 15px;
        }
        .role-filter input[type="checkbox"] {
            margin-right: 5px;
        }
        .role-section-table {
            border-radius: 8px;
            overflow: hidden;
        }
        .role-section-table th {
            background-color: #1c9362;
        }
        .role-section-table td {
            vertical-align: middle;
        }
        .role-form {
            display: flex;
            flex-direction: column;
            align-items: center;
        }
        .role-form label {
            margin-bottom: 5px;
        }
        .role-form input[type="checkbox"] {
            margin-right: 10px;
        }
        .role-form button {
            width: fit-content;
            margin-top: 10px;
            padding: 10px;
            background-color: #4CAF50;
            color: #fff;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }
        .role-form button:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>BÀI TẬP THỰC HÀNH TUẦN 1</h1>
        <%
        Account userAccount = (Account) session.getAttribute("account");
        Long logId = (Long) session.getAttribute("logId");
        String accountId = request.getParameter("accountId");
        if (userAccount != null) {
            boolean isAdmin = userAccount.getRoleNames().contains("administrator");
    %>
    <h2>User Information</h2>
    <div class="info">
        <p><span>Full Name:</span> <%= userAccount.getFullName() %></p>
        <p><span>Email:</span> <%= userAccount.getEmail() %></p>
        <p><span>Phone:</span> <%= userAccount.getPhone() %></p>
        <p><span>Status:</span> <%= userAccount.getStatus() %></p>
        <p><span>Roles:</span> <%= String.join(", ", userAccount.getRoleNames()) %></p>
    </div>
    <div class="actions">
        <form action="ControlServlet" method="post" class="form-inline">
            <input type="hidden" name="action" value="logout">
            <input type="hidden" name="logId" value="<%= logId %>">
            <button type="submit">Log out</button>
        </form>
    </div>
    <%
        if (isAdmin) {
    %>
    <h2>Account Management</h2>
    <table class="role-section-table">
        <tr>
            <th>Account ID</th>
            <th>Full Name</th>
            <th>Email</th>
            <th>Phone</th>
            <th>Status</th>
            <th>Roles</th>
            <th>Actions</th>
        </tr>
        <%
            List<Account> accounts = (List<Account>) session.getAttribute("accounts");
            if (accounts != null) {
                for (Account account : accounts) {
        %>
        <tr>
            <td><%= account.getAccountId() %></td>
            <td><%= account.getFullName() %></td>
            <td><%= account.getEmail() %></td>
            <td><%= account.getPhone() %></td>
            <td><%= account.getStatus() %></td>
            <td><%= String.join(", ", account.getRoleNames()) %></td> <!-- Display roles -->
            <td class="action-buttons">
                <form action="ControlServlet" method="post" class="form-inline">
                    <input type="hidden" name="action" value="edit">
                    <input type="hidden" name="accountId" value="<%= account.getAccountId() %>">
                    <button type="submit" class="btn-edit">Edit</button>
                </form>
                <form action="ControlServlet" method="post" class="form-inline">
                    <input type="hidden" name="action" value="delete">
                    <input type="hidden" name="accountId" value="<%= account.getAccountId() %>">
                    <button type="submit" class="btn-delete">Delete</button>
                </form>
            </td>
        </tr>
        <%
            }
        } else {
        %>
        <tr>
            <td colspan="7">No accounts found.</td>
        </tr>
        <%
            }
        %>
    </table>
    <div class="actions">
        <form action="ControlServlet" method="post" class="form-inline">
            <input type="hidden" name="action" value="add">
            <button type="submit">Add Account</button>
        </form>
    </div>
    <div class="roles-section">
        <h2>Assign Roles</h2>
        <% if (request.getAttribute("errorMessage") != null) { %>
        <div style="color: red; font-weight: bold; margin-bottom: 20px;">
            <%= request.getAttribute("errorMessage") %>
        </div>
        <% } %>
        <div class="role-filter">
            <label><input type="checkbox" name="role" value="administrator" onclick="filterRoles()"> Administrator</label>
            <label><input type="checkbox" name="role" value="user" onclick="filterRoles()"> User</label>
        </div>
        <form action="ControlServlet" method="post" class="role-form">
            <input type="hidden" name="action" value="assignRoles">
            <table>
                <tr>
                    <th>Account ID</th>
                    <th>Full Name</th>
                    <th>Roles</th>
                    <th>Assign Roles</th>
                </tr>
                <% if (accounts != null) {
                    for (Account account : accounts) { %>
                <tr class="role-row" data-roles="<%= String.join(" ", account.getRoleNames()) %>">
                    <td><%= account.getAccountId() %></td>
                    <td><%= account.getFullName() %></td>
                    <td><%= String.join(", ", account.getRoleNames()) %></td>
                    <td>
                        <input type="hidden" name="accountId_<%= account.getAccountId() %>" value="<%= account.getAccountId() %>">
                        <label>
                            <input type="checkbox" name="roles_<%= account.getAccountId() %>" value="administrator"
                                <%= account.getRoleNames().contains("administrator") ? "checked" : "" %>> Administrator
                        </label>
                        <label>
                            <input type="checkbox" name="roles_<%= account.getAccountId() %>" value="user"
                                <%= account.getRoleNames().contains("user") ? "checked" : "" %>> User
                        </label>
                    </td>
                </tr>
                <% }
                } else { %>
                <tr>
                    <td colspan="4">No accounts found.</td>
                </tr>
                <% } %>
            </table>
            <button type="submit">Apply Changes</button>
        </form>
    </div>
    <%
        }
    } else {
    %>
    <p>No user information available.</p>
    <%
        }
    %>
</div>
<script>
    function filterRoles() {
        var checkboxes = document.querySelectorAll('.role-filter input[type="checkbox"]');
        var rows = document.querySelectorAll('.role-row');
        var selectedRoles = Array.from(checkboxes).filter(checkbox => checkbox.checked).map(checkbox => checkbox.value);

        rows.forEach(row => {
            var rowRoles = row.getAttribute('data-roles').split(' ');
            var showRow = selectedRoles.every(role => rowRoles.includes(role));
            row.style.display = showRow ? '' : 'none';
        });
    }
</script>
</body>
</html>