<%@ page import="edu.iuh.fit.week01_lab_nguyenkienthuc_21038611.entities.Account" %>
<%@ page import="java.util.List" %><%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<html>
<head>
  <title>Index</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      background-color: #f4f4f4;
    }
    .login-container {
      margin: 100px auto;
      padding: 20px;
      width: 300px;
      background-color: white;
      box-shadow: 0 0 10px rgba(0,0,0,0.1);
    }
    h1 {
      text-align: center;
      color: #333;
    }
    input[type=text], input[type=password] {
      width: 100%;
      padding: 10px;
      margin: 10px 0;
      border: 1px solid #ddd;
    }
    input[type=submit] {
      width: 100%;
      padding: 10px;
      background-color: #4CAF50;
      color: white;
      border: none;
      cursor: pointer;
    }
    input[type=submit]:hover {
      background-color: #45a049;
    }
  </style>
</head>
<body>
<div class="login-container">
  <h1>Login</h1>
  <!-- Form đăng nhập -->
  <form action="ControlServlet" method="post">
    <input type="hidden" name="action" value="login">
    <label for="username">Username:</label>
    <input type="text" id="username" name="username" required>
    <label for="password">Password:</label>
    <input type="password" id="password" name="password" required>
    <input type="submit" value="Login">
  </form>

  <!-- Hiển thị lỗi nếu có -->
  <%
    String errorMessage = (String) request.getAttribute("errorMessage");
    if (errorMessage != null) {
  %>
  <p style="color:red;"><%= errorMessage %></p>
  <%
    }
  %>
</div>
</body>
</html>
